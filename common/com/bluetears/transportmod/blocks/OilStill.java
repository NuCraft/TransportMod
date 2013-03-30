package com.bluetears.transportmod.blocks;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;

public class OilStill extends BlockStationary {

    protected OilStill(int id) {

        super(id, Material.water);
        blockHardness = 100F;
        this.setLightOpacity(3);
        this.setUnlocalizedName("oilstill");
        this.setCreativeTab(TransportMod.transportmodtab);
        this.disableStats();
    }
}