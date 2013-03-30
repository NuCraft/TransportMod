package com.bluetears.transportmod.items;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemReed;

public class TestPlacement extends ItemReed
{


public TestPlacement(int id, Block par2)
{
         super(id, par2);
         setCreativeTab(CreativeTabs.tabTransport);
}

@Override
public void updateIcons(IconRegister iconRegister)
{
         iconIndex = iconRegister.registerIcon("transportmod:testtest");
}

}