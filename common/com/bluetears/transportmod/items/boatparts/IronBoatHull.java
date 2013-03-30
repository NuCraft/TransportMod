package com.bluetears.transportmod.items.boatparts;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class IronBoatHull extends Item
{


public IronBoatHull(int id)
{
         super(id);
         setCreativeTab(TransportMod.boatstab);
         setMaxStackSize(64);
         setUnlocalizedName("ironboathull");
}

@Override
public void updateIcons(IconRegister iconRegister)
{
         iconIndex = iconRegister.registerIcon("transportmod:ironboathull");
}

}
