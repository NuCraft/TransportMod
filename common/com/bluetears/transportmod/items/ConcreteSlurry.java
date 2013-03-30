package com.bluetears.transportmod.items;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ConcreteSlurry extends Item
{


public ConcreteSlurry(int id)
{
         super(id);
         setCreativeTab(TransportMod.transportmodtab);
         setUnlocalizedName("concreteSlurry");
         setMaxStackSize(64);
}

@Override
public void updateIcons(IconRegister iconRegister)
{
         iconIndex = iconRegister.registerIcon("transportmod:concreteSlurry");
}

}
