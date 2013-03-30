package com.bluetears.transportmod.items.boatparts;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.item.ItemBoat;

public class IronBoat extends ItemBoat{

	public IronBoat(int par1) {
		super(par1);
        this.maxStackSize = 1;
        this.setCreativeTab(TransportMod.boatstab);
        setUnlocalizedName("ironboat");
	}

}
