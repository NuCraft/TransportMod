package com.bluetears.transportmod.blocks;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.block.BlockRailBase;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CurvedRail extends BlockRailBase {

        public CurvedRail (int par1) {
        	super(par1, false);
                
        }
   
        public void registerIcons(IconRegister par1IconRegister)
        {
                 this.blockIcon = par1IconRegister.registerIcon("transportmod:curvedrail");
        }
        
    	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
    		
    		int curve = getCurve(par2, par3, par4, par1World);

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
    	 *  Returns the integer combination of the curve, other wise retuns 0
    	 *  1st digit represents the radius of the curve
    	 *  2nd digit is orientation on X axis, 1 being positive, 0 being negative
    	 *  3rd digit is orientation on Z axis, 1 being positive, 0 being negative
    	 */
      	private int getCurve(int x, int y, int z, World world)
      	{
      		if (world.getBlockId(x + 1, y, z) == TransportMod.curvedRail.blockID)
      		{
      			if (world.getBlockId(x + 1, y, z + 1) == TransportMod.curvedRail.blockID
      			 &&	world.getBlockId(x - 1, y, z - 1) == TransportMod.curvedRail.blockID
      			 && world.getBlockId(x, y, z - 1)     == TransportMod.curvedRail.blockID) 
          		{
          			return 301;
          		}
      			else if (world.getBlockId(x + 1, y, z - 1) == TransportMod.curvedRail.blockID
      				  && world.getBlockId(x - 1, y, z + 1) == TransportMod.curvedRail.blockID
      	      		  && world.getBlockId(x, y, z + 1)     == TransportMod.curvedRail.blockID) 
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
      			if (world.getBlockId(x - 1, y, z + 1) == TransportMod.curvedRail.blockID
      			 && world.getBlockId(x + 1, y, z - 1) == TransportMod.curvedRail.blockID
        	     && world.getBlockId(x, y, z - 1)     == TransportMod.curvedRail.blockID) 
          		{
          			return 311;
          		}
      			else if (world.getBlockId(x - 1, y, z - 1) == TransportMod.curvedRail.blockID
      				  && world.getBlockId(x + 1, y, z + 1) == TransportMod.curvedRail.blockID
        	      	  && world.getBlockId(x, y, z + 1)     == TransportMod.curvedRail.blockID) 
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
        
