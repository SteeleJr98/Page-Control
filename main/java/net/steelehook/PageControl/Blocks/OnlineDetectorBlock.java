package net.steelehook.PageControl.Blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.steelehook.PageControl.PageControl;
import net.steelehook.PageControl.Blocks.Tile.OnlineDetectorTileEntity;
import net.steelehook.PageControl.CreativeTabs.stpc170Tab;
import net.steelehook.PageControl.Handlers.ConfigHandler;
import net.steelehook.PageControl.Handlers.ServerLogging;

public class OnlineDetectorBlock extends Block {

    public static boolean shouldProvidePower = false;

    public OnlineDetectorBlock(Material material) {
        super(material);
        this.setCreativeTab(stpc170Tab.stpcTab);
    }

    @Override
    public TileEntity createTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TestTE();
    }
    
    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
    	TestTE tile = (TestTE)world.getTileEntity(x, y, z);
        return meta;
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float aX, float aY, float aZ) {

    	player.openGui(PageControl.instance, PageControl.GUI_ONLINEBLOCK, world, x, y, z);
    	
    	
    	return true;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase playerM, ItemStack itemStack) {
    	
    	TestTE tile = (TestTE)world.getTileEntity(x, y, z);

    	
		if (playerM instanceof EntityPlayer) {
        	//ServerLogging.sendMessageFromServer("placer is player");
        	//ServerLogging.sendMessageFromServer("is there a tile: " + String.valueOf(tile));
            //tile.playerName = String.valueOf(((EntityPlayer) playerM).getDisplayName());

            EntityPlayer player = (EntityPlayer) playerM;

            makeNBTCompound(tile, player);

            
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
        if (!((iba.getBlockMetadata(x, y, z)) == 1)) {
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

    
    public static void updateBlockPower(World world, int x, int y, int z, boolean playerIn) {
    	
    	int meta = playerIn ? 1 : 0;
    	
    	world.setBlockMetadataWithNotify(x, y, z, meta, meta);
    }

    public void makeNBTCompound(TestTE tile, EntityPlayer player) {
        NBTTagCompound pName = new NBTTagCompound();
        tile.playerNames.add(player.getDisplayName());
        tile.writeToNBT(pName);
    }

    public static class TestTE extends TileEntity {
    	
    	private static final int toDiv = ConfigHandler.checkTimer;
	    private int tickCount = 20 * toDiv;
	    //public String playerName;
	    public ArrayList<String> playerNames = new ArrayList();

	    

	    @Override
	    public boolean canUpdate() {
	    	return true;
	    }

	    @Override
	    public void updateEntity() {

	        if (worldObj.isRemote) 
	        	return;

	        if (tickCount >= 20 * toDiv) {


	            //List<String> playerList = new MinecraftServer.getServer().getConfigurationManager().getAllUsernames();

	            //MessageLogging.sendFromServer("from te: " + playerName);
	            //MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), "/say " + "beep");
	            

	            int blockX = this.xCoord;
	            int blockY = this.yCoord;
	            int blockZ = this.zCoord;
	            
	            Block block = worldObj.getBlock(blockX, blockY, blockZ);
	            
	            
	            if (block instanceof OnlineDetectorBlock) {
	            	//ServerLogging.sendMessageFromServer("Does this return a name: " + playerName);
	            	
	            	OnlineDetectorBlock.updateBlockPower(worldObj, blockX, blockY, blockZ, checkNamesVsPlayers());
	            	
	            }
	            

	            //OnlineDetectorBlock.shouldProvidePower = playerList.contains(playerName);
	            //ServerLogging.sendMessageFromServer("Checked");
	            //ServerLogging.sendMessageFromServer(String.valueOf(Arrays.asList(uuidList).contains(playerUUID)));
	            finishUpdate();
	            tickCount = 0;

	            //ServerLogging.sendMessageFromServer("Looking for player: " + playerName);

	        }

	        tickCount++;
	        //ServerLogging.sendMessageFromServer(String.valueOf(tickCount));
	    }

//	    public OnlineDetectorTileEntity() {
//
//	    }
	    
	    @Override
	    public void writeToNBT(NBTTagCompound pName) {
	        super.writeToNBT(pName);
	        NBTTagList playerList = new NBTTagList();
	        for (String nameString : playerNames) {
	        	playerList.appendTag(new NBTTagString(nameString));
	        }
	        //pName.setString("playerName", playerName);
	        pName.setTag("playerList", playerList);

	        markDirty();
	        //ServerLogging.sendMessageFromServer("Marked Dirty");

	    }

	    @Override
	    public void readFromNBT(NBTTagCompound pName) {
	        super.readFromNBT(pName);
	        //playerName = pName.getString("playerName");
	        NBTTagList playerList = (NBTTagList)pName.getTag("playerList");
	        
	        for (int i = 0; i < playerList.tagCount(); i++) {
	        	playerNames.add(playerList.getStringTagAt(i));
	        }
	    }

	    public void finishUpdate() {
			Block myBlock = (Block)(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
			worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, myBlock);
			markDirty();
		}

//		public String getPlayerName() {
//			return playerName;
//		}
	    
	    public void doNameLogic(String name) {
	    	if (!worldObj.isRemote) {
		    	if (!playerNames.contains(name)) {
		    		playerNames.add(name);
				}
				else {
					int playerIndex = playerNames.indexOf(name);
					playerNames.remove(playerIndex);
				}
		    	//ServerLogging.sendMessageFromServer("did player logic");
		    	markDirty();
	    	}
	    }
	    
	    private boolean checkNamesVsPlayers() {
	    	ArrayList<String> playerList = new ArrayList<String>(Arrays.asList(MinecraftServer.getServer().getConfigurationManager().getAllUsernames()));
	    	
	    	for (String name : this.playerNames) {
	    		if (playerList.contains(name)) {
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    
//	    public List<String> getNames() {
//	    	//List<String> tempList = ((TestTE)worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord)).playerNames;
//	    	//ServerLogging.sendMessageFromServer("Sending names: " + this.playerNames.toString());
//	    	
//	    	return this.playerNames;
//	    }

	}


    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
    	this.blockIcon = iconRegister.registerIcon(PageControl.MODID + ":onlineDetectorBlock");
    }

}
