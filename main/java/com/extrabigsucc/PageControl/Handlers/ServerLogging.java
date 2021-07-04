package com.extrabigsucc.PageControl.Handlers;

import net.minecraft.server.MinecraftServer;

public class ServerLogging {

    public static void sendMessageFromServer(String msg){
        MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), "/say " + msg);
    }

}
