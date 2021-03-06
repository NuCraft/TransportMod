package com.bluetears.transportmod.items.boatparts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

import com.bluetears.transportmod.TransportMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityIronBoat extends EntityBoat
{
	private boolean field_70279_a;
    private double field_70276_b;
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    @SideOnly(Side.CLIENT)
    private double velocityX;
    @SideOnly(Side.CLIENT)
    private double velocityY;
    @SideOnly(Side.CLIENT)
    private double velocityZ;
    
	 public EntityIronBoat(World par1World)
	    {
	        super(par1World);
	        this.field_70279_a = true;
	        this.field_70276_b = 0.07D;//0.07
	        this.preventEntitySpawning = true;
	        this.setSize(1.5F, 0.6F);
	        this.yOffset = this.height / 2.0F;
	    }
	 
	 public EntityIronBoat(World par1World, double par2, double par4, double par6)
	    {
	        this(par1World);
	        this.setPosition(par2, par4 + (double)this.yOffset, par6);
	        this.motionX = 0.0D;
	        this.motionY = 0.0D;
	        this.motionZ = 0.0D;
	        this.prevPosX = par2;
	        this.prevPosY = par4;
	        this.prevPosZ = par6;
	    }
	 
	 @Override
	 public void updateRiderPosition()
	    {
	        if (this.riddenByEntity != null)
	        {
	            double d0 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
	            double d1 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
	            this.riddenByEntity.setPosition(this.posX + d0, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1);
	        }
	    }
	 
	 @Override
	  public double getMountedYOffset()
	    {
	        return (double)this.height * 0.0D - 0.30000001192092896D;
	    }
	 
	 	@Override
	    @SideOnly(Side.CLIENT)

	    /**
	     * Sets the velocity to the args. Args: x, y, z
	     */
	    public void setVelocity(double par1, double par3, double par5)
	    {
	        this.velocityX = this.motionX = par1;
	        this.velocityY = this.motionY = par3;
	        this.velocityZ = this.motionZ = par5;
	    }
	 	
	 	@Override
	 	 public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
	     {
	         if (this.field_70279_a)
	         {
	             this.boatPosRotationIncrements = par9 + 5;
	         }
	         else
	         {
	             double d3 = par1 - this.posX;
	             double d4 = par3 - this.posY;
	             double d5 = par5 - this.posZ;
	             double d6 = d3 * d3 + d4 * d4 + d5 * d5;

	             if (d6 <= 1.0D)
	             {
	                 return;
	             }

	             this.boatPosRotationIncrements = 3;
	         }

	         this.boatX = par1;
	         this.boatY = par3;
	         this.boatZ = par5;
	         this.boatYaw = (double)par7;
	         this.boatPitch = (double)par8;
	         this.motionX = this.velocityX;
	         this.motionY = this.velocityY;
	         this.motionZ = this.velocityZ;
	     }
	 	
	 	
	 
	    @Override
	    public void onUpdate()
	    {
	        if (this.getTimeSinceHit() > 0)
	        {
	            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
	        }

	        if (this.getDamageTaken() > 0)
	        {
	            this.setDamageTaken(this.getDamageTaken() - 1);
	        }

	        this.prevPosX = this.posX;
	        this.prevPosY = this.posY;
	        this.prevPosZ = this.posZ;
	        byte b0 = 5;
	        double d0 = 0.0D;

	        for (int i = 0; i < b0; ++i)
	        {
	            double d1 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(i + 0) / (double)b0 - 0.125D;
	            double d2 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(i + 1) / (double)b0 - 0.125D;
	            AxisAlignedBB axisalignedbb = AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX, d1, this.boundingBox.minZ, this.boundingBox.maxX, d2, this.boundingBox.maxZ);

	            if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.water)|| this.worldObj.isAABBInMaterial(axisalignedbb, Material.lava))
	            {
	                d0 += 1.0D / (double)b0;
	            }
	        }

	        double d3 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
	        double d4;
	        double d5;

	        if (d3 > 0.26249999999999996D)
	        {
	            d4 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D);
	            d5 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D);

	            for (int j = 0; (double)j < 1.0D + d3 * 60.0D; ++j)
	            {
	                double d6 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
	                double d7 = (double)(this.rand.nextInt(2) * 2 - 1) * 0.7D;
	                double d8;
	                double d9;

	                if (this.rand.nextBoolean())
	                {
	                    d8 = this.posX - d4 * d6 * 0.8D + d5 * d7;
	                    d9 = this.posZ - d5 * d6 * 0.8D - d4 * d7; 
	                    this.worldObj.spawnParticle("splash", d8, this.posY - 0.125D, d9, this.motionX, this.motionY, this.motionZ);
	                }
	                else
	                {
	                    d8 = this.posX + d4 + d5 * d6 * 0.7D;
	                    d9 = this.posZ + d5 - d4 * d6 * 0.7D;
	                    this.worldObj.spawnParticle("splash", d8, this.posY - 0.125D, d9, this.motionX, this.motionY, this.motionZ);
	                }
	            }
	        }

	        double d10;
	        double d11;

	        if (this.worldObj.isRemote && this.field_70279_a)
	        {
	            if (this.boatPosRotationIncrements > 0)
	            {
	                d4 = this.posX + (this.boatX - this.posX) / (double)this.boatPosRotationIncrements;
	                d5 = this.posY + (this.boatY - this.posY) / (double)this.boatPosRotationIncrements;
	                
	                d11 = this.posZ + (this.boatZ - this.posZ) / (double)this.boatPosRotationIncrements;
	                d10 = MathHelper.wrapAngleTo180_double(this.boatYaw - (double)this.rotationYaw);
	                this.rotationYaw = (float)((double)this.rotationYaw + d10 / (double)this.boatPosRotationIncrements);
	                this.rotationPitch = (float)((double)this.rotationPitch + (this.boatPitch - (double)this.rotationPitch) / (double)this.boatPosRotationIncrements);
	                --this.boatPosRotationIncrements;
	                this.setPosition(d4, d5, d11);
	                this.setRotation(this.rotationYaw, this.rotationPitch);
	            }
	            else
	            {
	                d4 = this.posX + this.motionX;
	                d5 = this.posY + this.motionY;
	                d11 = this.posZ + this.motionZ;
	                this.setPosition(d4, d5, d11);

	                if (this.onGround)
	                {
	                    this.motionX *= 0.5D;
	                    this.motionY *= 0.5D;
	                    this.motionZ *= 0.5D;
	                }

	                this.motionX *= 0.9900000095367432D;
	                this.motionY *= 0.949999988079071D;
	                this.motionZ *= 0.9900000095367432D;
	            }
	        }
	        else
	        {
	            if (d0 < 1.0D)
	            {
	                d4 = d0 * 2.0D - 1.0D;
	                this.motionY += 0.03999999910593033D * d4;
	            }
	            else
	            {
	                if (this.motionY < 0.0D)
	                {
	                    this.motionY /= 2.0D;
	                }

	                this.motionY += 0.007000000216066837D;
	            }

	            if (this.riddenByEntity != null)
	            {
	                this.motionX += this.riddenByEntity.motionX * this.field_70276_b * 4;//remove *4's
	                this.motionZ += this.riddenByEntity.motionZ * this.field_70276_b * 4;//changes acceleration and turn rate
	            }

	            d4 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

	            if (d4 > 0.35D)//was 0.35D
	            {
	                d5 = 0.35D / d4;
	                this.motionX *= d5;
	                this.motionZ *= d5;
	                d4 = 0.35D;
	            }

	            if (d4 > d3 && this.field_70276_b < 0.35D)
	            {
	                this.field_70276_b += (0.35D - this.field_70276_b) / 35.0D;

	                if (this.field_70276_b > 0.35D)
	                {
	                    this.field_70276_b = 0.35D;
	                }
	            }
	            else
	            {
	                this.field_70276_b -= (this.field_70276_b - 0.07D) / 35.0D;

	                if (this.field_70276_b < 0.07D)
	                {
	                    this.field_70276_b = 0.07D;//last thing from 0.35 change
	                }
	            }

	            if (this.onGround)
	            {
	                this.motionX *= 0.5D;
	                this.motionY *= 0.5D;
	                this.motionZ *= 0.5D;
	            }

	            this.moveEntity(this.motionX, this.motionY, this.motionZ);
	            

	            if (this.isCollidedHorizontally && d3 > 0.3D)//was 0.2
	            {
	                if (!this.worldObj.isRemote)
	                {
	                    this.setDead();
	                    this.dropItem(TransportMod.ironBoat.itemID,1);
	                
	                }
	            }
	            else
	            {
	                this.motionX *= 0.9900000095367432D;
	                this.motionY *= 0.949999988079071D;
	                this.motionZ *= 0.9900000095367432D;
	            }

	            this.rotationPitch = 0.0F;
	            d5 = (double)this.rotationYaw;
	            d11 = this.prevPosX - this.posX;
	            d10 = this.prevPosZ - this.posZ;

	            if (d11 * d11 + d10 * d10 > 0.001D)
	            {
	                d5 = (double)((float)(Math.atan2(d10, d11) * 180.0D / Math.PI));
	            }

	            double d12 = MathHelper.wrapAngleTo180_double(d5 - (double)this.rotationYaw);

	            if (d12 > 20.0D)
	            {
	                d12 = 20.0D;
	            }

	            if (d12 < -20.0D)
	            {
	                d12 = -20.0D;
	            }

	            this.rotationYaw = (float)((double)this.rotationYaw + d12);
	            this.setRotation(this.rotationYaw, this.rotationPitch);

	            if (!this.worldObj.isRemote)
	            {
	                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
	                int l;

	                if (list != null && !list.isEmpty())
	                {
	                    for (l = 0; l < list.size(); ++l)
	                    {
	                        Entity entity = (Entity)list.get(l);

	                        if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityBoat)
	                        {
	                            entity.applyEntityCollision(this);
	                        }
	                    }
	                }

	                for (l = 0; l < 4; ++l)
	                {
	                    int i1 = MathHelper.floor_double(this.posX + ((double)(l % 2) - 0.5D) * 0.8D);
	                    int j1 = MathHelper.floor_double(this.posZ + ((double)(l / 2) - 0.5D) * 0.8D);

	                    for (int k1 = 0; k1 < 2; ++k1)
	                    {
	                        int l1 = MathHelper.floor_double(this.posY) + k1;
	                        int i2 = this.worldObj.getBlockId(i1, l1, j1);

	                        if (i2 == Block.snow.blockID)
	                        {
	                            this.worldObj.setBlockToAir(i1, l1, j1);
	                        }
	                        else if (i2 == Block.waterlily.blockID)
	                        {
	                            this.worldObj.destroyBlock(i1, l1, j1, true);
	                        }
	                    }
	                }

	                if (this.riddenByEntity != null && this.riddenByEntity.isDead)
	                {
	                    this.riddenByEntity = null;
	                }
	            }
	        }
	    }
	    /**
	     * Called when the entity is attacked.
	     */
	    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	    {	
	    	if(par1DamageSource instanceof EntityDamageSource)
	    	{
	    	{
	        if (this.isEntityInvulnerable())
	        {
	            return false;
	        }
	        else if (!this.worldObj.isRemote && !this.isDead)
	        {
	            this.setForwardDirection(-this.getForwardDirection());
	            this.setTimeSinceHit(10);
	            this.setDamageTaken(this.getDamageTaken() + par2 * 10);
	            this.setBeenAttacked();
	            boolean flag = par1DamageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)par1DamageSource.getEntity()).capabilities.isCreativeMode;

	            if (flag || this.getDamageTaken() > 40)
	            {
	                if (this.riddenByEntity != null)
	                {
	                    this.riddenByEntity.mountEntity(this);
	                }

	                if (!flag)
	                {
	                    this.dropItemWithOffset(TransportMod.ironBoat.itemID, 1, 0.0F);
	                }

	                this.setDead();
	            }

	            return true;
	        }
	        else
	        {
	            return true;
	        }
	    	}
	    }
	    	else return false;
	    	
	    }
}
