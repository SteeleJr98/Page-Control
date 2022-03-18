package net.steelehook.pagecontrol.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.steelehook.pagecontrol.util.CommandHandling;

public class CreativeItemVoidBlock extends ItemVoidBlock {

	public CreativeItemVoidBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult raytrace) {
		if (!world.isClientSide) {
			delItems(world, pos);			
		}
		
		return ActionResultType.sidedSuccess(world.isClientSide);
	}
	
	@Override
	public void delItems(World world, BlockPos pos) {
		CommandHandling.killCommand("@e[type=minecraft:item]");
	}
}
