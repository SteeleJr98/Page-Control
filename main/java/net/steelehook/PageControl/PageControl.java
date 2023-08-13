package net.steelehook.PageControl;

import org.omg.CORBA.PRIVATE_MEMBER;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.handshake.FMLHandshakeMessage.ModList;
import cpw.mods.fml.relauncher.ModListHelper;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.steelehook.PageControl.Handlers.ConfigHandler;
import net.steelehook.PageControl.Handlers.GuiHandler;
import net.steelehook.PageControl.Handlers.SpawnStopHandling;
import net.steelehook.PageControl.Network.PCPacketHandler;
import net.steelehook.PageControl.Proxy.IProxy;
import net.steelehook.PageControl.Register.RegisterBlocks;
import net.steelehook.PageControl.Register.Recipes.BlockCrafting;
import scala.tools.nsc.doc.model.Public;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(name = PageControl.NAME, modid = PageControl.MODID, acceptedMinecraftVersions = "1.7.10", dependencies = PageControl.DEPS, canBeDeactivated = false)

public class PageControl
{
    public static final String NAME = "Page Control";
    public static final String MODID = "stpc170";
    //public static final String VERSION = "1.0.3"; //version taken from mcmod.info
    public static final String DEPS = "";

    public static final String CLIENT_PROXY_CLASS = "net.steelehook.PageControl.Proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "net.steelehook.PageControl.Proxy.ServerProxy";

    @Mod.Instance(PageControl.MODID)
    public static PageControl instance = new PageControl();
    
    private static int modGuiIndex = 0;
    public static final int GUI_ONLINEBLOCK = modGuiIndex++;

    @SidedProxy(clientSide = PageControl.CLIENT_PROXY_CLASS, serverSide = PageControl.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	PCPacketHandler.init();
    	
        String configDir = event.getModConfigurationDirectory().toString();
        ConfigHandler.init(configDir);
        FMLCommonHandler.instance().bus().register(new ConfigHandler());
        MinecraftForge.EVENT_BUS.register(new SpawnStopHandling());

        RegisterBlocks.Blocks();
        RegisterBlocks.TileEntities();

        BlockCrafting.Shaped();
        BlockCrafting.Shapeless();
        
//        String[] testStrings = ConfigHandler.testString;
//        for (Object object : testStrings) {
//        	System.out.println(object.toString());
//        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
