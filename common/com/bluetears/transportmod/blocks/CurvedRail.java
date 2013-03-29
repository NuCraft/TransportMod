package com.bluetears.transportmod.blocks;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.block.BlockRailBase;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class CurvedRail extends BlockRailBase {

        public CurvedRail (int par1) {
        	super(par1, false);
                
        }
   
        public void registerIcons(IconRegister par1IconRegister)
        {
                 this.blockIcon = par1IconRegister.registerIcon("transportmod:curvedrail");
        }

        /**
         * Called upon block activation (right click on the block.)
         */
        /*
        public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
        {
        	System.out.println("success");
            return false;
        }
        */
        
    	
    	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
    		int curve = getCurve(par2, par3, par4, par1World);
    		
    		//System.out.println("Check" + (curve));
    		/*if (isValidCombination(par2, par3, par4, par1World) != 0) {
    			if (par1World.getBlockTileEntity(par2, par3, par4) != null) {
    				System.out.println("This is the master block");
    				TileEntityFurnace tile = (TileEntityFurnace) par1World.getBlockTileEntity(par2, par3, par4);
    				par5EntityPlayer.displayGUIFurnace(tile);
    				return true;
    		
    			}
    	
    		}
    		*/
    		
    		if ( curve!= 0)
    		{
    			if(curve >299)
    			{
    				System.out.println("Valid 3x3 facing " + (curve-300));
    			}
    			else if(curve >199 && curve <299)
    			{
    				System.out.println("Valid 2x2 facing " + (curve-200));
    			}
    		}
    		return false;
    	}
        /**
         * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
         */
        public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
        {
        	//System.out.println(par9);
       
            return par9;
            
        }
        
        public void onBlockAdded(World world, int x, int y, int z) {
        	/*	int curveGrid =isValidCombination(x, y, z, world);
    		if (curveGrid != 0){
    			if (curveGrid == 1100001100 || curveGrid == 1100110)
    			{
    				System.out.println("this is a valid 3x3 combination");
    			}
    			if (curveGrid == 1100 || curveGrid == 110)
    			{
    				System.out.println("this is a valid 2x2 combination");
    			}
    			super.onBlockAdded(world, x, y, z);
    			System.out.println("this is a valid combination " + curveGrid);
    		}
    		else
    		{System.out.println("nope dot avi");
    		}
    		*/
    	}
        
        private int isValidCombination(int x, int y, int z, World world) {
        	int verifyGrid = 0;
        	
        	if (world.getBlockId(x + 1, y, z) == TransportMod.curvedRail.blockID)
	        	{
	        		verifyGrid += 100;
	        	}
	        	else 
	        	{
	        		return 0;
	        	} 
        	
        	if (world.getBlockId(x + 1, y, z + 1) == TransportMod.curvedRail.blockID)
	        	{
	        		verifyGrid += 10;
	        	}
	        	else if (world.getBlockId(x + 1, y, z - 1) == TransportMod.curvedRail.blockID)
	        	{
	        		verifyGrid += 1000;
	        	}
        		else 
        			{
        			return 0;
        			}
        	if (world.getBlockId(x + 2, y, z - 2) == TransportMod.curvedRail.blockID)
	        	{
	        		verifyGrid += 1000000000;
	        	}
        	if (world.getBlockId(x + 2, y, z - 1) == TransportMod.curvedRail.blockID)
	        	{
	        		verifyGrid += 100000000;
	        	}
        	if (world.getBlockId(x + 2, y, z + 2) == TransportMod.curvedRail.blockID)
	        	{
	        		verifyGrid += 100000;
	        	}
        	if (world.getBlockId(x + 2, y, z + 1) == TransportMod.curvedRail.blockID)
	        	{
	        		verifyGrid += 1000000;
	        	}

    		return verifyGrid;
    	}
        
        private boolean getNextX(int x, int y, int z, World world) {
    		return world.getBlockId(x + 1, y, z) == TransportMod.curvedRail.blockID;
    	}
    	
    	private boolean getPrevX(int x, int y, int z, World world) {
    		return world.getBlockId(x - 1, y, z) == TransportMod.curvedRail.blockID;
    	}
    	
    	private boolean getNextZ(int x, int y, int z, World world) {
    		return world.getBlockId(x, y, z + 1) == TransportMod.curvedRail.blockID;
    	}
    	
    	private boolean getPrevZ(int x, int y, int z, World world) {
    		return world.getBlockId(x, y, z - 1) == TransportMod.curvedRail.blockID;
    	}
    	
    	private boolean getBottomRightDiag(int x, int y, int z, World world) {
      	  return world.getBlockId(x - 1, y, z + 1) == TransportMod.curvedRail.blockID;
      	 }
        
    	private boolean getBottomLeftDiag(int x, int y, int z, World world) {
      	  return world.getBlockId(x - 1, y, z - 1) == TransportMod.curvedRail.blockID;
      	 }
    	
    	private boolean getTopRightDiag(int x, int y, int z, World world) {
        	  return world.getBlockId(x + 1, y, z + 1) == TransportMod.curvedRail.blockID;
        	 }
          
      	private boolean getTopLeftDiag(int x, int y, int z, World world) {
        	  return world.getBlockId(x + 1, y, z - 1) == TransportMod.curvedRail.blockID;
        	 }
    	
      	private int getCurve(int x, int y, int z, World world)
      	{
      		if (world.getBlockId(x + 1, y, z) == TransportMod.curvedRail.blockID)
      		{
      			if (world.getBlockId(x + 1, y, z+1) == TransportMod.curvedRail.blockID)
          		{
          			return 301;
          		}
      			else if (world.getBlockId(x + 1, y, z-1) == TransportMod.curvedRail.blockID)
          		{
          			return 300;
          		}
      			else if (world.getBlockId(x, y, z-1) == TransportMod.curvedRail.blockID)
          		{
          			return 210;
          		}
      			else if (world.getBlockId(x, y, z+1) == TransportMod.curvedRail.blockID)
          		{
          			return 211;
          		}
      			
      		}
      		
      		if (world.getBlockId(x - 1, y, z) == TransportMod.curvedRail.blockID)
      		{
      			if (world.getBlockId(x - 1, y, z+1) == TransportMod.curvedRail.blockID)
          		{
          			return 311;
          		}
      			else if (world.getBlockId(x - 1, y, z-1) == TransportMod.curvedRail.blockID)
          		{
          			return 310;
          		}
      			else if (world.getBlockId(x, y, z-1) == TransportMod.curvedRail.blockID)
          		{
          			return 200;
          		}
      			else if (world.getBlockId(x, y, z+1) == TransportMod.curvedRail.blockID)
          		{
          			return 201;
          		}
      			
      		}
      		
      		return 0;
      	}
      	
    	
    }    
        
