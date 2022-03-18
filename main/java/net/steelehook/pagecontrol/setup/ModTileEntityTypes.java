package net.steelehook.pagecontrol.setup;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.steelehook.pagecontrol.tileentities.OnlineDetectorTileEntity;

public class ModTileEntityTypes {
	
	//TILE ENTITIES
	public static final RegistryObject<TileEntityType<OnlineDetectorTileEntity>> ONLINE_DETECTOR_TILE = register("online_detector_tile", OnlineDetectorTileEntity::new, ModBlocks.ONLINE_DETECTOR_BLOCK);

			
	//TILE ENTITY REGISTRATION FUNCTIONS
	private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<T> factory, RegistryObject<? extends Block> block) {
		return Registration.TILE_ENTITIES.register(name, () -> {
			return TileEntityType.Builder.of(factory, block.get()).build(null);
		});
	}
	
	static void register() {}
}
