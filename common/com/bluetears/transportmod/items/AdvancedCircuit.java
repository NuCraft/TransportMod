package com.bluetears.transportmod.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AdvancedCircuit extends Item
{


public AdvancedCircuit(int id)
{
         super(id);
         setCreativeTab(CreativeTabs.tabMisc);
}

@Override
public void updateIcons(IconRegister iconRegister)
{
         iconIndex = iconRegister.registerIcon("transportmod:advancedcircuit");
}

}
