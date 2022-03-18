package net.steelehook.pagecontrol.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.steelehook.pagecontrol.setup.ModTileEntityTypes;
import net.steelehook.pagecontrol.tileentities.OnlineDetectorTileEntity;

public class OnlineDetectorBlock extends Block {
	
	public static final IntegerProperty power = BlockStateProperties.POWER;

	public OnlineDetectorBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(power, Integer.valueOf(0)));
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.ONLINE_DETECTOR_TILE.get().create();
	}
	
	@Override
	public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity player, ItemStack item) {
		if (world.isClientSide) {
			return;
		} else {
			if (player instanceof PlayerEntity) {
				OnlineDetectorTileEntity te = (OnlineDetectorTileEntity) world.getBlockEntity(pos);
				te.playerNameString = player.getDisplayName().getString();
			}
		}
	}
	
	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}
	
	@Override
	public int getSignal(BlockState state, IBlockReader world, BlockPos pos, Direction dir) {
		return state.getValue(power);
	}
	
	public static void updatePowerOutput(BlockState state, World world, BlockPos pos, boolean playerHere) {
		world.setBlock(pos, state.setValue(power, Integer.valueOf(playerHere ? 15 : 3)), 3);
	}
	
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
		p_206840_1_.add(power);
	}

}
