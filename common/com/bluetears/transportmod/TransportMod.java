package com.bluetears.transportmod;



import com.bluetears.transportmod.blocks.Concrete;
import com.bluetears.transportmod.blocks.AsphaltOre;
import com.bluetears.transportmod.blocks.CopperOre;
import com.bluetears.transportmod.blocks.CurveRail;
import com.bluetears.transportmod.blocks.CurvedRail;
import com.bluetears.transportmod.blocks.GuiltaliumOre;
import com.bluetears.transportmod.blocks.TinOre;
import com.bluetears.transportmod.blocks.monorails.MonorailBeam;
import com.bluetears.transportmod.blocks.roads.AsphaltRoad;
import com.bluetears.transportmod.curvedRail.CurveRailTileEntity;
import com.bluetears.transportmod.curvedRail.RenderCurveRail;
import com.bluetears.transportmod.items.AdvancedCircuit;
import com.bluetears.transportmod.items.ConcreteSlurry;
import com.bluetears.transportmod.items.CopperIngot;
import com.bluetears.transportmod.items.Guiltalium;
import com.bluetears.transportmod.items.SimpleCircuit;
import com.bluetears.transportmod.items.SimpleCombustionEngine;
import com.bluetears.transportmod.items.TinIngot;
import com.bluetears.transportmod.items.boatparts.Anchor;
import com.bluetears.transportmod.items.boatparts.IronBoat;
import com.bluetears.transportmod.items.boatparts.IronBoatHull;
import com.bluetears.transportmod.items.boatparts.SimpleDriveShaft;
import com.bluetears.transportmod.items.boatparts.SimplePropeller;
import com.bluetears.transportmod.items.roads.Asphalt;
import com.bluetears.transportmod.items.roads.CondensedAsphalt;
import com.bluetears.transportmod.items.roads.HotAsphalt;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
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
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid="TransportMod", name="TransportMod", version="0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class TransportMod {

        // The instance of your mod that Forge uses.
        @Instance("TransportMod")
        public static TransportMod instance;
        
        
        //Calling of the 3 Creative Tabs and Config of them
        public static CreativeTabs transportmodtab = new CreativeTabs("transportmodtab") {
        	public ItemStack getIconItemStack() {
                	return new ItemStack(oreGuiltalium);
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

            startBlockId = config.getBlock("Starting Block Id", 500).getInt();

            startItemId = config.getItem("Starting Item Id", 20000).getInt();

            // Since this flag is a boolean, we can read it into the variable directly from the config.
            oreTinGenFlag = config.get(Configuration.CATEGORY_GENERAL, "Tin Ore Generation Flag", false).getBoolean(false);
            oreCopperGenFlag = config.get(Configuration.CATEGORY_GENERAL, "Copper Ore Generation Flag", false).getBoolean(false);
            
            



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
        	addOreDictionary();

            
                proxy.registerRenderers();
        }
        

		@PostInit
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
		
		public static void addBlocks(){
        	curvedRail = new CurvedRail(startBlockId).setUnlocalizedName("curvedRail").setHardness(0.5F).setCreativeTab(TransportMod.trainstab);
        	oreAsphalt = new AsphaltOre(startBlockId+1, Material.iron);
            oreGuiltalium = new GuiltaliumOre(startBlockId+2, Material.iron);
            oreTin = new TinOre(startBlockId+3, Material.iron);
            oreCopper = new CopperOre(startBlockId+4, Material.iron);
            curveRail = new CurveRail(startBlockId+5, CurveRailTileEntity.class);
            concrete = new Concrete(startBlockId+6,Material.iron);
            asphaltRoad = new AsphaltRoad(startBlockId+7,Material.iron);
            monorailBeam = new MonorailBeam(startBlockId+8);
        	
        }
        
        public static void addItems(){
        	advancedCircuit = new AdvancedCircuit(startItemId);
            simpleCircuit = new SimpleCircuit(startItemId+1);
            simpleCombustionEngine = new SimpleCombustionEngine(startItemId+2);
            anchor = new Anchor(startItemId+3);
            simplePropeller = new SimplePropeller(startItemId+4);
            ironBoatHull = new IronBoatHull(startItemId+5);
            ironBoat = new IronBoat(startItemId+6);
            asphalt = new Asphalt(startItemId+7);
            concreteSlurry = new ConcreteSlurry(startItemId+8);
            ingotCopper = new CopperIngot(startItemId+9);
            ingotTin = new TinIngot(startItemId+10);
            hotAsphalt = new HotAsphalt(startItemId+11);
            condensedAsphalt = new CondensedAsphalt(startItemId+12);
            guiltalium = new Guiltalium(startItemId+13);
            simpleDriveShaft = new SimpleDriveShaft(startItemId+14);
        }
		
		public static void registerBlocks(){
			GameRegistry.registerBlock(curvedRail, "curvedRail");
			GameRegistry.registerBlock(oreAsphalt, "oreAsphalt");
			GameRegistry.registerBlock(oreTin, "tintore");
			GameRegistry.registerBlock(oreCopper, "oreCopper");
			GameRegistry.registerBlock(oreGuiltalium, "oreGuiltalium");
			GameRegistry.registerBlock(curveRail, "curveRail");
			GameRegistry.registerBlock(concrete,"concrete");
			GameRegistry.registerBlock(asphaltRoad,"asphaltRoad");
			GameRegistry.registerBlock(monorailBeam,"monorailBeam");
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
        	LanguageRegistry.addName(oreAsphalt, "Asphalt Ore");
        	LanguageRegistry.addName(oreTin, "Tin Ore");
        	LanguageRegistry.addName(oreCopper, "Copper Ore");
        	LanguageRegistry.addName(oreGuiltalium, "Guiltalium Ore");
        	LanguageRegistry.addName(concrete, "Concrete");
        	LanguageRegistry.addName(concreteSlurry, "Concrete Slurry");
        	LanguageRegistry.addName(ingotTin,"Tin Ingot");
        	LanguageRegistry.addName(ingotCopper,"Copper Ingot");
        	LanguageRegistry.addName(hotAsphalt,"Hot Asphalt");
        	LanguageRegistry.addName(condensedAsphalt, "Condensed Asphalt");
        	LanguageRegistry.addName(asphaltRoad, "Asphalt Road");
        	LanguageRegistry.addName(monorailBeam, "Monorail Beam");
        	LanguageRegistry.addName(guiltalium,"Guiltalium");
        	LanguageRegistry.addName(simpleDriveShaft, "Simple Drive Shaft");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.monorailstab", "en_US", "Monorails");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.transportmodtab", "en_US", "Transport Mod");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.boatstab", "en_US", "Boats");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.trainstab", "en_US", "Trains");
        	LanguageRegistry.instance().addStringLocalization("itemGroup.roadstab", "en_US", "Roads");
        	
        	ModLoader.registerTileEntity(CurveRailTileEntity.class, "CurveRail", new RenderCurveRail() );
        	
        	

        	
        }
        
        public static void addCrafting(){
        	
        	//Furnace Recipes
        	GameRegistry.addSmelting(concreteSlurry.itemID, new ItemStack(concrete), 0.1f);
        	GameRegistry.addSmelting(oreCopper.blockID, new ItemStack(ingotCopper), 0.1f);
        	GameRegistry.addSmelting(oreTin.blockID, new ItemStack(ingotTin), 0.1f);
        	GameRegistry.addSmelting(condensedAsphalt.itemID,new ItemStack(hotAsphalt),0.1f);
        	
        	//Crafting Recipes
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
			GameRegistry.addRecipe(new ItemStack(concreteSlurry,8),"sgs","gcg","sgs",'c',Block.blockClay,'g',Block.gravel,'s', Block.stone );
			GameRegistry.addRecipe(new ItemStack(concreteSlurry,8),"gsg","scs","gsg",'c',Block.blockClay,'g',Block.gravel,'s', Block.stone );
			GameRegistry.addRecipe(new ItemStack(condensedAsphalt,8),"ggg","gag","ggg",'a',asphalt,'g',Block.gravel);
			GameRegistry.addRecipe(new ItemStack(asphaltRoad,8),"ccc","chc","ccc",'h',hotAsphalt,'c',concrete);
			GameRegistry.addShapelessRecipe(new ItemStack(curvedRail), new ItemStack(Block.rail));
			GameRegistry.addShapelessRecipe(new ItemStack(Block.rail), new ItemStack(curvedRail));
			GameRegistry.addRecipe(new ItemStack(monorailBeam,8)," c ","ici"," c ",'i',ingotCopper,'c',concrete);
			GameRegistry.addRecipe(new ItemStack(ironBoat),"ahm","  d","  p",'d',simpleDriveShaft,'m',simpleCombustionEngine,'a',anchor,'h',ironBoatHull,'p',simplePropeller);
		}
        
        public static void addMinables(){
        	MinecraftForge.setBlockHarvestLevel(oreAsphalt, "pickaxe", 1);
        	MinecraftForge.setBlockHarvestLevel(oreGuiltalium, "pickaxe", 3);
        	MinecraftForge.setBlockHarvestLevel(oreCopper, "pickaxe", 1);
        	MinecraftForge.setBlockHarvestLevel(oreTin, "pickaxe", 2);
        	MinecraftForge.setBlockHarvestLevel(curveRail, null, 0);
        	
        }
        
        public static void addWorldGens(){
        	GameRegistry.registerWorldGenerator(new TransportModWorldGenerator(oreCopperGenFlag,oreTinGenFlag));
        }
        
        public static void addOreDictionary(){
        	OreDictionary.registerOre("ingotCopper", new ItemStack(ingotCopper));
        	OreDictionary.registerOre("ingotTin", new ItemStack(ingotTin));
        	OreDictionary.registerOre("oreCopper", new ItemStack(oreCopper));
        	OreDictionary.registerOre("oreGuiltalium", new ItemStack(oreGuiltalium));
        	OreDictionary.registerOre("oreAsphalt", new ItemStack(oreAsphalt));
        	OreDictionary.registerOre("oreTin", new ItemStack(oreTin));
        	OreDictionary.registerOre("itemAsphalt", new ItemStack(asphalt));
			
		}
        
        //Variables for the Blocks used in the mod
        public static Block curvedRail;
        public static Block oreAsphalt;
        public static Block oreGuiltalium;
        public static Block oreTin;
        public static Block oreCopper;
        public static Block curveRail;
        public static Block concrete;
        public static Block asphaltRoad;
        public static Block monorailBeam;
        
        //Variables for the items used in the mod
        public static Item advancedCircuit;
        public static Item simpleCircuit;
        public static Item simpleCombustionEngine;
        public static Item anchor;
        public static Item simplePropeller;
        public static Item ironBoatHull;
        public static Item ironBoat;
        public static Item asphalt;
        public static Item concreteSlurry;
        public static Item ingotCopper;
        public static Item ingotTin;
        public static Item condensedAsphalt;
        public static Item hotAsphalt;
        public static Item guiltalium;
        public static Item simpleDriveShaft;
        
        public static int startBlockId;
        public static int startItemId;
        public static boolean oreTinGenFlag;
        public static boolean oreCopperGenFlag;
        
        }

		
        	
        


