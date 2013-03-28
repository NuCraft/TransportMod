package com.bluetears.transportmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class CurvedRail extends Block {

        public CurvedRail (int id, Material material) {
                super(id, material);
                setCreativeTab(CreativeTabs.tabBlock);
        }
       //ioaujwebgfviu
   
        public void registerIcons(IconRegister par1IconRegister)
        {
                 this.blockIcon = par1IconRegister.registerIcon("transportmod:curvedrail");
        }

        
    }    
        
