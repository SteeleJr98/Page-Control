package net.steelehook.PageControl.Blocks.ItemBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.steelehook.PageControl.Blocks.Base.BaseItemBlock;

import java.util.List;

public class ItemVoidItemBlock extends BaseItemBlock {
    public ItemVoidItemBlock(Block block) {
        super(block);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
        list.add("Removes all Dropped Items");
        list.add("Within a 33x33x11 Box");
        list.add("Around The Block");
    }
}
