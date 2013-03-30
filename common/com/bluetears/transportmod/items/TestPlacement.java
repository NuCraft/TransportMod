package com.bluetears.transportmod.items;

import com.bluetears.transportmod.TransportMod;
import com.bluetears.transportmod.blocks.CurveRail;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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

@Override
public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
{
	System.out.println("Toss1 " + par2EntityPlayer.getRotationYawHead());
	if( super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10))
			{
				//spawnID = CurveRail.blockID;
			}
	super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
	return false;
	
	
}

}