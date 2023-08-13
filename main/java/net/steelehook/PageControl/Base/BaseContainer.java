package net.steelehook.PageControl.Base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;

public class BaseContainer extends Container {
	
	protected final TileEntity tile;
	int posX; int posY; int posZ;
	protected EntityPlayer ep;
	protected final IInventory ii;

	public BaseContainer(EntityPlayer player, TileEntity tile) {
		this(player, tile, tile instanceof IInventory ? (IInventory)tile : null);
	}
	
	protected BaseContainer(EntityPlayer player, TileEntity te, IInventory i) {
		tile = te;
		posX = tile.xCoord;
		posY = tile.yCoord;
		posZ = tile.zCoord;
		ep = player;
		//this.detectAndSendChanges();
		ii = i;
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

}
