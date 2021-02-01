package gregtech.common.metatileentities.electric;

import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.render.OrientedOverlayRenderer;
import net.minecraft.util.ResourceLocation;

public class MetaTileEntityGasCollector extends SimpleMachineMetaTileEntity {

    private int dimID;
    private final int TANK_SIZE = 32000;

    public MetaTileEntityGasCollector(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, OrientedOverlayRenderer renderer, int tier) {
        super(metaTileEntityId, recipeMap, renderer, tier);
        this.dimID = getWorld().provider.getDimension();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityGasCollector(metaTileEntityId, workable.recipeMap, renderer, getTier());
    }

    /*
    @Override
    protected FluidTankList createExportFluidHandler() {
        return new FluidTankList(true, TANK_SIZE);
    }*/
}
