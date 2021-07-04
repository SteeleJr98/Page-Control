package com.extrabigsucc.PageControl.Init;

import com.extrabigsucc.PageControl.Blocks.Base.BaseBlock;
import com.extrabigsucc.PageControl.Blocks.Base.BaseTileEntity;
import com.extrabigsucc.PageControl.Blocks.OnlineDetectorBlock;
import com.extrabigsucc.PageControl.Blocks.TeTestBlock;
import com.extrabigsucc.PageControl.Blocks.TestBlock;
import com.extrabigsucc.PageControl.Blocks.Tile.TeTestTileEntity;

public class InitBlocks {
    public static final BaseBlock testBlock = new TestBlock("testBlock");
    public static final BaseBlock teTestBlock = new TeTestBlock("teTestBlock");

    public static final BaseBlock onlineDetectorBlock = new OnlineDetectorBlock("onlineDetectorBlock");
}
