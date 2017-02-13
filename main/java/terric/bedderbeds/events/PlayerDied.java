package terric.bedderbeds.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import terric.bedderbeds.capabilities.ILastBedLocation;
import terric.bedderbeds.capabilities.ILastSleptInBag;
import terric.bedderbeds.capabilities.LastBedLocationProvider;
import terric.bedderbeds.capabilities.LastSleptInBagProvider;

public class PlayerDied {
	//when a player dies

	//use last bed location if slept in a sleeping bag but bag no longer available
	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent event) {
		//if entity is player
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			
			//if player last slept in sleeping bag and has a bed position saved
			ILastSleptInBag lastSleptInBagCap = player.getCapability(LastSleptInBagProvider.LAST_SLEPT_IN_BAG_CAP, null);
			Boolean lastSleptInBag = lastSleptInBagCap.getBag();
			ILastBedLocation lastBedLocCap = player.getCapability(LastBedLocationProvider.LAST_BED_LOCATION_CAP, null);
			BlockPos lastBedPos = lastBedLocCap.getLastBedLocation();
			if (lastSleptInBag && lastBedPos != new BlockPos(0, 0, 0)) {

				//if no safe spawn location near bag, set LastSleptInBag to false and use LastBedLocation
				BlockPos safeSpawn = player.getBedSpawnLocation(player.worldObj, player.getBedLocation(), false);
				if (safeSpawn == null) {
					lastSleptInBagCap.setBag(false);
					player.setSpawnPoint(lastBedPos, false);
				}
			}
			//continue on to actual spawn of player normally from here
		}
	}

	//make sleeping bag capabilities persist upon death
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {
		//if player died (as opposed to moved spawned into different world)
		if (event.isWasDeath()) {
			EntityPlayer dyingPlayer = event.getOriginal();
			EntityPlayer spawningPlayer = event.getEntityPlayer();

			//copy LastBedLocation and LastSleptInBag to "new" player so that data will persist
			ILastBedLocation lastBedLocCapDying = dyingPlayer.getCapability(LastBedLocationProvider.LAST_BED_LOCATION_CAP, null);
			ILastBedLocation lastBedLocCapSpawning = spawningPlayer.getCapability(LastBedLocationProvider.LAST_BED_LOCATION_CAP, null);
			lastBedLocCapSpawning.setLastBedLocation(lastBedLocCapDying.getLastBedLocation());
			
			ILastSleptInBag lastSleptInBagCapDying = dyingPlayer.getCapability(LastSleptInBagProvider.LAST_SLEPT_IN_BAG_CAP, null);
			ILastSleptInBag lastSleptInBagCapSpawning = spawningPlayer.getCapability(LastSleptInBagProvider.LAST_SLEPT_IN_BAG_CAP, null);
			lastSleptInBagCapSpawning.setBag(lastSleptInBagCapDying.getBag());
		}
	}
	

}
