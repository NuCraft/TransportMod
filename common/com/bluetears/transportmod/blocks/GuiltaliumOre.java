package com.bluetears.transportmod.blocks;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class GuiltaliumOre extends Block {

        public GuiltaliumOre (int id, Material iron) {
                super(id, iron);
                setHardness(4.0F); // 33% harder than diamond
                setStepSound(Block.soundStoneFootstep);
                setCreativeTab(TransportMod.transportmodtab);
                setUnlocalizedName("guiltaliumore");
        }
        
        public void registerIcons(IconRegister par1IconRegister)
        {
                 this.blockIcon = par1IconRegister.registerIcon("transportmod:guiltaliumore");
        }

        
        
}