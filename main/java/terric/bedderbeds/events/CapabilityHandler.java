package terric.bedderbeds.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import terric.bedderbeds.Reference;
import terric.bedderbeds.capabilities.LastBedLocationProvider;
import terric.bedderbeds.capabilities.LastSleptInBagProvider;

/**
 * This class is responsible for attaching capabilities
 */

public class CapabilityHandler {
	public static final ResourceLocation LAST_SLEPT_IN_BAG_CAP = new ResourceLocation(Reference.MODID, "last_slept_in_bag");
	public static final ResourceLocation LAST_BED_LOCATION_CAP = new ResourceLocation(Reference.MODID, "last_bed_location");
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent.Entity event) {
		//don't do this unless entity is player
		if (event.getEntity() instanceof EntityPlayer) {
			//attach capability to player
			event.addCapability(LAST_SLEPT_IN_BAG_CAP, new LastSleptInBagProvider());
			event.addCapability(LAST_BED_LOCATION_CAP, new LastBedLocationProvider());
		}
	}
}
