package gregtech.common.tools;

import net.minecraft.item.ItemStack;

public class ToolBendingCylinder extends ToolBase {

    @Override
    public int getToolDamagePerBlockBreak(ItemStack stack) {
        return 2;
    }

    @Override
    public int getToolDamagePerContainerCraft(ItemStack stack) {
        return 5;
    }

    @Override
    public float getBaseDamage(ItemStack stack) {
        return 3F;
    }
}
