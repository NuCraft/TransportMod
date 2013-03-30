package com.bluetears.transportmod.items;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class TinIngot extends Item
{


public TinIngot(int id)
{
         super(id);
         setCreativeTab(TransportMod.transportmodtab);
         setUnlocalizedName("ingotTin");
         setMaxStackSize(64);
}

@Override
public void updateIcons(IconRegister iconRegister)
{
         iconIndex = iconRegister.registerIcon("transportmod:ingotTin");
}

}
