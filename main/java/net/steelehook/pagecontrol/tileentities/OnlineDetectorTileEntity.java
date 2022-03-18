package net.steelehook.pagecontrol.tileentities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.steelehook.pagecontrol.blocks.OnlineDetectorBlock;
import net.steelehook.pagecontrol.setup.ModTileEntityTypes;

public class OnlineDetectorTileEntity extends TileEntity implements ITickableTileEntity{

	public String playerNameString = "";
	private int tickCount = 0;
	
	public OnlineDetectorTileEntity() {
		super(ModTileEntityTypes.ONLINE_DETECTOR_TILE.get());
	}

	@Override
	public void tick() {
		if (this.level.isClientSide) { return; }
		
		if (tickCount >= 20) {
			List<String> playerList = new ArrayList<String>(Arrays.asList(ServerLifecycleHooks.getCurrentServer().getPlayerNames()));
			
			BlockState state = this.getBlockState();
			Block block = state.getBlock();
			
			if (block instanceof OnlineDetectorBlock) {
				OnlineDetectorBlock.updatePowerOutput(state, level, worldPosition, playerList.contains(playerNameString));
			}
			tickCount = 0;
		}
		tickCount++;
	}
}
