package gregtech.integration.jei.multiblock.infos;

import com.google.common.collect.Lists;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class FusionReactorInfo extends MultiblockInfoPage {

    private final int fusionTier;

    public FusionReactorInfo(int fusionTier) {
        this.fusionTier = fusionTier - 1;
    }

    @Override
    public MultiblockControllerBase getController() {
        return MetaTileEntities.FUSION_REACTOR[fusionTier];
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
                .aisle("###############", "######NCN######", "###############")
                .aisle("######DCD######", "####CCcccCC####", "######UCU######")
                .aisle("####CC###CC####", "###sccNCNccs###", "####CC###CC####")
                .aisle("###C#######C###", "##wcnC###Cnce##", "###C#######C###")
                .aisle("##C#########C##", "#Cce#######wcC#", "##C#########C##")
                .aisle("##C#########C##", "#CcC#######CcC#", "##C#########C##")
                .aisle("#D###########D#", "WcE#########WcE", "#U###########U#")
                .aisle("#C###########C#", "CcC#########CcC", "#C###########C#")
                .aisle("#D###########D#", "WcE#########WcE", "#U###########U#")
                .aisle("##C#########C##", "#CcC#######CcC#", "##C#########C##")
                .aisle("##C#########C##", "#Cce#######wcC#", "##C#########C##")
                .aisle("###C#######C###", "##wcsC###Csce##", "###C#######C###")
                .aisle("####CC###CC####", "###nccSCSccn###", "####CC###CC####")
                .aisle("######DCD######", "####CCcccCC####", "######UCU######")
                .aisle("###############", "######NMN######", "###############")
                .where('M', getController(), EnumFacing.SOUTH)
                .where('C', getCasing())
                .where('c', getCoil())
                .where('W', MetaTileEntities.FLUID_EXPORT_HATCH[6 + fusionTier], EnumFacing.WEST)
                .where('E', MetaTileEntities.FLUID_EXPORT_HATCH[6 + fusionTier], EnumFacing.EAST)
                .where('S', MetaTileEntities.FLUID_EXPORT_HATCH[6 + fusionTier], EnumFacing.SOUTH)
                .where('N', MetaTileEntities.FLUID_EXPORT_HATCH[6 + fusionTier], EnumFacing.NORTH)
                .where('w', MetaTileEntities.ENERGY_INPUT_HATCH[6 + fusionTier], EnumFacing.WEST)
                .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[6 + fusionTier], EnumFacing.EAST)
                .where('s', MetaTileEntities.ENERGY_INPUT_HATCH[6 + fusionTier], EnumFacing.SOUTH)
                .where('n', MetaTileEntities.ENERGY_INPUT_HATCH[6 + fusionTier], EnumFacing.NORTH)
                .where('U', MetaTileEntities.FLUID_IMPORT_HATCH[6 + fusionTier], EnumFacing.UP)
                .where('D', MetaTileEntities.FLUID_IMPORT_HATCH[6 + fusionTier], EnumFacing.DOWN)
                .where('#', Blocks.AIR.getDefaultState()).build();

        return Lists.newArrayList(shapeInfo);
    }

    private IBlockState getCasing() {
        switch (fusionTier) {
            case 0:
                return MetaBlocks.MACHINE_CASING.getState(BlockMachineCasing.MachineCasingType.LuV);
            case 1:
                return MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.FUSION_CASING);
            default:
                return MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.FUSION_CASING_MK2);
        }
    }

    private IBlockState getCoil() {
        return fusionTier == 0 ?
                MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.SUPERCONDUCTOR) :
                MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.FUSION_COIL);
    }

    @Override
    public String[] getDescription() {
        String translationKey = "gregtech.multiblock.fusion_reactor_mk" + (fusionTier + 1) + ".description";
        return new String[] { I18n.format(translationKey) };
    }

    @Override
    public float getDefaultZoom() {
        return 0.5f;
    }
}
