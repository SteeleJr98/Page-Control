package com.steelehook.PageControl.CreativeTabs;

import com.steelehook.PageControl.PageControl;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class stpc170Tab {
    public static final CreativeTabs stpcTab = new CreativeTabs(PageControl.MODID.toLowerCase() + ":stpc.name") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.redstone_block);
        }
    };
}
