package com.bluetears.transportmod.items.boatparts;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class WoodBoatHull extends Item
{


public WoodBoatHull(int id)
{
         super(id);
         setCreativeTab(TransportMod.boatstab);
}

@Override
public void updateIcons(IconRegister iconRegister)
{
         iconIndex = iconRegister.registerIcon("transportmod:woodenboathull");
}

}
