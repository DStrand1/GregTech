package gregtech.api.items.metaitem.stats;

import net.minecraft.item.ItemStack;

public interface IFluidStats extends IMetaItemStats {

    boolean allowPartiallyFilled();

    /**
     *
     * @param container
     *            ItemStack which is the fluid container.
     * @return Capacity of this fluid container.
     */
    int getCapacity(ItemStack container);

    /**
     *
     * @param container
     *            ItemStack which is the fluid container.
     * @return Min temperature this container can accept.
     */
    int getMinFluidTemperature(ItemStack container);

    /**
     *
     * @param container
     *            ItemStack which is the fluid container.
     * @return Max temperature this container can accept.
     */
    int getMaxFluidTemperature(ItemStack container);
}
