package com.bluetears.transportmod.blocks;

import net.minecraft.block.BlockRailBase;
import net.minecraft.client.renderer.texture.IconRegister;

public class CurvedRail extends BlockRailBase {

        public CurvedRail (int par1) {
        	super(par1, false);
                
        }
   
        public void registerIcons(IconRegister par1IconRegister)
        {
                 this.blockIcon = par1IconRegister.registerIcon("transportmod:curvedrail");
        }

        
    }    
        
