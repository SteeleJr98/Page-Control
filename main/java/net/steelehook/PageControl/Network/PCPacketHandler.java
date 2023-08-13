package net.steelehook.PageControl.Network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PCPacketHandler {
	public static final SimpleNetworkWrapper packetWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("pc170");
	
	public static void init() {
		packetWrapper.registerMessage(PCBlockUpdater.Handler.class, PCBlockUpdater.class, 0, Side.SERVER);
	}
}
