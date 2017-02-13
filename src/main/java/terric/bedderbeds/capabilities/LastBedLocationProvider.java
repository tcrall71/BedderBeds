package terric.bedderbeds.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class LastBedLocationProvider implements ICapabilitySerializable<NBTBase> {
	@CapabilityInject(ILastBedLocation.class)
	public static final Capability<ILastBedLocation> LAST_BED_LOCATION_CAP = null;
	private ILastBedLocation instance = LAST_BED_LOCATION_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == LAST_BED_LOCATION_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == LAST_BED_LOCATION_CAP ? LAST_BED_LOCATION_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return LAST_BED_LOCATION_CAP.getStorage().writeNBT(LAST_BED_LOCATION_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		LAST_BED_LOCATION_CAP.getStorage().readNBT(LAST_BED_LOCATION_CAP, this.instance, null, nbt);
		
	}

}
