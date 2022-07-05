package net.steelehook.PageControl.Init;

import net.steelehook.PageControl.PageControl;
import net.steelehook.PageControl.Blocks.*;
import net.steelehook.SteeleCore.Base.Blocks.BaseBlock;

public class InitBlocks {
    public static final BaseBlock onlineDetectorBlock = new OnlineDetectorBlock("onlineDetectorBlock", PageControl.MODID);
    public static final BaseBlock itemVoidBlock = new ItemVoidBlock("itemVoidBlock", PageControl.MODID);
    public static final BaseBlock serverItemVoidBlock = new ServerItemVoidBlock("serverItemVoidBlock", PageControl.MODID);
}
