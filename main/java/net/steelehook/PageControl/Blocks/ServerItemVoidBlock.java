package net.steelehook.PageControl.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.steelehook.PageControl.PageControl;
import net.steelehook.PageControl.CreativeTabs.stpc170Tab;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ServerItemVoidBlock extends Block {

    public static boolean poweredState = false;

    public ServerItemVoidBlock(Material material) {
        super(material);
        this.setCreativeTab(stpc170Tab.stpcTab);
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

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
    	this.blockIcon = iconRegister.registerIcon(PageControl.MODID + ":serverItemVoidBlock");
    }


}
