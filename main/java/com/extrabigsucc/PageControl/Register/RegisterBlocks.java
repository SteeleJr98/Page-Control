package com.extrabigsucc.PageControl.Register;

import com.extrabigsucc.PageControl.Blocks.Base.BaseItemBlock;

import com.extrabigsucc.PageControl.Blocks.Tile.TeTestTileEntity;
import com.extrabigsucc.PageControl.Init.InitBlocks;

import cpw.mods.fml.common.registry.GameRegistry;

public class RegisterBlocks {
    public static void Blocks() {
        GameRegistry.registerBlock(InitBlocks.testBlock, BaseItemBlock.class, "testBlock"); //No Texture
        GameRegistry.registerBlock(InitBlocks.teTestBlock, BaseItemBlock.class, "teTestBlock"); //No Texture

        GameRegistry.registerBlock(InitBlocks.onlineDetectorBlock, BaseItemBlock.class, "onlineDetectorBlock"); //No Texture
        GameRegistry.registerBlock(InitBlocks.itemVoidBlock, BaseItemBlock.class, "itemVoidBlock"); //No Texture

    }

    public static void TileEntities() {
        GameRegistry.registerTileEntity(TeTestTileEntity.class, "TestTileEntity");
    }
}
