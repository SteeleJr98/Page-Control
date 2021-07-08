package com.steelehook.PageControl.Blocks.ItemBlocks;

import com.steelehook.PageControl.Blocks.Base.BaseItemBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class OnlineDetectorItemBlock extends BaseItemBlock {
    public OnlineDetectorItemBlock(Block block) {
        super(block);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
        list.add("Emits a Redstone Signal");
        list.add("When The Owner is Online");
    }

}
