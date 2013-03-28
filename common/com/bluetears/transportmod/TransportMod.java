package com.bluetears.transportmod;


import com.bluetears.transportmod.items.AdvancedCircuit;
import com.bluetears.transportmod.items.SimpleCircuit;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;


import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="TransportMod", name="TransportMod", version="0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class TransportMod {

        // The instance of your mod that Forge uses.
        @Instance("TransportMod")
        public static TransportMod instance;
        
        //Below is the code for generating all of the blocks
        
        
        //Below is the code for generating all of the items
        public final static Item advancedcircuit = new AdvancedCircuit(5001).setMaxStackSize(64).setUnlocalizedName("advancedCircuit");
        public final static Item simplecircuit = new SimpleCircuit(5002).setMaxStackSize(64).setUnlocalizedName("simpleCircuit");
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="com.bluetears.transportmod.client.ClientProxy", serverSide="com.bluetears.transportmod.CommonProxy")
        public static CommonProxy proxy;
        
        @PreInit
        public void preInit(FMLPreInitializationEvent event) {
                // Stub Method
        }
        
        @Init
        
        public void load(FMLInitializationEvent event) {
        	
        	addNames();
        	addCrafting();

            
                proxy.registerRenderers();
        }
        

		@PostInit
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
        
        
        public static void addNames(){
        
        	LanguageRegistry.addName(advancedcircuit, "Advanced Circuit");
        	LanguageRegistry.addName(simplecircuit, "Simple Circuit");
        	
        }
        
        private void addCrafting(){

        	GameRegistry.addRecipe(new ItemStack(simplecircuit), "rqr", "ici","sss", 
        	        'r',Item.redstone, 'q',Item.field_94583_ca,'s',Block.stone,'i',Item.ingotIron,'c',new ItemStack(Item.dyePowder,1,2));
        	GameRegistry.addRecipe(new ItemStack(advancedcircuit), "rqr", "gig","sss", 
        	        'r',Item.redstone, 'q',Item.field_94583_ca,'s',Item.ingotIron,'g',Item.ingotGold,'i',new ItemStack(Item.dyePowder,1,0));
        	GameRegistry.addRecipe(new ItemStack(Block.anvil), "ipi", "i i","iii", 
        	        'i',Item.ingotIron,'p',Block.pistonBase);
			
		}
}


