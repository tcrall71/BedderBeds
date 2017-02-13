package terric.bedderbeds.events;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import terric.bedderbeds.capabilities.ILastBedLocation;
import terric.bedderbeds.capabilities.ILastSleptInBag;
import terric.bedderbeds.capabilities.LastBedLocationProvider;
import terric.bedderbeds.capabilities.LastSleptInBagProvider;
import terric.bedderbeds.init.ModBlocks;

public class PlayerSlept {
	//when a player wakes up, check the type of bed slept in and update sleeping flags
	@SubscribeEvent
	public void onPlayerWakeUp(PlayerWakeUpEvent event) {
		EntityPlayer player = event.getEntityPlayer();
		World world = player.worldObj;
		//do only server side, stop here if client side
		if (world.isRemote) return;
		
		BlockPos currentBedLocation = player.bedLocation;
		IBlockState lastSleptHere = world.getBlockState(currentBedLocation); 
		ILastBedLocation lastBedLocCap = null;
		ILastSleptInBag lastSleptInBagCap = null;
		lastBedLocCap = player.getCapability(LastBedLocationProvider.LAST_BED_LOCATION_CAP, null);
		lastSleptInBagCap = player.getCapability(LastSleptInBagProvider.LAST_SLEPT_IN_BAG_CAP, null);

		//if bed, copy the location and set the last-slept-in-bag flag to false
		if (lastSleptHere.getBlock() == Blocks.BED ||
				lastSleptHere.getBlock() == ModBlocks.blockbed_colored) {
			lastBedLocCap.setLastBedLocation(currentBedLocation);
			lastSleptInBagCap.setBag(false);
		}

		//if sleeping bag, set the last-slept-in-bag flag to true, copy last bed location if necessary on first use
		if (lastSleptHere.getBlock() == ModBlocks.blocksleepingbag_colored) {
			//if last bed location Z is 0 (can't be true for vanilla bed) but there is a last bed (vanilla) saved, and player did not last sleep in sleeping bag,
			//assume this is the first use of the mod and save the last bed location to the capability before recording the sleeping bag
			if (lastBedLocCap.getLastBedLocation().getY() == 0 && player.getBedLocation() != null && lastSleptInBagCap.getBag() == false) {
				lastBedLocCap.setLastBedLocation(player.getBedLocation());
			}
			//set the last-slept-in-bag flag to true
			lastSleptInBagCap = player.getCapability(LastSleptInBagProvider.LAST_SLEPT_IN_BAG_CAP, null);
			lastSleptInBagCap.setBag(true);
		}
	}
}
