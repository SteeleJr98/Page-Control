package net.steelehook.PageControl.Handlers;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.steelehook.PageControl.PageControl;
import net.steelehook.PageControl.Base.BaseContainer;
import net.steelehook.PageControl.Blocks.OnlineDetectorBlock.TestTE;
import net.steelehook.PageControl.Blocks.Tile.OnlineDetectorTileEntity;
import net.steelehook.PageControl.Gui.Screen.GuiOnlineDetector;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == PageControl.GUI_ONLINEBLOCK) {
			//MessageLogging.sendFromClient(player, "test2");
			//return new GuiOnlineDetector(player, (OnlineDetectorTileEntity)world.getTileEntity(x, y, z));
			return new BaseContainer(player, (TestTE)world.getTileEntity(x, y, z));
		}
		
		return null;
		
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == PageControl.GUI_ONLINEBLOCK) {
			//MessageLogging.sendFromClient(player, "test");
			//MessageLogging.sendFromClient(player, String.valueOf(world.getTileEntity(x, y, z) instanceof TestTE));
			return new GuiOnlineDetector(player, (TestTE)world.getTileEntity(x, y, z));
		}
		else {
			return null;
		}
	}
}
