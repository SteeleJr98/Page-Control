package com.extrabigsucc.PageControl.Blocks.Tile;

import com.extrabigsucc.PageControl.Blocks.TeTestBlock;
import com.extrabigsucc.PageControl.Handlers.ServerLogging;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

import java.util.Arrays;

public class TeTestTileEntity extends TileEntity {

    public String playerName = "";
    private int tickCount = 0;

    @Override
    public void updateEntity() {


        if (worldObj.isRemote) { return; }

        tickCount++;
        if (tickCount >= 100) {
            String [] playerList = MinecraftServer.getServer().getConfigurationManager().getAllUsernames();
            if (Arrays.asList(playerList).contains(playerName)) {
                ServerLogging.sendMessageFromServer("Owner Online");
                TeTestBlock.shouldProvidePower = true;
            }
            else {
                ServerLogging.sendMessageFromServer("Owner Offline");
                TeTestBlock.shouldProvidePower = false;
            }
            finishUpdate();

            //run player check
            tickCount = 0;
        }




//        TeTestBlock myBlock = (TeTestBlock)(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
//
//        worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, myBlock);
//
//        markDirty();
    }

    public void finishUpdate() {
        TeTestBlock myBlock = (TeTestBlock)(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
        worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, myBlock);
        markDirty();
    }

    public TeTestTileEntity() {

    }



    public void writeToNBT(NBTTagCompound pName) {
        super.writeToNBT(pName);
        pName.setString("playerName", playerName);
    }

    public void readFromNBT(NBTTagCompound pName) {
        super.readFromNBT(pName);
        this.playerName = pName.getString("playerName");
    }

}
