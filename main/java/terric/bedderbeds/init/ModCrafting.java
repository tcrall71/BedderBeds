package terric.bedderbeds.init;

import java.util.Iterator;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {
	public static void register() {

		//remove bed recipe
		Iterator<IRecipe> iterator = CraftingManager.getInstance().getRecipeList().iterator();
		while (iterator.hasNext())
		{
		    IRecipe recipe = iterator.next();
		    if (recipe != null) {
		    	ItemStack output = recipe.getRecipeOutput();
			    if (output != null && output.getItem() == Items.BED)
			     iterator.remove();
		    }
		}
		
		//Sleeping bags made from wool and feathers
		for (int color = 0; color < 16; color++) {
			GameRegistry.addShapedRecipe (
				new ItemStack(ModItems.rolledsleepingbag_colored, 1, color),
				"FFF",
				"FFF",
				"WWW",
				'W', new ItemStack(Blocks.WOOL, 1, color),
				'F', Items.FEATHER
			);
		}
		
		//Default sleeping bags for multiple colors of wool
		GameRegistry.addShapedRecipe (
			new ItemStack(ModItems.rolledsleepingbag_colored, 1, 8), //silver
			"FFF",
			"FFF",
			"WWW",
			'W', Blocks.WOOL,
			'F', Items.FEATHER
		);

		//Sleeping bags dyed a new color
		for (int newColor = 0; newColor < 16; newColor++) {
			for (int originalColor = 0; originalColor < 16; originalColor++) {
				int dye = 15 - newColor; //dye meta is reversed
				if (originalColor != newColor ) { //only add recipe if new color is different from old
					GameRegistry.addShapelessRecipe	(
						new ItemStack(ModItems.rolledsleepingbag_colored, 1, newColor),
						new ItemStack(ModItems.rolledsleepingbag_colored, 1, originalColor),
						new ItemStack(Items.DYE, 1, dye)
					);
				}
			}
		}

		//Beds made from wool and wood
		for (int color = 0; color < 16; color++) {
			GameRegistry.addShapedRecipe (
				new ItemStack(ModItems.bed_colored, 1, color),
				"LLL",
				"DDD",
				'L', new ItemStack(Blocks.WOOL, 1, color),
				'D', Blocks.PLANKS
			);
		}
		
		//Default beds for multiple colors of wool 
		for (int color = 0; color < 16; color++) {
			GameRegistry.addShapedRecipe (
				new ItemStack(ModItems.bed_colored, 1, 8), //silver
				"LLL",
				"DDD",
				'L', Blocks.WOOL,
				'D', Blocks.PLANKS
			);
		}
		
		//Beds dyed a new color
		for (int newColor = 0; newColor < 16; newColor++) {
			for (int originalColor = 0; originalColor < 16; originalColor++) {
				int dye = 15 - newColor; //dye meta is reversed
				if (originalColor != newColor ) { //only add recipe if new color is different from old
					GameRegistry.addShapelessRecipe	(
						new ItemStack(ModItems.bed_colored, 1, newColor),
						new ItemStack(ModItems.bed_colored, 1, originalColor),
						new ItemStack(Items.DYE, 1, dye)
					);
				}
			}
		}

		//Vanilla beds dyed a new color (change forever to bed_colored!)
		for (int newColor = 0; newColor < 16; newColor++) {
			int dye = 15 - newColor; //dye meta is reversed
			GameRegistry.addShapelessRecipe	(
				new ItemStack(ModItems.bed_colored, 1, newColor),
				Items.BED,
				new ItemStack(Items.DYE, 1, dye)
			);
		}
	}
}
