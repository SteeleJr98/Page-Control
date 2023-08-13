package net.steelehook.PageControl.Blocks.ItemBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class OnlineDetectorItemBlock extends ItemBlock {
    public OnlineDetectorItemBlock(Block block) {
        super(block);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
        list.add("Emits a Redstone Signal");
        list.add("When The Owner is Online");
    }

}
