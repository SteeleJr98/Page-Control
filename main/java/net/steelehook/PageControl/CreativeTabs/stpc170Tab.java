package net.steelehook.PageControl.CreativeTabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.steelehook.PageControl.PageControl;

public class stpc170Tab {
    public static final CreativeTabs stpcTab = new CreativeTabs(PageControl.MODID.toLowerCase() + ":stpc.name") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.redstone_block);
        }
    };
}
