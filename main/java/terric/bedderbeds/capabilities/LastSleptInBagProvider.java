package terric.bedderbeds.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class LastSleptInBagProvider implements ICapabilitySerializable<NBTBase>{
	@CapabilityInject(ILastSleptInBag.class)
	public static final Capability<ILastSleptInBag> LAST_SLEPT_IN_BAG_CAP = null;
	private ILastSleptInBag instance = LAST_SLEPT_IN_BAG_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == LAST_SLEPT_IN_BAG_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == LAST_SLEPT_IN_BAG_CAP ? LAST_SLEPT_IN_BAG_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return LAST_SLEPT_IN_BAG_CAP.getStorage().writeNBT(LAST_SLEPT_IN_BAG_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		LAST_SLEPT_IN_BAG_CAP.getStorage().readNBT(LAST_SLEPT_IN_BAG_CAP, this.instance, null, nbt);
		
	}

}
