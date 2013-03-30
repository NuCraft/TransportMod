package com.bluetears.transportmod.blocks;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;

public class OilFlowing extends BlockFlowing {

    protected OilFlowing(int id) {

        super(id, Material.water);
        blockHardness = 100F;
        this.setLightOpacity(3);
        this.setCreativeTab(TransportMod.transportmodtab);
        this.setUnlocalizedName("oilflowing");
    }
}