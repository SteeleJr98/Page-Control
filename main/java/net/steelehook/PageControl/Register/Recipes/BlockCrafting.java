package net.steelehook.PageControl.Register.Recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.steelehook.PageControl.Init.InitBlocks;

public class BlockCrafting {



    public static void Shaped() {
        GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.onlineDetectorBlock), "IRI", "RCR", "IEI", 'I', Items.iron_ingot, 'R', Items.redstone, 'C', Blocks.crafting_table, 'E', Items.ender_pearl);
        GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.itemVoidBlock), "IRI", "RBR", "IEI", 'I', Items.iron_ingot, 'R', Items.redstone, 'B', Items.lava_bucket, 'E', Items.ender_pearl);
    }

    public static void Shapeless() {

    }

}
