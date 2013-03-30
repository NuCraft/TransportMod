package com.bluetears.transportmod;


import com.bluetears.transportmod.blocks.AsphaltOre;
import com.bluetears.transportmod.blocks.Concrete;
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
                	return new ItemStack(guiltaliumOre);
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
                	return new ItemStack(concrete, 1, 0);
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
            asphaltOre = new AsphaltOre(startBlockId+1, Material.iron);
            guiltaliumOre = new GuiltaliumOre(startBlockId+2, Material.iron);
            tinOre = new TinOre(startBlockId+3, Material.iron);
            copperOre = new CopperOre(startBlockId+4, Material.iron);
            curveRail = new CurveRail(startBlockId+5, CurveRailTileEntity.class);
            concrete = new Concrete(startBlockId+6,Material.iron);
        	
        }
        
        public static void addItems(){
        	advancedCircuit = new AdvancedCircuit(startItemId).setMaxStackSize(64).setUnlocalizedName("advancedCircuit");
            simpleCircuit = new SimpleCircuit(startItemId+1).setMaxStackSize(64).setUnlocalizedName("simpleCircuit");
            simpleCombustionEngine = new SimpleCombustionEngine(startItemId+2).setMaxStackSize(64).setUnlocalizedName("simpleCombustionEngine");
            anchor = new Anchor(startItemId+3).setMaxStackSize(64).setUnlocalizedName("anchor");
            simplePropeller = new SimplePropeller(startItemId+4).setMaxStackSize(64).setUnlocalizedName("propeller");
            ironBoatHull = new IronBoatHull(startItemId+5).setMaxStackSize(64).setUnlocalizedName("ironboathull");
            ironBoat = new IronBoat(startItemId+6).setUnlocalizedName("ironboat");
            asphalt = new Asphalt(startItemId+7).setUnlocalizedName("asphalt").setMaxStackSize(64);
            testPlacement = new TestPlacement(startItemId+8, curveRail).setUnlocalizedName("placementTest");
        }
		
		public static void registerBlocks(){
			GameRegistry.registerBlock(curvedRail, "curvedRail");
			GameRegistry.registerBlock(asphaltOre, "asphaltore");
			GameRegistry.registerBlock(tinOre, "tintore");
			GameRegistry.registerBlock(copperOre, "copperore");
			GameRegistry.registerBlock(guiltaliumOre, "guiltaliumore");
			GameRegistry.registerBlock(curveRail, "curveRail");
			GameRegistry.registerBlock(concrete,"concrete");
		}
        
        
        public static void addNames(){
        
        	//Watch your language!
        	LanguageRegistry.addName(curveRail, "3x3 Curve");
        	LanguageRegistry.addName(curvedRail, "Curved Rail");
        	LanguageRegistry.addName(advancedCircuit, "Advanced Circuit");
        	LanguageRegistry.addName(simpleCircuit, "Simple Circuit");
        	LanguageRegistry.addName(simpleCombustionEngine, "Simple Combustion Engine");
        	LanguageRegistry.addName(anchor, "Anchor");
        	LanguageRegistry.addName(simplePropeller, "Simple Propeller");
        	LanguageRegistry.addName(ironBoatHull, "Iron Boat Hull");
        	LanguageRegistry.addName(ironBoat, "Iron Boat");
        	LanguageRegistry.addName(asphalt, "Asphalt");
        	LanguageRegistry.addName(asphaltOre, "Asphalt Ore");
        	LanguageRegistry.addName(tinOre, "Tin Ore");
        	LanguageRegistry.addName(copperOre, "Copper Ore");
        	LanguageRegistry.addName(guiltaliumOre, "Guiltalium Ore");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.monorailstab", "en_US", "Monorails");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.transportmodtab", "en_US", "Transport Mod");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.boatstab", "en_US", "Boats");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.trainstab", "en_US", "Trains");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.roadstab", "en_US", "Roads");
        	
        	ModLoader.registerTileEntity(CurveRailTileEntity.class, "CurveRail", new RenderCurveRail() );

        	
        }
        
        public static void addCrafting(){

        	GameRegistry.addRecipe(new ItemStack(simpleCircuit), "rqr", "ici","sss", 
        	        'r',Item.redstone, 'q',Item.field_94583_ca,'s',Block.stone,'i',Item.ingotIron,'c',new ItemStack(Item.dyePowder,1,2));
        	GameRegistry.addRecipe(new ItemStack(advancedCircuit), "rqr", "gig","sss", 
        	        'r',Item.redstone, 'q',Item.field_94583_ca,'s',Item.ingotIron,'g',Item.ingotGold,'i',new ItemStack(Item.dyePowder,1,0));
        	GameRegistry.addRecipe(new ItemStack(advancedCircuit), " a ", "gsg"," i ", 
        	        's',Item.ingotIron,'g',Item.ingotGold,'i',new ItemStack(Item.dyePowder,1,0),'a',simpleCircuit);
        	GameRegistry.addRecipe(new ItemStack(simpleCombustionEngine), "ipi", "i i","iii", 
        	        'i',Item.ingotIron,'p',Block.pistonBase);
        	GameRegistry.addRecipe(new ItemStack(ironBoatHull),"i i","iii","bbb",'i',Item.ingotIron,'b',new ItemStack(Item.potion,0));
			GameRegistry.addRecipe(new ItemStack(anchor)," i "," i ","iii",'i',Item.ingotIron);
			GameRegistry.addRecipe(new ItemStack(simplePropeller)," i "," i ","i i",'i',Item.ingotIron);
		}
        
        public static void addMinables(){
        	MinecraftForge.setBlockHarvestLevel(asphaltOre, "pickaxe", 1);
        	MinecraftForge.setBlockHarvestLevel(guiltaliumOre, "pickaxe", 3);
        	MinecraftForge.setBlockHarvestLevel(copperOre, "pickaxe", 1);
        	MinecraftForge.setBlockHarvestLevel(tinOre, "pickaxe", 2);
        }
        
        public static void addWorldGens(){
        	GameRegistry.registerWorldGenerator(new TransportModWorldGenerator(copperOreGenFlag,tinOreGenFlag));
        }
        
        //Variables for the Blocks used in the mod
        public static Block curvedRail;
        public static Block asphaltOre;
        public static Block guiltaliumOre;
        public static Block tinOre;
        public static Block copperOre;
        public static Block curveRail;
        public static Block concrete;
        
        //Variables for the items used in the mod
        public static Item advancedCircuit;
        public static Item simpleCircuit;
        public static Item simpleCombustionEngine;
        public static Item anchor;
        public static Item simplePropeller;
        public static Item ironBoatHull;
        public static Item ironBoat;
        public static Item asphalt;
        public static Item testPlacement;
        
        public static int startBlockId;
        public static int startItemId;
        public static boolean tinOreGenFlag;
        public static boolean copperOreGenFlag;
        
        }
        	
        


