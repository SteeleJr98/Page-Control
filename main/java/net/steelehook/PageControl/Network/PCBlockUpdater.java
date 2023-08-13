package net.steelehook.PageControl.Network;

import java.util.List;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.steelehook.PageControl.Blocks.OnlineDetectorBlock.TestTE;

public class PCBlockUpdater implements IMessage {

	private int xCoord;
	private int yCoord;
	private int zCoord;
	private String playerString;
	
	public PCBlockUpdater() {}
	
	public PCBlockUpdater(int xCoord, int yCoord, int zCoord, String playerString) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
		this.playerString = playerString;
	}
	
	@Override
    public void fromBytes(ByteBuf buf)
    {
        xCoord = buf.readInt();
        yCoord = buf.readInt();
        zCoord = buf.readInt();
        playerString = ByteBufUtils.readUTF8String(buf);
    }
 
    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(xCoord);
        buf.writeInt(yCoord);
        buf.writeInt(zCoord);
        ByteBufUtils.writeUTF8String(buf, playerString);
    }
    
    public static class Handler implements IMessageHandler<PCBlockUpdater, IMessage> {

		@Override
		public IMessage onMessage(PCBlockUpdater message, MessageContext ctx) {
			if (ctx.side == Side.SERVER) {
				TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.xCoord, message.yCoord, message.zCoord);
				if (tile instanceof TestTE) {
					TestTE te = (TestTE) tile;
					te.doNameLogic(message.playerString);
				}
				ctx.getServerHandler().playerEntity.worldObj.markBlockForUpdate(message.xCoord, message.yCoord, message.zCoord);
			}
			return null;
		}
    	
    }
	
}
