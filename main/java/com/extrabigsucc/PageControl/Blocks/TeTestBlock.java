package com.extrabigsucc.PageControl.Blocks;

import com.extrabigsucc.PageControl.Blocks.Base.BaseBlock;
import com.extrabigsucc.PageControl.Blocks.Tile.TeTestTileEntity;
import com.extrabigsucc.PageControl.Handlers.ServerLogging;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;


public class TeTestBlock extends BaseBlock implements ITileEntityProvider {

    private Class tte;

    public static boolean shouldProvidePower = false;


    public TeTestBlock(String unlocalizedName) {
        super("tileEntityTestBlock");

    }



    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TeTestTileEntity();
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {

        TeTestTileEntity tile = (TeTestTileEntity)world.getTileEntity(x, y, z);



        return meta;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase playerM, ItemStack itemStack) {

        TeTestTileEntity tile = (TeTestTileEntity)world.getTileEntity(x, y, z);



        if (!world.isRemote) {

            if (playerM instanceof EntityPlayer) {
                ServerLogging.sendMessageFromServer((world.getBlock(x, y, z)).getLocalizedName() + " placed by: " + ((EntityPlayer) playerM).getDisplayName());
                tile.playerName = ((EntityPlayer) playerM).getDisplayName();
            }
            else {
                ServerLogging.sendMessageFromServer("block placed but not by player?");
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int sideM, float aX, float aY, float aZ) {

        TeTestTileEntity tile = (TeTestTileEntity)world.getTileEntity(x, y, z);

        String toLog = tile.playerName;
        if (!world.isRemote) {
            ServerLogging.sendMessageFromServer("Block Activated And Originally placed by: " + toLog);
            //ServerLogging.sendMessageFromServer("shouldProvidePower: " + shouldProvidePower);
            //shouldProvidePower = !shouldProvidePower;

            String[] playerList = MinecraftServer.getServer().getConfigurationManager().getAllUsernames();

            if (Arrays.asList(playerList).contains(tile.playerName)) {
                ServerLogging.sendMessageFromServer("Owner Online");
            }
            else {
                ServerLogging.sendMessageFromServer("Owner Offline");
            }

            //tile.updateEntity();



        }







        return true;
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess iba, int x, int y, int z, int side) {



        if (!shouldProvidePower) {
            return 0;
        }
        else {
            return 15;
        }
    }



    @Override
    public boolean hasTileEntity() {
        return true;
    }
}
