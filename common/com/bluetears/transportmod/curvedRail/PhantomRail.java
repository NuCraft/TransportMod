package com.bluetears.transportmod.curvedRail;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRail;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class PhantomRail extends BlockRail{

	private Icon theIcon;
	
	public PhantomRail(int par1) {
		super(par1);
		setStepSound(Block.soundStoneFootstep);
		setCreativeTab(null);
	}
	public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        this.blockIcon = par1IconRegister.registerIcon("transportmod:phantomRail");
        this.theIcon = par1IconRegister.registerIcon("transportmod:phantomRail_turn");
    }
	
	@SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return par2 >= 6 ? this.theIcon : this.blockIcon;
    }
	
	@Override
	public int idDropped(int par1, Random random, int par2) {
	    return 0;
	    
	}
}
