package com.extrabigsucc.PageControl.Blocks;

import com.extrabigsucc.PageControl.Blocks.Base.BaseBlock;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import com.extrabigsucc.PageControl.Handlers.ServerLogging;

public class TestBlock extends BaseBlock {
    public TestBlock(String unlocalizedName) {
        super("testBlock");
    }


    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase playerM, ItemStack itemStack) {
        if (!world.isRemote) {

            if (playerM instanceof EntityPlayer) {
                //MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), "/say " + (world.getBlock(x, y, z)).getUnlocalizedName() + "placed by a player: " + ((EntityPlayer) playerM).getDisplayName());
                ServerLogging.sendMessageFromServer((world.getBlock(x, y, z)).getLocalizedName() + " placed by: " + ((EntityPlayer) playerM).getDisplayName());

            }
            else {
                //MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), "/say block placed by not a player?");
                ServerLogging.sendMessageFromServer("block placed but not by player?");
            }
        }
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int m1) {

        if (!world.isRemote) {
            //MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), "/say Block placed at: " + x + " " + y + " " + z);
            ServerLogging.sendMessageFromServer("Block placed at: " + x + " " + y + " " + z);

        }
        return 0;
    }


}
