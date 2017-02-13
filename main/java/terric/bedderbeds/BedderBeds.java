package terric.bedderbeds;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import terric.bedderbeds.capabilities.ILastBedLocation;
import terric.bedderbeds.capabilities.ILastSleptInBag;
import terric.bedderbeds.capabilities.LastBedLocation;
import terric.bedderbeds.capabilities.LastBedLocationStorage;
import terric.bedderbeds.capabilities.LastSleptInBag;
import terric.bedderbeds.capabilities.LastSleptInBagStorage;
import terric.bedderbeds.events.CapabilityHandler;
import terric.bedderbeds.events.PlayerDied;
import terric.bedderbeds.events.PlayerSlept;
import terric.bedderbeds.init.ModBlocks;
import terric.bedderbeds.init.ModCrafting;
import terric.bedderbeds.init.ModItems;
import terric.bedderbeds.proxy.CommonProxy;
import terric.bedderbeds.tileentity.TileEntityBed_Colored;
import terric.bedderbeds.tileentity.TileEntitySleepingBag_Colored;


//Identifies the mod for Forge
@Mod(
		modid = Reference.MODID, 
		name = Reference.NAME, 
		version = Reference.VERSION, 
		acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)

public class BedderBeds {
	
	@Instance
	public static BedderBeds instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	// preInit Introduce the basics - declare new items, blocks
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //initialize blocks
        ModBlocks.init();
        ModBlocks.register();
        
        //initialize items
        ModItems.init();
    	ModItems.register();

		//initialize capabilities
    	CapabilityManager.INSTANCE.register(ILastSleptInBag.class, new LastSleptInBagStorage(), LastSleptInBag.class);
    	CapabilityManager.INSTANCE.register(ILastBedLocation.class, new LastBedLocationStorage(), LastBedLocation.class);
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerSlept());
        MinecraftForge.EVENT_BUS.register(new PlayerDied());

		//call sided things to register properly for client/server sides
    	proxy.preInit();
    }

    @EventHandler
    public void Init(FMLInitializationEvent event)
    {
        //initialize crafting recipes
    	ModCrafting.register();
    	
    	//initialize tile entities
    	GameRegistry.registerTileEntity(TileEntitySleepingBag_Colored.class, Reference.MODID + "TileEntitySleepingBag_Colored");
    	GameRegistry.registerTileEntity(TileEntityBed_Colored.class, Reference.MODID + "TileEntityBed_Colored");

    	//call sided things to register properly for client/server sides?
    	proxy.init();
    }
    
    @EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
    	//call sided things to register properly for client/server sides?
    	proxy.postInit();
    }
}
