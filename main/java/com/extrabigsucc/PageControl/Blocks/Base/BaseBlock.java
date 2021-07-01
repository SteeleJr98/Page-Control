package com.extrabigsucc.PageControl.Blocks.Base;

import com.extrabigsucc.PageControl.CreativeTabs.stpc170Tab;
import com.extrabigsucc.PageControl.PageControl;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BaseBlock extends Block {
    public BaseBlock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        this.setBlockName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setCreativeTab(stpc170Tab.stpcTab);
    }

    public BaseBlock(String unlocalizedName) {
        this(unlocalizedName, Material.rock, 3F, 5F);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s%s", PageControl.MODID.toLowerCase() + ":", getUnwrappedLocalizedNAme(super.getUnlocalizedName()));
    }

    protected String getUnwrappedLocalizedNAme(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedLocalizedNAme(this.getUnlocalizedName())));
    }
}
