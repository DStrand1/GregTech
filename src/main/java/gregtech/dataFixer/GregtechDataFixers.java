package gregtech.dataFixer;

import com.google.common.collect.ImmutableList;
import gregtech.api.GTValues;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTLog;
import gregtech.common.blocks.BlockCompressed;
import gregtech.common.blocks.MetaBlocks;
import gregtech.dataFixer.fixes.BlockFixDefinition;
import gregtech.dataFixer.fixes.BlockFixer;
import gregtech.dataFixer.fixes.GeneralFixer;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.FixTypes;
import net.minecraftforge.common.util.ModFixs;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ZipperUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import static gregtech.api.GTValues.MODID;

public class GregtechDataFixers {

    // The first version which introduced wrong data
    public static final String WRONG_DATA_VERSION_START = "1.10.5";

    // The last version which used wrong data
    public static final String WRONG_DATA_VERSION_END = "1.11.3";

    // Whether there is currently a world loaded, used to prevent multiple WorldEvent.Load events
    public static boolean worldLoaded = false;

    public static DataFixInfoWorldData worldSavedData;

    public static List<BlockFixDefinition> blockFixDefinitions;

    public static void createFixDefinitions() {
        final ImmutableList.Builder<BlockFixDefinition> fixDefs = new ImmutableList.Builder<>();

        Set<Material> toIgnore = new HashSet<>();
        toIgnore.add(Materials.Granite);

        final Set<Mapping> oldMappings = new TreeSet<>();
        MetaBlocks.createGeneratedBlockDeprecated(
                material -> !toIgnore.contains(material) &&
                        material instanceof DustMaterial &&
                        !OrePrefix.block.isIgnored(material),
                generateMappingSet(m -> m instanceof DustMaterial, oldMappings)
        );

        //This will create the fix definition for before granite was added to the material list
        for(Mapping item : oldMappings) {
            fixDefs.add(new BlockFixDefinition(
                    new ResourceLocation("gregtech", "compressed_" + item.materialIndex),
                    (short)item.metaBlockNumber, Block.getBlockFromName("gregtech:compressed_" + item.materialIndex).getStateFromMeta(item.metaBlockNumber)));
        }

        //If we go with changing the block name, which we should, there needs to be another set of fix definitions, for after
        //Granite was added


        //These are the included examples from the example DFU mod, I have left them here so that we can look at them if needed

  /*      HashMap<Integer, IBlockState> stoneVariants = new HashMap<>();
        for (int i = 0; i <= 6; i++) {
            stoneVariants.put(i, Block.getBlockFromName("minecraft:stone").getStateFromMeta(i));
        }

        for (short i = 1; i <= 6; i++) {
            fixDefs.add(new BlockFixDefinition(
                new ResourceLocation("minecraft", "stone"), i, stoneVariants.get(i - 1)));
        }

        fixDefs.add(new BlockFixDefinition(
            new ResourceLocation("minecraft", "grass"), (short) 0, stoneVariants.get(6)));

        //fixDefs.add(new BlockFixDefinition(
        //    new ResourceLocation("examplemod:test2"), (short) 0, RegistryHandler.BLOCKS.get(new ResourceLocation("examplemod:test1")).getStateFromMeta(0)));
*/
        blockFixDefinitions = fixDefs.build();
    }

    private static BiConsumer<Material[], Integer> generateMappingSet(Predicate<Material> filter,
                                                                      Set<Mapping> resultSet) {
        return (materials, blockNumber) -> {
            for(int metaNumber = 0; metaNumber < materials.length; metaNumber++) {
                final Material material = materials[metaNumber];
                if (filter.test(material))
                    resultSet.add(new Mapping(blockNumber, metaNumber, material));
            }
        };
    }

    private void registerFixes() {
        final ModFixs modFixs = FMLCommonHandler.instance().getDataFixer().init(GTValues.MODID, 12);
        modFixs.registerFix(FixTypes.CHUNK, new BlockFixer());

        modFixs.registerFix(FixTypes.ENTITY, new GeneralFixer(compound -> {
            // Dynamically fix items in entities' inventories/hand etc.
            GregtechDataFixers.printNBTCompound(compound, "Entity");
            if (worldSavedData != null && worldSavedData.dataFixes) GeneralFixer.fixItemsInCompound(compound);
        }));

        modFixs.registerFix(FixTypes.BLOCK_ENTITY, new GeneralFixer(compound -> {
            // Dynamically fix items in entities' inventories/hand etc.
            GregtechDataFixers.printNBTCompound(compound, "BlockEntity");
            if (worldSavedData != null && worldSavedData.dataFixes) GeneralFixer.fixItemsInCompound(compound);
        }));

        modFixs.registerFix(FixTypes.PLAYER, new GeneralFixer(compound -> {
            // Dynamically fix items in joining player's inventories, except when the player is host of an IntegratedServer
            GregtechDataFixers.printNBTCompound(compound, "Player");
            if (worldSavedData != null && worldSavedData.dataFixes) GeneralFixer.fixItemsInCompound(compound);
        }));

        /*modFixs.registerFix(FixTypes.LEVEL, new GeneralFixer(compound -> {
            ExampleMod.printNBTCompound(compound, "Level");
            if (dataFixes) GeneralFixer.fixItemsInCompound(compound);
        }));*/

        //modFixs.registerFix(FixTypes.ITEM_INSTANCE, new GeneralFix(compound -> printNBTCompound(compound, "Item")));
        //FMLCommonHandler.instance().getDataFixer().registerWalker(FixTypes.ITEM_INSTANCE, new ItemWalker());
    }

    //@SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (!event.getWorld().isRemote && !worldLoaded) {
            worldLoaded = true;
            GTLog.logger.debug("Opened world save");

            // Check if this world was previously started being fixed
            worldSavedData = DataFixInfoWorldData.load(event.getWorld());
            if (worldSavedData != null) {
                GTLog.logger.debug("This world was previously flagged with dataFixes {}", worldSavedData.dataFixes ? "enabled" : "disabled");
                return;
            }
            worldSavedData = new DataFixInfoWorldData();

            // Any code from this point should only ever run at most once for a world

            try {
                File saveDir = event.getWorld().getSaveHandler().getWorldDirectory();
                if (!saveDir.exists()) return;
                File levelDat = new File(saveDir, "level.dat");
                if (!levelDat.exists()) {
                    // Most likely a freshly created world, no need for data fixes
                    GTLog.logger.debug("Level.dat doesn't exist!");
                    GTLog.logger.debug("World is freshly created, no need for dataFixes.");
                    DataFixInfoWorldData.save(event.getWorld(), worldSavedData);
                    return;
                }
                NBTTagCompound nbt = CompressedStreamTools.readCompressed(new FileInputStream(levelDat));
                NBTTagCompound fmlTag = nbt.getCompoundTag("FML");

                if (fmlTag.hasKey("ModList")) {
                    NBTTagList modList = fmlTag.getTagList("ModList", (byte) 10);
                    for (int i = 0; i < modList.tagCount(); i++) {
                        NBTTagCompound mod = modList.getCompoundTagAt(i);
                        if (!mod.getString("ModId").equals(MODID)) continue;
                        String versionInSave = mod.getString("ModVersion");

                        // Check if the mod version in the save is between WRONG_DATA_VERSION_START and WRONG_DATA_VERSION_END
                        int startVer = VersionUtil.compare(versionInSave, WRONG_DATA_VERSION_START);
                        int endVer = VersionUtil.compare(versionInSave, WRONG_DATA_VERSION_END);
                        if ((startVer == 1 || startVer == 0) && (endVer == -1 || endVer == 0)) {
                            // Create a backup of the world in case something goes wrong with the conversion
                            createWorldBackup();

                            // This world was last opened with a "wrong" version
                            worldSavedData.dataFixes = true;
                            worldSavedData.fixHostPlayerInventory = true;
                        } else if (startVer == -1) {
                            // This world was last opened with a version that didn't introduce wrong data yet
                            // TODO set some boolean in worldSavedData to use in the fixing process for determining what kind of conversion to do
                        }
                        // Make sure to persist whether this world needs to be fixed
                        DataFixInfoWorldData.save(event.getWorld(), worldSavedData);

                        GTLog.logger.debug("Mod version in save is {}, which means dataFixes should be {}.", versionInSave, worldSavedData.dataFixes ? "enabled" : "disabled");
                    }
                } else GTLog.logger.debug("NBT doesnt have ModList key");
            } catch (Exception e) {
                GTLog.logger.error("Error on WorldLoad");
                e.printStackTrace();
            }
        }
    }

    //@SubscribeEvent
    public void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        GTLog.logger.debug("PlayerLoggedIn");

        // If data fixing is disabled for the current world, skip
        if (!worldSavedData.dataFixes) return;

        EntityPlayer player = event.player;

        // The first time this world is loaded in singleplayer, fixHostPlayerInventory
        // is going to be set to false, and never run again.
        // Itemfixing here only happens for the host player of this singleplayer world,
        // any other case (players joining a DedicatedServer or a LAN IntegratedServer)
        // is covered by the Player Datafixer.

        // Check if this is an IntegratedServer
        if (!FMLCommonHandler.instance().getMinecraftServerInstance().isDedicatedServer()) {
            // Fix items in player's inventory
            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                NBTTagCompound slotNBT = player.inventory.getStackInSlot(i).serializeNBT();
                GTLog.logger.debug("PlayerInventory contents at index {}: {}", i, slotNBT);
                if (worldSavedData.fixHostPlayerInventory) {
                    GeneralFixer.fixItemsInCompound(slotNBT);
                    player.inventory.setInventorySlotContents(i, new ItemStack(slotNBT));
                }
            }

            // Fix items in player's EnderChest
            for (int i = 0; i < player.getInventoryEnderChest().getSizeInventory(); i++) {
                NBTTagCompound slotNBT = player.getInventoryEnderChest().getStackInSlot(i).serializeNBT();
                GTLog.logger.debug("Player EnderChest contents at index {}: {}", i, slotNBT);
                if (worldSavedData.fixHostPlayerInventory) {
                    GeneralFixer.fixItemsInCompound(slotNBT);
                    player.getInventoryEnderChest().setInventorySlotContents(i, new ItemStack(slotNBT));
                }
            }

            worldSavedData.fixHostPlayerInventory = false;
            DataFixInfoWorldData.save(event.player.getEntityWorld(), worldSavedData);
        } else GTLog.logger.debug("Server is dedicated, skipping PlayerLoggedIn fixes.");
    }

    //@SubscribeEvent
    public void missingMappingsBlock(RegistryEvent.MissingMappings<Block> event) {
        GTLog.logger.debug(event.getAllMappings().size());
        GTLog.logger.debug(event.getMappings().size());
        GTLog.logger.debug(event.getName());

        for (RegistryEvent.MissingMappings.Mapping<Block> entry : event.getAllMappings()) {
            GTLog.logger.debug(entry.id);
            GTLog.logger.debug(entry.key);
            entry.ignore();
            //if (entry.key.toString().equals("examplemod:test2")) entry.remap(RegistryHandler.BLOCKS.get(new ResourceLocation("examplemod:test1")));
        }
    }

    //@SubscribeEvent
    public void missingMappingsItem(RegistryEvent.MissingMappings<Item> event) {
        GTLog.logger.debug(event.getAllMappings().size());
        GTLog.logger.debug(event.getMappings().size());
        GTLog.logger.debug(event.getName());

        for (RegistryEvent.MissingMappings.Mapping<Item> entry : event.getAllMappings()) {
            GTLog.logger.debug(entry.id);
            GTLog.logger.debug(entry.key);
            entry.ignore();
            //if (entry.key.toString().equals("examplemod:test2")) entry.remap(RegistryHandler.ITEMS.get(new ResourceLocation("examplemod:test1")));
        }
    }

    private static void createWorldBackup() {
        try {
            GTLog.logger.info("Creating world backup before starting DataFixers...");
            ZipperUtil.backupWorld();
        } catch (Exception e) {
            GTLog.logger.error("Error creating backup!!!");
            e.printStackTrace();
            // Maybe the server should be closed to prevent damage? like how Forge MissingMappings does it
        }
    }

    public static void printNBTCompound(NBTTagCompound rootCompound, String type) {
        String str = rootCompound.toString().trim();
        if (str.length() > 2) GTLog.logger.debug("{} Fixer: {}", type, str);
    }

    public static void printNBTCompoundMatch(NBTTagCompound compound) {
        String str = compound.toString().trim();
        if (str.length() > 2) GTLog.logger.debug("Found NBTCompound match: {}", str);
    }
}
