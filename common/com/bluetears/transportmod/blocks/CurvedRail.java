package com.bluetears.transportmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class CurvedRail extends BlockRailBase {

        public CurvedRail (int par1) {
        	super(par1, false);
                
        }
   
        public void registerIcons(IconRegister par1IconRegister)
        {
                 this.blockIcon = par1IconRegister.registerIcon("transportmod:curvedrail");
        }

        
    }    
        
