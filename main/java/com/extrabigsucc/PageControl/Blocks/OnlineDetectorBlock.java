package com.extrabigsucc.PageControl.Blocks;

import com.extrabigsucc.PageControl.Blocks.Base.BaseBlock;
import com.extrabigsucc.PageControl.Blocks.Tile.OnlineDetectorTileEntity;
import com.extrabigsucc.PageControl.Blocks.Tile.TeTestTileEntity;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class OnlineDetectorBlock extends BaseBlock implements ITileEntityProvider {

    public static boolean shouldProvidePower = false;

    public OnlineDetectorBlock(String unlocalizedName) {
        super("onlineDetectorBlock");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new OnlineDetectorTileEntity();
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        OnlineDetectorTileEntity tile = (OnlineDetectorTileEntity)world.getTileEntity(x, y, z);
        return meta;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase playerM, ItemStack itemStack) {
        OnlineDetectorTileEntity tile = (OnlineDetectorTileEntity)world.getTileEntity(x, y, z);

        if (!world.isRemote) {
            if (playerM instanceof EntityPlayer) {
                tile.playerName = ((EntityPlayer) playerM).getDisplayName();
            }
        }

    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return canProvidePower();
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
    public boolean isSideSolid(IBlockAccess iba, int x, int y, int z, ForgeDirection side) {
        return true;
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }




}
