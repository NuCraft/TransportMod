package com.bluetears.transportmod.items.roads;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class HotAsphalt extends Item
{


public HotAsphalt(int id)
{
         super(id);
         setCreativeTab(TransportMod.roadstab);
         setUnlocalizedName("hotAsphalt");
         setMaxStackSize(64);
}

@Override
public void updateIcons(IconRegister iconRegister)
{
         iconIndex = iconRegister.registerIcon("transportmod:hotAsphalt");
}

}
