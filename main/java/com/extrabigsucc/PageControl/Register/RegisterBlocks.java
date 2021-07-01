package com.extrabigsucc.PageControl.Register;

import com.extrabigsucc.PageControl.Blocks.Base.BaseItemBlock;
import com.extrabigsucc.PageControl.Init.InitBlocks;
import cpw.mods.fml.common.registry.GameRegistry;

public class RegisterBlocks {
    public static void Blocks() {
        GameRegistry.registerBlock(InitBlocks.testBlock, BaseItemBlock.class, "testBlock"); //No Texture
    }
}
