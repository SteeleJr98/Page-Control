package net.steelehook.pagecontrol.setup;

import java.util.function.Supplier;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.steelehook.pagecontrol.blocks.ItemVoidBlock;
import net.steelehook.pagecontrol.blocks.OnlineDetectorBlock;

public class ModBlocks {
	
	//BLOCKS
	public static final RegistryObject<OnlineDetectorBlock> ONLINE_DETECTOR_BLOCK = register("online_detector_block", () ->
			new OnlineDetectorBlock(AbstractBlock.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL)));
	
	public static final RegistryObject<ItemVoidBlock> ITEM_VOID_BLOCK = register("item_void_block", () ->
			new ItemVoidBlock(AbstractBlock.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL)));
	
	public static final RegistryObject<ItemVoidBlock> CREATIVE_ITEM_VOID_BLOCK = register("creative_item_void_block", () ->
			new ItemVoidBlock(AbstractBlock.Properties.of(Material.METAL).strength(-1.0F, 3600000.0F).noDrops().sound(SoundType.METAL)));

	
	
	
	//BLOCK REGISTRATION FUNCTIONS
	public static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
		return Registration.BLOCKS.register(name, block);
	}
	
	public static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
		RegistryObject<T> ret = registerNoItem(name, block);
		Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(ItemGroup.TAB_REDSTONE)));
		return ret;
	}
	
	static void register() {}
}
