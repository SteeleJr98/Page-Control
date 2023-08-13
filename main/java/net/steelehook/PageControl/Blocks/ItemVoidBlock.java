package net.steelehook.PageControl.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.steelehook.PageControl.PageControl;
import net.steelehook.PageControl.CreativeTabs.stpc170Tab;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemVoidBlock extends Block {

    public static int counter = 0;
    public static boolean poweredState = false;


    public ItemVoidBlock(Material material) {
        super(material);
        this.setCreativeTab(stpc170Tab.stpcTab);
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int sideM, float aX, float aY, float aZ) {

        if(!world.isRemote) {

            delItemList(world, x, y, z);

            //ServerLogging.sendMessageFromServer("Powered?: " + checkIfPowered(world, x, y, z));

        }
        return true;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!world.isRemote) {

            if (checkIfPowered(world, x, y, z) && !poweredState) {
                //ServerLogging.sendMessageFromServer("Block has been powered");
                delItemList(world, x, y, z);
                poweredState = true;

            }

            if (!checkIfPowered(world, x, y, z) && poweredState) {
                //ServerLogging.sendMessageFromServer("Block has been unpowered");
                poweredState = false;
            }
        }
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }


    public boolean checkIfPowered(World world, int x, int y, int z) {
        return world.isBlockIndirectlyGettingPowered(x, y, z);
    }

    public void delItemList(World world, int x, int y, int z) {

        AxisAlignedBB box = AxisAlignedBB.getBoundingBox(x - 16, y - 5, z - 16, x + 16, y + 5, z + 16);

        List list = world.getEntitiesWithinAABB(EntityItem.class, box);

        //ServerLogging.sendMessageFromServer(String.valueOf(list));

        for (Object o : list) {
            EntityItem tempE = (EntityItem) o;
            tempE.setDead();
        }

    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
    	this.blockIcon = iconRegister.registerIcon(PageControl.MODID + ":itemVoidBlock");
    }





}
