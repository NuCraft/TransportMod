package com.bluetears.transportmod.items.roads;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class Asphalt extends Item
{


public Asphalt(int id)
{
         super(id);
         setCreativeTab(TransportMod.roadstab);
         setUnlocalizedName("asphalt");
         setMaxStackSize(64);
}

@Override
public void updateIcons(IconRegister iconRegister)
{
         iconIndex = iconRegister.registerIcon("transportmod:asphalt");
}

}
