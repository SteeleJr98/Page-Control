package net.steelehook.PageControl.Blocks.Tile;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

import net.steelehook.PageControl.Blocks.OnlineDetectorBlock;
import net.steelehook.PageControl.Handlers.ConfigHandler;
import net.steelehook.PageControl.Handlers.ServerLogging;

import net.steelehook.SteeleCore.Base.Blocks.BaseTileEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OnlineDetectorTileEntity extends net.steelehook.SteeleCore.Base.Blocks.BaseTileEntity {

    public String playerName = "";
    private int tickCount = 0;

    private static final int toDiv = ConfigHandler.checkTimer;




    @Override
    public void updateEntity() {

        if (worldObj.isRemote) { return; }

        if (tickCount >= 20 * toDiv) {

            List<String> playerList = new ArrayList<String>(Arrays.asList(MinecraftServer.getServer().getConfigurationManager().getAllUsernames()));

            //List<String> playerList = new MinecraftServer.getServer().getConfigurationManager().getAllUsernames();



            int blockX = this.xCoord;
            int blockY = this.yCoord;
            int blockZ = this.zCoord;
            
            Block block = worldObj.getBlock(blockX, blockY, blockZ);
            
            if (block instanceof OnlineDetectorBlock) {
            	OnlineDetectorBlock.updateBlockPower(worldObj, blockX, blockY, blockZ, playerList.contains(playerName));
            }
            

            //OnlineDetectorBlock.shouldProvidePower = playerList.contains(playerName);
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
