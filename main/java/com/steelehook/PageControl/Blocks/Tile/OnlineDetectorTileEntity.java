package com.steelehook.PageControl.Blocks.Tile;

import com.steelehook.PageControl.Blocks.Base.BaseTileEntity;
import com.steelehook.PageControl.Blocks.OnlineDetectorBlock;
import com.steelehook.PageControl.Handlers.ConfigHandler;
import com.steelehook.PageControl.Handlers.ServerLogging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnlineDetectorTileEntity extends BaseTileEntity {

    public String playerName = "";
    private int tickCount = 0;

    private static final int toDiv = ConfigHandler.checkTimer;




    @Override
    public void updateEntity() {

        if (worldObj.isRemote) { return; }

        if (tickCount >= 20 * toDiv) {

            List<String> playerList = new ArrayList<String>(Arrays.asList(MinecraftServer.getServer().getConfigurationManager().getAllUsernames()));

            //List<String> playerList = new MinecraftServer.getServer().getConfigurationManager().getAllUsernames();




            

            OnlineDetectorBlock.shouldProvidePower = playerList.contains(playerName);
            //ServerLogging.sendMessageFromServer("Checked");
            //ServerLogging.sendMessageFromServer(String.valueOf(Arrays.asList(uuidList).contains(playerUUID)));
            finishUpdate();
            tickCount = 0;

            //ServerLogging.sendMessageFromServer("Looking for player: " + playerName);

        }

        tickCount++;
        //ServerLogging.sendMessageFromServer(String.valueOf(tickCount));
    }

    public OnlineDetectorTileEntity() {

    }

    public void writeToNBT(NBTTagCompound pName) {
        super.writeToNBT(pName);
        pName.setString("playerName", playerName);

        markDirty();
        //ServerLogging.sendMessageFromServer("Marked Dirty");

    }

    public void readFromNBT(NBTTagCompound pName) {
        super.readFromNBT(pName);
        this.playerName = pName.getString("playerName");
    }





}
