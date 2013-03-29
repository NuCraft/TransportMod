package com.bluetears.transportmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.bluetears.transportmod.curvedRail.CurveRailTileEntity;
import com.bluetears.transportmod.curvedRail.Helper;
import com.bluetears.transportmod.curvedRail.RenderCurveRail;

public class CurveRail extends BlockContainer {

public CurveRail(int par1) {
	super(par1, Material.iron);
setUnlocalizedName("curveRail");
setLightOpacity(0);
}

@Override
public void onBlockPlacedBy(World w, int x, int y, int z, EntityLiving l, ItemStack stack){
int dir = Helper.yaw2dir(l.rotationYaw);
dir = Helper.oppositeDirection(dir);
placeStargateWithRotation(w, x, y, z, dir);
}

public void placeStargateWithRotation(World w, int x, int y, int z, int dir){
checkPlace(w, x, y, z, dir);
}
/*
@Override
public int getRenderType(){
return RenderCurveRail.instance().getRenderId();
}
*/
public void checkPlace(World w, int x, int y, int z, int dir){
boolean placed = false;
boolean dirX = (dir == Helper.dirXNeg || dir == Helper.dirXPos);
if(dirX){
if(w.isAirBlock(x, y, z+1) && w.isAirBlock(x, y, z-1) && w.isAirBlock(x, y, z+2) && w.isAirBlock(x, y, z-2)){
placed = true;
for(int lz = -3; lz <= 3; lz++){
for(int ly = 1; ly <= 6; ly++){
if(!w.isAirBlock(x, y + ly, z + lz)){
placed = false;
break;
}
}
if(!placed){
break;
}
}
}
} else {
if(w.isAirBlock(x+1, y, z) && w.isAirBlock(x-1, y, z) && w.isAirBlock(x+2, y, z) && w.isAirBlock(x-2, y, z)){
placed = true;
for(int lx = -3; lx <= 3; lx++){
for(int ly = 1; ly <= 6; ly++){
if(!w.isAirBlock(x + lx, y + ly, z)){
placed = false;
break;
}
}
if(!placed){
break;
}
}
}
}
}

@Override
public void breakBlock(World w, int x, int y, int z, int id, int meta){
}

@Override public boolean isOpaqueCube(){ return false; }
@Override public TileEntity createNewTileEntity(World w){ return new CurveRailTileEntity(); }


}