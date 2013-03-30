package com.bluetears.transportmod.items.boatparts;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class SimpleDriveShaft extends Item
{


public SimpleDriveShaft(int id)
{
         super(id);
         setCreativeTab(TransportMod.transportmodtab);
         setMaxStackSize(64);
         setUnlocalizedName("simpleDriveShaft");
}

@Override
public void updateIcons(IconRegister iconRegister)
{
         iconIndex = iconRegister.registerIcon("transportmod:simpleDriveShaft");
}

}
