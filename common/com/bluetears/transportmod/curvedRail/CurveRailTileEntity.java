package com.bluetears.transportmod.curvedRail;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class CurveRailTileEntity extends TileEntity {
	
	public Packet132TileEntityData getDescriptionPacket(){
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }

public void onDataPacket(INetworkManager net, Packet132TileEntityData packet){
NBTTagCompound nbt = packet.customParam1;
if(nbt != null){
this.readFromNBT(nbt);
}
    }

public CurveRailTileEntity(){}

@Override
public void validate(){
super.validate();
}


public int getDirection(){
return worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
}

public void setDirection(int d){
worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, d, 2);
}

@Override
public AxisAlignedBB getRenderBoundingBox(){
return AxisAlignedBB.getAABBPool().getAABB(xCoord - 3, yCoord, zCoord - 3, xCoord + 4, yCoord + 7, zCoord + 4);
}

//@Override
//public double func_82115_m(){
//return 65536D;
//}

}