package terric.bedderbeds.init;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import terric.bedderbeds.blocks.BlockBed_Colored;
import terric.bedderbeds.blocks.BlockSleepingBag_Colored;

public class ModBlocks {

	//declare ModBlocks array
	public static Block[] modBlockArray;
	public static int numberModBlocks;
	
	public static Block blocksleepingbag_colored;
	public static Block blockbed_colored;
	
	public static void init() {
		blocksleepingbag_colored = new BlockSleepingBag_Colored();
		blockbed_colored = new BlockBed_Colored();

		//fill ModBlocks array
		numberModBlocks = 2;
		modBlockArray = new Block[numberModBlocks];
		modBlockArray[0] = blocksleepingbag_colored;
		modBlockArray[1] = blockbed_colored;
}
	
	public static void register() {
		//register all ModBlocks
		for (int i = 0; i < numberModBlocks; i++) {
			GameRegistry.register(modBlockArray[i]);
		}
	}
	
	//called from client proxy
	public static void registerRenders() {
		//register renders for all ModBlocks
		for (int i = 0; i < numberModBlocks; i++) {
			registerRender(modBlockArray[i]);
		}
	}
	
	private static void registerRender(Block block) {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		//itemBlocks
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
		System.out.println("REGISTRY NAME >> " + block.getRegistryName());
	}

}
