package com.extrabigsucc.PageControl.Blocks.Base;

import com.extrabigsucc.PageControl.Blocks.TeTestBlock;
import net.minecraft.tileentity.TileEntity;

public class BaseTileEntity extends TileEntity {

    public void finishUpdate() {
        BaseBlock myBlock = (BaseBlock)(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
        worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, myBlock);
        markDirty();
    }

}
