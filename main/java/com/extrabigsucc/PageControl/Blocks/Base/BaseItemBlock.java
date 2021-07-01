package com.extrabigsucc.PageControl.Blocks.Base;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class BaseItemBlock extends ItemBlock {
    public BaseItemBlock(Block block) {
        super(block);
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemstack) {
        return (StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(itemstack) + ".name"));
    }

}
