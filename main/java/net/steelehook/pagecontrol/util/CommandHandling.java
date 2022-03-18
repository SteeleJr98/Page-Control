package net.steelehook.pagecontrol.util;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;

public class CommandHandling {
	
	public static void executeCommand(String syntax, String locOrEnt, String command) {
		MinecraftServer source = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
		source.getCommands().performCommand(source.createCommandSourceStack(), "execute " + syntax + " " + locOrEnt + " " + "run " + command);
	}
	
	public static void killCommand(String syntax) {
		MinecraftServer source = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
		source.getCommands().performCommand(source.createCommandSourceStack(), "kill " + syntax);
	}

}
