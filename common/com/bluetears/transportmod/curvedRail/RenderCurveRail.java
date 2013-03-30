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

@Override
public void renderTileEntityAt(TileEntity e, double x, double y, double z, float f){
	CurveRailTileEntity curveRail = (CurveRailTileEntity) e;
GL11.glPushMatrix();
switch(curveRail.getDirection()){
case Helper.dirXPos:
GL11.glTranslated(x, y+0.5, z+0.5);
break;
case Helper.dirXNeg:
GL11.glTranslated(x+1, y+0.5, z+0.5);
GL11.glRotatef(180, 0, 1, 0);
break;
case Helper.dirZNeg:
GL11.glTranslated(x+0.5, y+0.5, z+1);
GL11.glRotatef(90, 0, 1, 0);
break;
case Helper.dirZPos:
GL11.glTranslated(x+0.5, y+0.5, z);
GL11.glRotatef(270, 0, 1, 0);
break;
default:
GL11.glPopMatrix();
return;
}
bindTextureByName("/mods/StargateTech/textures/stargate.png");
model.renderModel(0.0625F);
GL11.glPopMatrix();
}
}