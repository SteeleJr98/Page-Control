package com.steelehook.PageControl.Blocks;

import com.steelehook.PageControl.Blocks.Base.BaseBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.List;

public class ServerItemVoidBlock extends BaseBlock {

    public static boolean poweredState = false;

    public ServerItemVoidBlock(String unlocalizedName) {
        super("serverItemVoidBlock");
    }


    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float aX, float aY, float aZ) {
        if (!world.isRemote) {
            delItemWorld(world);
        }
        return true;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!world.isRemote) {

            if (checkIfPowered(world, x, y, z) && !poweredState) {
                delItemWorld(world);
                poweredState = true;

            }

            if (!checkIfPowered(world, x, y, z) && poweredState) {
                poweredState = false;
            }
        }
    }

    public boolean checkIfPowered(World world, int x, int y, int z) {
        return world.isBlockIndirectlyGettingPowered(x, y, z);
    }

    public void delItemWorld(World world) {
        List list = world.loadedEntityList;
        //ServerLogging.sendMessageFromServer(String.valueOf(list));
        for (Object o : list) {
            if (o instanceof EntityItem) {
                EntityItem tempE = (EntityItem) o;
                tempE.setDead();
            }
        }
    }



}
