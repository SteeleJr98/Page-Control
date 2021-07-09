package com.steelehook.PageControl.Register;

import com.steelehook.PageControl.Blocks.Base.BaseItemBlock;

import com.steelehook.PageControl.Blocks.ItemBlocks.ItemVoidItemBlock;
import com.steelehook.PageControl.Blocks.ItemBlocks.OnlineDetectorItemBlock;
import com.steelehook.PageControl.Blocks.ItemBlocks.ServerItemVoidItemBlock;
import com.steelehook.PageControl.Blocks.Tile.OnlineDetectorTileEntity;
import com.steelehook.PageControl.Init.InitBlocks;

import cpw.mods.fml.common.registry.GameRegistry;


public class RegisterBlocks {
    public static void Blocks() {
        GameRegistry.registerBlock(InitBlocks.onlineDetectorBlock, OnlineDetectorItemBlock.class, "onlineDetectorBlock"); //Temp Texture
        GameRegistry.registerBlock(InitBlocks.itemVoidBlock, ItemVoidItemBlock.class, "itemVoidBlock"); //Temp Texture
        GameRegistry.registerBlock(InitBlocks.serverItemVoidBlock, ServerItemVoidItemBlock.class, "serverItemVoidBlock"); //Temp Texture
    }

    public static void TileEntities() {
        GameRegistry.registerTileEntity(OnlineDetectorTileEntity.class, "onlineDetectorTileEntity");
    }
}
