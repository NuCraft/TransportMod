package com.bluetears.transportmod;


import com.bluetears.transportmod.blocks.AsphaltOre;
import com.bluetears.transportmod.blocks.CopperOre;
import com.bluetears.transportmod.blocks.CurveRail;
import com.bluetears.transportmod.blocks.CurvedRail;
import com.bluetears.transportmod.blocks.GuiltaliumOre;
import com.bluetears.transportmod.blocks.TinOre;
import com.bluetears.transportmod.curvedRail.CurveRailTileEntity;
import com.bluetears.transportmod.curvedRail.RenderCurveRail;
import com.bluetears.transportmod.items.AdvancedCircuit;
import com.bluetears.transportmod.items.Asphalt;
import com.bluetears.transportmod.items.SimpleCircuit;
import com.bluetears.transportmod.items.SimpleCombustionEngine;
import com.bluetears.transportmod.items.TestPlacement;
import com.bluetears.transportmod.items.boatparts.Anchor;
import com.bluetears.transportmod.items.boatparts.IronBoat;
import com.bluetears.transportmod.items.boatparts.IronBoatHull;
import com.bluetears.transportmod.items.boatparts.SimplePropeller;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
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
import net.minecraftforge.common.Configuration;

@Mod(modid="TransportMod", name="TransportMod", version="0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class TransportMod {

        // The instance of your mod that Forge uses.
        @Instance("TransportMod")
        public static TransportMod instance;
        
        
        //Calling of the 3 Creative Tabs and Config of them
        public static CreativeTabs transportmodtab = new CreativeTabs("transportmodtab") {
        	public ItemStack getIconItemStack() {
                	return new ItemStack(guiltaliumore);
        	}
    	};
        public static CreativeTabs monorailstab = new CreativeTabs("monorailstab") {
            public ItemStack getIconItemStack() {
                    return new ItemStack(Item.eyeOfEnder, 1, 0);
            }
        };
        public static CreativeTabs boatstab = new CreativeTabs("boatstab") {
        	public ItemStack getIconItemStack() {
                	return new ItemStack(anchor);
        	}
    	};
    	public static CreativeTabs trainstab = new CreativeTabs("trainstab") {
            public ItemStack getIconItemStack() {
                    return new ItemStack(Block.rail);
            }
        };
        public static CreativeTabs roadstab = new CreativeTabs("roadstab") {
        	public ItemStack getIconItemStack() {
                	return new ItemStack(Item.eyeOfEnder, 1, 0);
        	}
    	};
        
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="com.bluetears.transportmod.client.ClientProxy", serverSide="com.bluetears.transportmod.CommonProxy")
        public static CommonProxy proxy;
        
        @PreInit
        public void preInit(FMLPreInitializationEvent event) {
        	Configuration config = new Configuration(event.getSuggestedConfigurationFile());

            config.load();

            startBlockId = config.getBlock("Starting Block Id", 200).getInt();

            startItemId = config.getItem("Starting Item Id", 20000).getInt();

            // Since this flag is a boolean, we can read it into the variable directly from the config.
            tinOreGenFlag = config.get(Configuration.CATEGORY_GENERAL, "Tin Ore Generation Flag", false).getBoolean(true);
            copperOreGenFlag = config.get(Configuration.CATEGORY_GENERAL, "Copper Ore Generation Flag", false).getBoolean(true);
            
            



            config.save();
            
        }
        
        @Init
        
        public void load(FMLInitializationEvent event) {
        	
        	addBlocks();
        	addItems();
        	addWorldGens();
        	addNames();
        	addCrafting();
        	registerBlocks();

            
                proxy.registerRenderers();
        }
        

		@PostInit
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
		
		public static void addBlocks(){
        	curvedRail = new CurvedRail(startBlockId).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("curvedRail").setHardness(0.5F).setCreativeTab(CreativeTabs.tabTransport);
            asphaltore = new AsphaltOre(startBlockId+1, Material.iron);
            guiltaliumore = new GuiltaliumOre(startBlockId+2, Material.iron);
            tinore = new TinOre(startBlockId+3, Material.iron);
            copperore = new CopperOre(startBlockId+4, Material.iron);
            curveRail = new CurveRail(startBlockId+5, CurveRailTileEntity.class);
        	
        }
        
        public static void addItems(){
        	advancedcircuit = new AdvancedCircuit(startItemId).setMaxStackSize(64).setUnlocalizedName("advancedCircuit");
            simplecircuit = new SimpleCircuit(startItemId+1).setMaxStackSize(64).setUnlocalizedName("simpleCircuit");
            simplecombustionengine = new SimpleCombustionEngine(startItemId+2).setMaxStackSize(64).setUnlocalizedName("simpleCombustionEngine");
            anchor = new Anchor(startItemId+3).setMaxStackSize(64).setUnlocalizedName("anchor");
            simplepropeller = new SimplePropeller(startItemId+4).setMaxStackSize(64).setUnlocalizedName("propeller");
            ironboathull = new IronBoatHull(startItemId+5).setMaxStackSize(64).setUnlocalizedName("ironboathull");
            ironboat = new IronBoat(startItemId+6).setUnlocalizedName("ironboat");
            asphalt = new Asphalt(startItemId+7).setUnlocalizedName("asphalt").setMaxStackSize(64);
            testPlacement = new TestPlacement(startItemId+8, curveRail).setUnlocalizedName("placementTest");
        }
		
		public static void registerBlocks(){
			GameRegistry.registerBlock(curvedRail, "curvedRail");
			GameRegistry.registerBlock(asphaltore, "asphaltore");
			GameRegistry.registerBlock(tinore, "tintore");
			GameRegistry.registerBlock(copperore, "copperore");
			GameRegistry.registerBlock(guiltaliumore, "guiltaliumore");
			GameRegistry.registerBlock(curveRail, "curveRail");
		}
        
        
        public static void addNames(){
        
        	//Watch your language!
        	LanguageRegistry.addName(curveRail, "3x3 Curve");
        	LanguageRegistry.addName(curvedRail, "Curved Rail");
        	LanguageRegistry.addName(advancedcircuit, "Advanced Circuit");
        	LanguageRegistry.addName(simplecircuit, "Simple Circuit");
        	LanguageRegistry.addName(simplecombustionengine, "Simple Combustion Engine");
        	LanguageRegistry.addName(anchor, "Anchor");
        	LanguageRegistry.addName(simplepropeller, "Simple Propeller");
        	LanguageRegistry.addName(ironboathull, "Iron Boat Hull");
        	LanguageRegistry.addName(ironboat, "Iron Boat");
        	LanguageRegistry.addName(asphalt, "Asphalt");
        	LanguageRegistry.addName(asphaltore, "Asphalt Ore");
        	LanguageRegistry.addName(tinore, "Tin Ore");
        	LanguageRegistry.addName(copperore, "Copper Ore");
        	LanguageRegistry.addName(guiltaliumore, "Guiltalium Ore");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.monorailstab", "en_US", "Monorails");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.transportmodtab", "en_US", "Transport Mod");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.boatstab", "en_US", "Boats");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.trainstab", "en_US", "Trains");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.roadstab", "en_US", "Roads");
        	
        	ModLoader.registerTileEntity(CurveRailTileEntity.class, "CurveRail", new RenderCurveRail() );

        	
        }
        
        public static void addCrafting(){

        	GameRegistry.addRecipe(new ItemStack(simplecircuit), "rqr", "ici","sss", 
        	        'r',Item.redstone, 'q',Item.field_94583_ca,'s',Block.stone,'i',Item.ingotIron,'c',new ItemStack(Item.dyePowder,1,2));
        	GameRegistry.addRecipe(new ItemStack(advancedcircuit), "rqr", "gig","sss", 
        	        'r',Item.redstone, 'q',Item.field_94583_ca,'s',Item.ingotIron,'g',Item.ingotGold,'i',new ItemStack(Item.dyePowder,1,0));
        	GameRegistry.addRecipe(new ItemStack(advancedcircuit), " a ", "gsg"," i ", 
        	        's',Item.ingotIron,'g',Item.ingotGold,'i',new ItemStack(Item.dyePowder,1,0),'a',simplecircuit);
        	GameRegistry.addRecipe(new ItemStack(simplecombustionengine), "ipi", "i i","iii", 
        	        'i',Item.ingotIron,'p',Block.pistonBase);
        	GameRegistry.addRecipe(new ItemStack(ironboathull),"i i","iii","bbb",'i',Item.ingotIron,'b',new ItemStack(Item.potion,0));
			GameRegistry.addRecipe(new ItemStack(anchor)," i "," i ","iii",'i',Item.ingotIron);
			GameRegistry.addRecipe(new ItemStack(simplepropeller)," i "," i ","i i",'i',Item.ingotIron);
		}
        
        public static void addMinables(){
        	MinecraftForge.setBlockHarvestLevel(asphaltore, "pickaxe", 1);
        	MinecraftForge.setBlockHarvestLevel(guiltaliumore, "pickaxe", 3);
        	MinecraftForge.setBlockHarvestLevel(copperore, "pickaxe", 1);
        	MinecraftForge.setBlockHarvestLevel(tinore, "pickaxe", 2);
        }
        
        public static void addWorldGens(){
        	GameRegistry.registerWorldGenerator(new TransportModWorldGenerator(copperOreGenFlag,tinOreGenFlag));
        }
        
        //Variables for the Blocks used in the mod
        public static Block curvedRail;
        public static Block asphaltore;
        public static Block guiltaliumore;
        public static Block tinore;
        public static Block copperore;
        public static Block curveRail;
        
        //Variables for the items used in the mod
        public static Item advancedcircuit;
        public static Item simplecircuit;
        public static Item simplecombustionengine;
        public static Item anchor;
        public static Item simplepropeller;
        public static Item ironboathull;
        public static Item ironboat;
        public static Item asphalt;
        public static Item testPlacement;
        
        public static int startBlockId;
        public static int startItemId;
        public static boolean tinOreGenFlag;
        public static boolean copperOreGenFlag;
        
        }
        	
        


