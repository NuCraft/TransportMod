package com.bluetears.transportmod;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class TransportModWorldGenerator implements IWorldGenerator{
	
	private boolean copperOreGen;
	private boolean tinOreGen;
	
	TransportModWorldGenerator(boolean copperOreGen,boolean tinOreGen){
		copperOreGen = this.copperOreGen;
		tinOreGen = this.tinOreGen;
		
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
		case -1:
		    generateNether(world, random, chunkX * 16, chunkZ * 16);
		    break;
		case 0:
		    generateSurface(world, random, chunkX * 16, chunkZ * 16);
		    break;
		case 1:
		    generateEnd(world, random, chunkX * 16, chunkZ * 16);
		    break;
		}
		
	}
	
	private void generateEnd(World world, Random random, int chunkX, int chunkZ) {
		
		
		
	}

	private void generateSurface(World world, Random random, int chunkX, int chunkZ) {
		
		
		for(int k = 0; k < 10; k++){
        	int firstBlockXCoord = chunkX + random.nextInt(16);
        	int firstBlockYCoord = random.nextInt(32)+32;
        	int firstBlockZCoord = chunkZ + random.nextInt(16);
        	
        	(new WorldGenMinable(TransportMod.asphaltOre.blockID, 8)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
        }
		
		
		for(int k = 0; k < 3; k++){
        	int firstBlockXCoord = chunkX + random.nextInt(16);
        	int firstBlockYCoord = random.nextInt(20);
        	int firstBlockZCoord = chunkZ + random.nextInt(16);
        	
        	(new WorldGenMinable(TransportMod.guiltaliumOre.blockID, 4)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
        }
		
		if(copperOreGen){
		for(int k = 0; k < 20; k++){
        	int firstBlockXCoord = chunkX + random.nextInt(16);
        	int firstBlockYCoord = random.nextInt(32)+32;
        	int firstBlockZCoord = chunkZ + random.nextInt(16);
        	
        	(new WorldGenMinable(TransportMod.copperOre.blockID, 8)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
        }
		}
		else{}
		
		if(tinOreGen){
		for(int k = 0; k < 20; k++){
        	int firstBlockXCoord = chunkX + random.nextInt(16);
        	int firstBlockYCoord = random.nextInt(32)+32;
        	int firstBlockZCoord = chunkZ + random.nextInt(16);
        	
        	(new WorldGenMinable(TransportMod.tinOre.blockID, 6)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
        }
		
		}
		else{}
		
		
		
        
	}

	private void generateNether(World world, Random random, int i, int j) {
		
	}

}
