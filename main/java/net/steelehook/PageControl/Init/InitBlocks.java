package net.steelehook.PageControl.Init;

import net.steelehook.PageControl.PageControl;
import net.steelehook.PageControl.Blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InitBlocks {
    public static Block onlineDetectorBlock = new OnlineDetectorBlock(Material.rock).setBlockName(PageControl.MODID + ":onlineDetectorBlock");
    public static Block itemVoidBlock = new ItemVoidBlock(Material.rock).setBlockName(PageControl.MODID + ":itemVoidBlock");
    public static Block serverItemVoidBlock = new ServerItemVoidBlock(Material.rock).setBlockName(PageControl.MODID + ":serverItemVoidBlock");
}
