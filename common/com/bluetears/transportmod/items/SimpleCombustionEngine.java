package com.bluetears.transportmod.items;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class SimpleCombustionEngine extends Item
{


public SimpleCombustionEngine(int id)
{
         super(id);
         setCreativeTab(TransportMod.transportmodtab);
         setMaxStackSize(64);
         setUnlocalizedName("simpleCombustionEngine");
}

@Override
public void updateIcons(IconRegister iconRegister)
{
         iconIndex = iconRegister.registerIcon("transportmod:simpleCombustionEngine");
}

}
