package gregtech.common.metatileentities.electric;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.common.ConfigHolder;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidTank;

import java.util.stream.IntStream;

public class MetaTileEntityGasCollector extends SimpleMachineMetaTileEntity {

    private int dimID;
    private static final int TANK_SIZE = 32000;

    public MetaTileEntityGasCollector(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, int tier) {
        super(metaTileEntityId, recipeMap, new OrientedOverlayRenderer(""), tier);
        dimID = -1000;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityGasCollector(metaTileEntityId, workable.recipeMap, getTier());
    }

    @Override
    protected FluidTankList createExportFluidHandler() {
        return new FluidTankList(false, new FluidTank(TANK_SIZE));
    }

    private int checkDimension() {
        return getWorld().provider.getDimension();
    }

    @Override
    public void update() {
        if (dimID == -1000)
            dimID = checkDimension();
        super.update();
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        EnumFacing frontFacing = getFrontFacing();
        for (EnumFacing side : EnumFacing.VALUES) {
            if (side.getAxis().isHorizontal()) {
                Textures.AIR_VENT_OVERLAY.renderSided(side, renderState, translation, pipeline);
            } else {
                Textures.FILTER_OVERLAY.renderSided(side, renderState, translation, pipeline);
            }
        }
        Textures.PIPE_OUT_OVERLAY.renderSided(frontFacing, renderState, translation, pipeline);
    }
}
