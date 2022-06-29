package net.steelehook.pagecontrol.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.steelehook.pagecontrol.util.CommandHandling;

public class ItemVoidBlock extends Block {

	private static final BooleanProperty isPowered = RedstoneTorchBlock.LIT;
	
	public ItemVoidBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(isPowered, Boolean.valueOf(false)));
	}
	
	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult raytrace) {
		if (!world.isClientSide) {
			delItems(world, pos);
		}
		
		return ActionResultType.sidedSuccess(world.isClientSide);
	}
	
	public void delItems(World world, BlockPos pos) {
		CommandHandling.executeCommand("positioned", String.valueOf(pos.getX()) + " " + String.valueOf(pos.getY()) + " "  + String.valueOf(pos.getZ()), "kill @e[distance=..16,type=minecraft:item]");
	}
	
	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}
	
	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos pos2, boolean bool) {
		if (!world.isClientSide) {
			if (checkIfPowered(world, pos) && !state.getValue(isPowered)) {
	            delItems(world, pos);
	        	world.setBlock(pos, state.cycle(isPowered), 2);
	        }
	        if (!checkIfPowered(world, pos) && state.getValue(isPowered)) {
	            world.setBlock(pos, state.cycle(isPowered), 2);
	        }
		}
	}
	
	public boolean checkIfPowered(World world, BlockPos pos) {
		return world.hasNeighborSignal(pos);
	}
	
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
	      p_206840_1_.add(isPowered);
	}
}
