package terric.bedderbeds.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import terric.bedderbeds.Reference;
import terric.bedderbeds.items.ItemBed_Colored;
import terric.bedderbeds.items.ItemRolledSleepingBag_Colored;

public class ModItems {

	//declare array of all ModItems
	public static Item[] modItemArray;
	public static int numberModItems;
	
	public static Item rolledsleepingbag_colored;
	public static Item bed_colored;
	
	
	public static void init() {
		rolledsleepingbag_colored = new ItemRolledSleepingBag_Colored();
		bed_colored = new ItemBed_Colored();
		
		//fill ModItems array
		//sleeping bag must be 0-15
		numberModItems = 2;
		modItemArray = new Item[numberModItems];
		modItemArray[0] = bed_colored;
		modItemArray[1] = rolledsleepingbag_colored;
	}
	
	public static void register() {
		//register all ModItems
		for (int i = 0; i < numberModItems; i++) {
			GameRegistry.register(modItemArray[i]);
		}
	}
	
	//called from client proxy
	public static void registerVariants() {
  		//set locations for bed models
  		ModelResourceLocation[] bedVariantNames = new ModelResourceLocation[16];
  		for (int i = 0; i < 16; i++) {
  			bedVariantNames[i] = new ModelResourceLocation(Reference.MODID + ":" + ItemBed_Colored.getNameFromMeta(i), "inventory");
System.out.println(i + ": " + bedVariantNames[i]);
  		}
  		registerColorVariants(bed_colored, bedVariantNames);

  		//set locations for sleeping bag models
  		ModelResourceLocation[] bagVariantNames = new ModelResourceLocation[16];
  		for (int i = 0; i < 16; i++) {
  			bagVariantNames[i] = new ModelResourceLocation(Reference.MODID + ":" + ItemRolledSleepingBag_Colored.getNameFromMeta(i), "inventory");
System.out.println(i + ": " + bagVariantNames[i]);
  		}
  		registerColorVariants(rolledsleepingbag_colored, bagVariantNames);
  	}

  	//called from client proxy
	public static void registerRenders() {
		//register renders for all ModItems
		for (int i = 0; i < numberModItems; i++) {
			registerRender(modItemArray[i]);
		}
	}
	
  	private static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		System.out.println("REGISTRY NAME >> " + item.getRegistryName());
	}
  	
  	private static void registerColorVariants(Item item, ModelResourceLocation resLoc[]) {
  		//register item variant models
  		ModelBakery.registerItemVariants(item, resLoc[0], resLoc[1], resLoc[2], resLoc[3], resLoc[4], resLoc[5], resLoc[6], resLoc[7], resLoc[8], resLoc[9], resLoc[10], resLoc[11], resLoc[12], resLoc[13], resLoc[14], resLoc[15]);
  		for (int i = 0; i < 16; i++) {
  			ModelLoader.setCustomModelResourceLocation(item, i, resLoc[i]);
  		}
  	}
}
