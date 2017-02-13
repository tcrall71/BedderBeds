package terric.bedderbeds.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class LastSleptInBagStorage implements IStorage<ILastSleptInBag>{

/**
 * This class is responsible for saving and reading last-slept-in-bag flag from or to server
 */
	
	@Override
	public NBTBase writeNBT(Capability<ILastSleptInBag> capability, ILastSleptInBag instance, EnumFacing side) {
		//stores boolean as byte
		Byte getBagByte = null;
		if(instance.getBag()) {
			getBagByte = 1;
		} else {
			getBagByte = 0;
		}
		return new NBTTagByte(getBagByte);
	}

	@Override
	public void readNBT(Capability<ILastSleptInBag> capability, ILastSleptInBag instance, EnumFacing side,
			NBTBase nbt) {
		//converts byte to boolean
		Byte setBagByte = ((NBTPrimitive)nbt).getByte();
		if(setBagByte == 1) {
			instance.setBag(true);
		} else {
			instance.setBag(false);
		}
	}

}
