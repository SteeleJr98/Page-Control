package net.steelehook.PageControl.Handlers;

import java.lang.reflect.Array;
import java.util.Arrays;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class SpawnStopHandling {
	
	private static final String[] blackList = ConfigHandler.testString; 
	
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityItem) {
			if (!event.world.isRemote) {
				EntityItem item = (EntityItem) event.entity;
				ServerLogging.sendMessageFromServer(item.getEntityItem().toString());
				
			}
		}
	}

}
