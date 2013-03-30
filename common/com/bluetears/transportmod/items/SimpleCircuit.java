package com.bluetears.transportmod.items;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class SimpleCircuit extends Item
{


public SimpleCircuit(int id)
{
         super(id);
         setCreativeTab(TransportMod.transportmodtab);
         setMaxStackSize(64);
         setUnlocalizedName("simpleCircuit");
}

@Override
public void updateIcons(IconRegister iconRegister)
{
         iconIndex = iconRegister.registerIcon("transportmod:simpleCircuit");
}

}
