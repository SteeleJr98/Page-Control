package com.steelehook.PageControl.Init;

import com.steelehook.PageControl.Blocks.*;
import com.steelehook.PageControl.Blocks.Base.BaseBlock;

public class InitBlocks {
    public static final BaseBlock testBlock = new TestBlock("testBlock");
    public static final BaseBlock teTestBlock = new TeTestBlock("teTestBlock");

    public static final BaseBlock onlineDetectorBlock = new OnlineDetectorBlock("onlineDetectorBlock");
    public static final BaseBlock itemVoidBlock = new ItemVoidBlock("itemVoidBlock");
    public static final BaseBlock serverItemVoidBlock = new ServerItemVoidBlock("serverItemVoidBlock");
}
