package net.steelehook.PageControl.Blocks.ItemBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import net.steelehook.SteeleCore.Base.Blocks.BaseItemBlock;

import java.util.List;

public class ServerItemVoidItemBlock extends BaseItemBlock {
    public ServerItemVoidItemBlock(Block block) {
        super(block);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
        list.add("No Crafting Recipe");
        list.add("Should Wipe Every Dropped");
        list.add("Item Currently Loaded");
        list.add("In The World");

    }
}
