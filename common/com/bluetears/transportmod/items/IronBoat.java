package com.bluetears.transportmod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBoat;

public class IronBoat extends ItemBoat{

	public IronBoat(int par1) {
		super(par1);
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabTransport);
	}

}
