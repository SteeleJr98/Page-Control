package net.steelehook.PageControl.Handlers;

import java.lang.reflect.Array;
import java.util.Arrays;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import scala.tools.nsc.interactive.CompilerControl.AskToDoFirstItem;

public class SpawnStopHandling {
	
	private static final String[] blackList = ConfigHandler.testString; 
	
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityItem) {
			if (!event.world.isRemote) {
				EntityItem item = (EntityItem) event.entity;
				//ServerLogging.sendMessageFromServer(item.getEntityItem().getItem().getUnlocalizedNameInefficiently(item.getEntityItem()));
				//ServerLogging.sendMessageFromServer(item.getEntityItem().getItem().getUnlocalizedName());
				//ServerLogging.sendMessageFromServer(item.getEntityItem().getDisplayName());
				//-->ServerLogging.sendMessageFromServer(item.getEntityItem().getUnlocalizedName());
				//ServerLogging.sendMessageFromServer(item.getEntityItem().getItem().getItemStackDisplayName(item.getEntityItem()));
				
				String itemNameString = item.getEntityItem().getUnlocalizedName();

				if (itemNameString.contains(":")) {
					String[] nameList = itemNameString.split(":");
					ServerLogging.sendMessageFromServer(nameList[1]);
					for (String s : blackList) {
						if (s.equals(nameList[1])) {
							event.setCanceled(true);
							ServerLogging.sendMessageFromServer("Event cancelled");
						}
					}
				}
				
				
				
			}
		}
	}

}
