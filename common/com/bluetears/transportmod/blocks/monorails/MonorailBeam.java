package com.bluetears.transportmod.blocks.monorails;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class MonorailBeam extends BlockRailBase {

        public MonorailBeam (int par1) {
        	super(par1, false);
        	setStepSound(Block.soundGravelFootstep);
        	setUnlocalizedName("monorailBeam");
        	setHardness(0.5F);
        	setCreativeTab(TransportMod.monorailstab);
                
        }
   
        public void registerIcons(IconRegister par1IconRegister)
        {
                 this.blockIcon = par1IconRegister.registerIcon("transportmod:monorailBeam");
        }
      	
    	
    }    
        
