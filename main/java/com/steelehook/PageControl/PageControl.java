package com.steelehook.PageControl;

import com.steelehook.PageControl.Handlers.ConfigHandler;
import com.steelehook.PageControl.Proxy.IProxy;
import com.steelehook.PageControl.Register.Recipes.BlockCrafting;
import com.steelehook.PageControl.Register.RegisterBlocks;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(name = PageControl.NAME, modid = PageControl.MODID, version = PageControl.VERSION, acceptedMinecraftVersions = "1.7.10", dependencies = PageControl.DEPS, canBeDeactivated = false)

public class PageControl
{
    public static final String NAME = "Page Control";
    public static final String MODID = "stpc170";
    public static final String VERSION = "1.0.0";
    public static final String DEPS = "required-after:Forge@[10.13.4.1614,)";

    public static final String CLIENT_PROXY_CLASS = "com.steelehook.PageControl.Proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "com.steelehook.PageControl.Proxy.ServerProxy";

    @Mod.Instance(PageControl.MODID)
    public static PageControl instance = new PageControl();

    @SidedProxy(clientSide = PageControl.CLIENT_PROXY_CLASS, serverSide = PageControl.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @EventHandler
    public void init(FMLPreInitializationEvent event) {
        String configDir = event.getModConfigurationDirectory().toString();
        ConfigHandler.init(configDir);
        FMLCommonHandler.instance().bus().register(new ConfigHandler());

        RegisterBlocks.Blocks();
        RegisterBlocks.TileEntities();

        BlockCrafting.Shaped();
        BlockCrafting.Shapeless();

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLPostInitializationEvent event) {

    }
}
