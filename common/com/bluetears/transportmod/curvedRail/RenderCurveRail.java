package com.bluetears.transportmod.curvedRail;


//import lordfokas.stargatetech.machine.StargateTE;
//import lordfokas.stargatetech.util.Helper;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

/**
* Renders a Stargate Model in the world. Block metadata defines the gate's orientation.
* Should be upgraded soon to remove the need for a Model and render stuff directly through OpenGL.
* @author LordFokas
*/
public class RenderCurveRail extends TileEntitySpecialRenderer {
	
public static final RenderCurveRail instance = new RenderCurveRail();
private static final ModelCurveRail model = new ModelCurveRail();

public void renderAModelAt(CurveRailTileEntity tile, double d, double d1, double d2, float f)
{
// here comes the hard part

int i =0; // a regular int, with a zero. more on this below

if(tile.worldObj != null) // to tell the world that your object is placed.
{
i =(tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord)); // to tell the game it needs to pick up metadata from your block
}
bindTextureByName("attempt2.png");
//for the int i = 0 which i mentioned above. the 0 will take over the texture from case 0, and have that texture render in your inventory. I unfortunately do not know yet how to make your block have more then one texture in your inventory. the blocks rendered in the world will have the allocated texture, the ones in the inventory wont. this will be updated as soon as I or anyone else founds the solution

GL11.glPushMatrix(); //start
GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); //size
GL11.glRotatef(0, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
GL11.glScalef(1.0F, -1F, -1F); // to make your block have a normal positioning. comment out to see what happens
model.renderModel(0.0625F); //renders and 0.0625F is exactly 1/16. every block has 16 entitys/pixels. if you make the number 1, every pixel will be as big as a block. make it 0.03125 and your block will be half as big as a normal one.
GL11.glPopMatrix(); //end

}

public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
{
renderAModelAt((CurveRailTileEntity) tileentity, d, d1, d2, f); //where to render
}

}