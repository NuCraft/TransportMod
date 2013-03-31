package com.bluetears.transportmod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBaseRailLogic;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockRail;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.bluetears.transportmod.TransportMod;
import com.bluetears.transportmod.curvedRail.CurveRailTileEntity;

public class CurveRail extends BlockContainer {

public CurveRail(int par1, @SuppressWarnings("rawtypes") Class class1)
{

super(par1, Material.ground);
setUnlocalizedName("curveRail");
setHardness(0.5F);
setStepSound(Block.soundStoneFootstep);
setBlockBounds(-1F, 0, -1F, 2F, 0.0625F, 2F);
}

@Override
public int idDropped(int par1, Random random, int par2) {
    return TransportMod.curvedRail.blockID;
    
}

@Override
public int quantityDropped(Random par1Random)
{
    return 5;
   
}

@Override
public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int x, int y, int z)
{
	return AxisAlignedBB.getBoundingBox((double)x-1F, (double)y, (double)z -1F, (double)x + 2F, (double)y+0.0625, (double)z + 2F);
} 

@Override
public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int x, int y, int z)
{
    return this.getCollisionBoundingBoxFromPool(par1World, x, y, z);
}

public void registerIcons(IconRegister par1IconRegister)
{
         this.blockIcon = par1IconRegister.registerIcon("transportmod:curveRail");
}


public boolean isOpaqueCube() {
	
return false;
} // make it opaque cube, or else you will be able to see trough the world !
public boolean renderAsNormalBlock() {
return false;
}


public boolean blockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer){

	
int p = MathHelper.floor_double((double)((par5EntityPlayer.rotationYaw * 4F) / 360F) + 0.5D) & 3; //i don't know what this is for, so we better keep that there

byte byte0 = 3;


if (p == 0)
{
byte0 = 4;
}
if (p == 1)
{
byte0 = 3;
}
if (p == 2)
{
byte0 = 2;
}
if (p == 3)
{
byte0 = 1;
}

par1World.setBlockMetadataWithNotify(par2, par3, par4, (int)byte0, 0);

return true;
}

public TileEntity getBlockEntity() {
return new CurveRailTileEntity();

}



@Override
public TileEntity createNewTileEntity(World world) {
	return new CurveRailTileEntity();
	
}

@Override
public void onBlockAdded(World par1World, int par2, int par3, int par4)
{
	super.onBlockAdded(par1World, par2, par3, par4);
	
	int x = par2-1;
	while(x < (par2+2))
		{
			if(par1World.getBlockId(x, par3, par4 +1) == TransportMod.curvedRail.blockID)
			{
				par1World.setBlockToAir(x, par3, par4+1);
			}
			if(par1World.getBlockId(x, par3, par4-1) == TransportMod.curvedRail.blockID)
			{
				par1World.setBlockToAir(x, par3, par4-1);
			}
			if(x !=par2 && par1World.getBlockId(x, par3, par4) == TransportMod.curvedRail.blockID)
			{
				par1World.setBlockToAir(x, par3, par4);
			}
			x++;
		}
	
}
	
///ATTEMPT TO MAKE IT A RAIL #1
	
    public static final boolean isRailBlockAt(World par0World, int par1, int par2, int par3)
    {
        return isRailBlock(par0World.getBlockId(par1, par2, par3));
    }

    /**
     * Return true if the parameter is a blockID for a valid rail block (current is rail, powered or detector).
     */
    public static final boolean isRailBlock(int par0)
    {
        return par0 == TransportMod.curveRail.blockID;
    }
	
	   public int getBasicRailMetadata(IBlockAccess world, EntityMinecart cart, int x, int y, int z)
	    {
	        int meta = world.getBlockMetadata(x, y, z);
	        return meta;
	    }
	   
	   public boolean isPowered()
	    {
	        return false;
	    }
	   
	    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	    {
	        return par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4);
	    }
	    
	    
	    protected void func_94358_a(World par1World, int par2, int par3, int par4, int par5, int par6, int par7)
	    {
	        if (par7 > 0 && Block.blocksList[par7].canProvidePower() && (new BlockBaseRailLogic(new CurvedRail(par2), par1World, par2, par3, par4)).func_94505_a() == 3)
	        {
	            this.refreshTrackShape(par1World, par2, par3, par4, false);
	        }
	    }
	    
	    public int getMobilityFlag()
	    {
	        return 0;
	    }
	    
	    public boolean canMakeSlopes(World world, int x, int y, int z)
	    {
	        return false;
	    }
	    
	    public float getRailMaxSpeed(World world, EntityMinecart cart, int y, int x, int z)
	    {
	        return 0.4f;
	    }
	    
	    public void onMinecartPass(World world, EntityMinecart cart, int y, int x, int z)
	    {
	    }    
	    
	    private int renderType = 9;
	    
	    public void setRenderType(int value)
	    {
	        renderType = value;
	    }
	    
	    protected void refreshTrackShape(World par1World, int par2, int par3, int par4, boolean par5)
	    {
	    }




}