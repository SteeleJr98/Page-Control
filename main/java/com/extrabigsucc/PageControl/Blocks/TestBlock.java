package com.extrabigsucc.PageControl.Blocks;

import com.extrabigsucc.PageControl.Blocks.Base.BaseBlock;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class TestBlock extends BaseBlock {
    public TestBlock(String unlocalizedName) {
        super("testBlock");
    }


    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase playerM, ItemStack itemStack) {
        if (!world.isRemote) {

            if (playerM instanceof EntityPlayer) {
                MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), "/say block placed by a player: " + ((EntityPlayer) playerM).getDisplayName());

            }
            else {
                MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), "/say block placed by not a player?");
            }
        }
    }


}
