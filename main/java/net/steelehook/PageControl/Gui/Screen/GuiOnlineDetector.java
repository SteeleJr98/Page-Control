package net.steelehook.PageControl.Gui.Screen;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.steelehook.PageControl.PageControl;
import net.steelehook.PageControl.Blocks.OnlineDetectorBlock.TestTE;
import net.steelehook.PageControl.Blocks.Tile.OnlineDetectorTileEntity;
import net.steelehook.PageControl.Handlers.ServerLogging;
import net.steelehook.PageControl.Network.PCBlockUpdater;
import net.steelehook.PageControl.Network.PCPacketHandler;
import net.steelehook.PageControl.Base.BaseContainer;

public class GuiOnlineDetector extends GuiContainer {
	
	
	private GuiTextField input;
	private String tempName;
	
	private ArrayList<String> ownerNames;
	private int maxListSize = 5;
	
	private GuiButton doneButton;
	private GuiButton submitButton;
	private GuiButton copyButton;
	private GuiButton debugButton;
	
	private final int imageHeight = 192;
	private final int imageWidth = 192;
	
	private final TestTE tile;
	private final EntityPlayer entityPlayer;
	
	private static ResourceLocation bgTexture = new ResourceLocation(PageControl.MODID + ":textures/gui/mcbook.png");

	public GuiOnlineDetector(EntityPlayer player, TestTE te) {
		super(new BaseContainer(player, te));
		
		
		this.tile = te;
		this.entityPlayer = player;
		//this.ownerNames = ((TestTE)MinecraftServer.getServer().getEntityWorld().getTileEntity(tile.xCoord, tile.yCoord, tile.zCoord)).playerNames;
		this.ownerNames = worldNameList();
		
		

	}
	
	@Override
	public void initGui() {
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		
		doneButton = new GuiButton(0, width / 2 + 2, 4 + imageHeight, 98, 20, I18n.format("gui.done", new Object[0]));
		buttonList.add(doneButton);
		submitButton = new GuiButton(1, width / 2 + 2 - 100, 4 + imageHeight, 98, 20, "Submit");
		buttonList.add(submitButton);
		copyButton = new GuiButton(2, width / 2 + 2 - 49, 4 + imageHeight + 20, 98, 20, "Copy Name");
		buttonList.add(copyButton);
		debugButton = new GuiButton(2, width / 2 + 2 - 200, 4 + imageHeight, 98, 20, "Debug");
		if ((Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment")) {
			buttonList.add(debugButton);
		}
		
		
		
		int offsetFromScreenLeft = (width - xSize) / 2;
		int offsetFromScreenTop = (height - ySize) / 2;
		input = new GuiTextField(fontRendererObj, offsetFromScreenLeft + 36, 150, 100, 16);
		input.setFocused(false);
		input.setMaxStringLength(50);
	}
	
	
	
//	@Override
//	public void drawScreen(int scrWidth, int scrHeight, float parFloat) {
//		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//		mc.getTextureManager().bindTexture(bgTexture);
//		int offsetFromScreenLeft = (width - imageWidth) / 2;
//		drawTexturedModalRect(offsetFromScreenLeft, 2, 0, 0, imageWidth, imageHeight);
//
//		//fontRendererObj.drawSplitString("Owner: " + ownerName, offsetFromScreenLeft + 36, 15, 116, 0);
//		
//		super.drawScreen(scrWidth, scrHeight, parFloat);
//	}
	
	@Override
	protected void keyTyped(char c, int i) {
		
		input.textboxKeyTyped(c, i);
		if (!(i == Keyboard.KEY_E && input.isFocused())) {
			super.keyTyped(c, i);
		}
		
	}
	
	@Override
	protected void mouseClicked(int i, int j, int k) {
		super.mouseClicked(i, j, k);
		input.mouseClicked(i, j, k);
		//ServerLogging.sendMessageFromServer("x: " + i + " y: " + j);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if (button == doneButton) {
			mc.displayGuiScreen((GuiScreen)null);
		}
		if (button == submitButton) {
			tempName = input.getText();
			if (tempName == null || tempName == "") {
				return;
			}
			if (!this.ownerNames.contains(tempName)) {
				if (this.ownerNames.size() >= maxListSize) {
					return;
				}
//				tile.playerNames.add(tempName);
			}
//			else {
//				int playerIndex = tile.playerNames.indexOf(tempName);
//				tile.playerNames.remove(playerIndex);
//			}
			PCPacketHandler.packetWrapper.sendToServer(new PCBlockUpdater(tile.xCoord, tile.yCoord, tile.zCoord, tempName));
			//this.tile.playerNames = worldNameList();
			this.ownerNames = worldNameList();
			
		}
		if (button == copyButton) {
			input.setText(this.entityPlayer.getDisplayName());
		}
		if (button == debugButton) {
			//ServerLogging.sendMessageFromServer("Gui Names" + ownerNames.toString());
			ServerLogging.sendMessageFromServer("Tile Names" + tile.playerNames.toString());
			ServerLogging.sendMessageFromServer("World Tile Names" +  ((TestTE)MinecraftServer.getServer().getEntityWorld().getTileEntity(tile.xCoord, tile.yCoord, tile.zCoord)).playerNames.toString());
		}
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
//		if (input.isFocused()) {
//			tempName = input.getText();
//		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		int offsetFromScreenLeft = (width - xSize) / 2;
		for (int i = 0; i < this.ownerNames.size(); i++) {
			fontRendererObj.drawSplitString(/*String.valueOf(i) + */this.ownerNames.get(i), offsetFromScreenLeft + 36, (i*10) + 15, 116, 0);
		}
		//fontRendererObj.drawSplitString("Owner: " + ownerName, offsetFromScreenLeft + 36, 15, 116, 0);
		//fontRendererObj.drawSplitString("tempName: " + tempName, offsetFromScreenLeft + 36, (ownerNames.size() * 10) + 15, 116, 0);
		fontRendererObj.drawSplitString("Steele says: \"you're doing it wrong\"", offsetFromScreenLeft + 36, (7 * 10) + 15, 116, 0);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(bgTexture);
		int offsetFromScreenLeft = (width - xSize) / 2;
		int offsetFromScreenTop = (height - ySize) / 2;
		drawTexturedModalRect(offsetFromScreenLeft, 2, 0, 0, imageWidth, imageHeight);
		
		input.drawTextBox();
		
	}
	
	private ArrayList<String> worldNameList() {
		return ((TestTE)MinecraftServer.getServer().worldServerForDimension(entityPlayer.dimension).getTileEntity(tile.xCoord, tile.yCoord, tile.zCoord)).playerNames;
	}
	
}
