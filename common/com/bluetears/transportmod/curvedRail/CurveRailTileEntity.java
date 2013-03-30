package com.bluetears.transportmod.curvedRail;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;

public class CurveRailTileEntity extends TileEntity {

	public int rota = 0;

	public CurveRailTileEntity(int rot)
	{
		super();
		rota = rot;
		System.out.println("Chekc create" + rot);
	}
}

