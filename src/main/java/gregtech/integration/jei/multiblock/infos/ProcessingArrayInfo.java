package gregtech.integration.jei.multiblock.infos;

import com.google.common.collect.Lists;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class ProcessingArrayInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return MetaTileEntities.PROCESSING_ARRAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
                .aisle("XIX", "XXX", "XXX")
                .aisle("XXX", "S#E", "XXX")
                .aisle("XOX", "XXX", "XXX")
                .where('S', MetaTileEntities.PROCESSING_ARRAY, EnumFacing.WEST)
                .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST))
                .where('#', Blocks.AIR.getDefaultState())
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.NORTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.LV],EnumFacing.EAST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.SOUTH).build();

        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[] { I18n.format("gregtech.multiblock.processing_array.description") };
    }

}
