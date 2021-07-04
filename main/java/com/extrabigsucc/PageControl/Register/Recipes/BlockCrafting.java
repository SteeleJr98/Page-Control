package com.extrabigsucc.PageControl.Register.Recipes;

import com.extrabigsucc.PageControl.Init.InitBlocks;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class BlockCrafting {



    public static void Shaped() {
        GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.onlineDetectorBlock), "IRI", "RCR", "IEI", 'I', Items.iron_ingot, 'R', Items.redstone, 'C', Blocks.crafting_table, 'E', Items.ender_pearl);
    }

    public static void Shapeless() {

    }

}
