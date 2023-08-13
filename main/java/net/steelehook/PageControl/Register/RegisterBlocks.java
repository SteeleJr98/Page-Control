package net.steelehook.PageControl.Register;

import cpw.mods.fml.common.registry.GameRegistry;
import net.steelehook.PageControl.Blocks.OnlineDetectorBlock.TestTE;
import net.steelehook.PageControl.Blocks.ItemBlocks.ItemVoidItemBlock;
import net.steelehook.PageControl.Blocks.ItemBlocks.OnlineDetectorItemBlock;
import net.steelehook.PageControl.Blocks.ItemBlocks.ServerItemVoidItemBlock;
import net.steelehook.PageControl.Blocks.Tile.OnlineDetectorTileEntity;
import net.steelehook.PageControl.Init.InitBlocks;


public class RegisterBlocks {
    public static void Blocks() {
        GameRegistry.registerBlock(InitBlocks.onlineDetectorBlock, OnlineDetectorItemBlock.class, InitBlocks.onlineDetectorBlock.getUnlocalizedName()); //Temp Texture
        GameRegistry.registerBlock(InitBlocks.itemVoidBlock, ItemVoidItemBlock.class, InitBlocks.itemVoidBlock.getUnlocalizedName()); //Temp Texture
        GameRegistry.registerBlock(InitBlocks.serverItemVoidBlock, ServerItemVoidItemBlock.class, InitBlocks.serverItemVoidBlock.getUnlocalizedName()); //Temp Texture
    }

    public static void TileEntities() {
        //GameRegistry.registerTileEntity(OnlineDetectorTileEntity.class, "onlineDetectorTileEntity");
        GameRegistry.registerTileEntity(TestTE.class, "onlineDetectorTileEntity");
    }
}

