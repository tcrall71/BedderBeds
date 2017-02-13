package terric.bedderbeds.init;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import terric.bedderbeds.Reference;
import terric.bedderbeds.tileentity.TileEntityBed_Colored;
import terric.bedderbeds.tileentity.TileEntitySleepingBag_Colored;

public class ModTileEntities {

	public static TileEntity tileentitysleepingbag;
	public static TileEntity tileentitybed;
	
	public static void init() {
		tileentitysleepingbag = new TileEntitySleepingBag_Colored();
		tileentitybed = new TileEntityBed_Colored();
	}
	
	public static void register() {
		//register all ModTileEntities
		GameRegistry.registerTileEntity(TileEntitySleepingBag_Colored.class, Reference.MODID + "TileEntitySleepingBag");
		GameRegistry.registerTileEntity(TileEntityBed_Colored.class, Reference.MODID + "TileEntityBed");
	}
}
