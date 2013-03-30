package com.bluetears.transportmod.items.roads;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class CondensedAsphalt extends Item
{


public CondensedAsphalt(int id)
{
         super(id);
         setCreativeTab(TransportMod.roadstab);
         setUnlocalizedName("condensedAsphalt");
         setMaxStackSize(64);
}

@Override
public void updateIcons(IconRegister iconRegister)
{
         iconIndex = iconRegister.registerIcon("transportmod:condensedAsphalt");
}

}
