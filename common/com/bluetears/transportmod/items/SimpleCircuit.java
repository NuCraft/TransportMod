package com.bluetears.transportmod.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SimpleCircuit extends Item
{


public SimpleCircuit(int id)
{
         super(id);
         setCreativeTab(CreativeTabs.tabMisc);
}

@Override
public void updateIcons(IconRegister iconRegister)
{
         iconIndex = iconRegister.registerIcon("transportmod:simplecircuit");
}

}
