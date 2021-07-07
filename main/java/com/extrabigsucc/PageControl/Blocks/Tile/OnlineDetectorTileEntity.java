package com.extrabigsucc.PageControl.Blocks.Tile;

import com.extrabigsucc.PageControl.Blocks.Base.BaseTileEntity;
import com.extrabigsucc.PageControl.Blocks.OnlineDetectorBlock;
import com.extrabigsucc.PageControl.Handlers.ServerLogging;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

import java.util.Arrays;

public class OnlineDetectorTileEntity extends BaseTileEntity {

    public String playerName = "";
    private int tickCount = 0;

    @Override
    public void updateEntity() {

        if (worldObj.isRemote) { return; }

        if (tickCount >= 100) {
            String [] playerList = MinecraftServer.getServer().getConfigurationManager().getAllUsernames();
            OnlineDetectorBlock.shouldProvidePower = Arrays.asList(playerList).contains(playerName);
            //ServerLogging.sendMessageFromServer("Checked");
            finishUpdate();
            tickCount = 0;
        }
        tickCount++;
    }

    public OnlineDetectorTileEntity() {

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
