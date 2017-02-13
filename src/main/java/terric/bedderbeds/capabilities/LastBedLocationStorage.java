package terric.bedderbeds.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class LastBedLocationStorage implements IStorage<ILastBedLocation>{

	/**
	 * This class is responsible for saving and reading last bed position from or to server
	 */
		
	@Override
	public NBTBase writeNBT(Capability<ILastBedLocation> capability, ILastBedLocation instance, EnumFacing side) {
		BlockPos lastBedPos = null;
		NBTTagCompound lastBedPosNBT = null;
		if(instance.getLastBedLocation() != null) {
			lastBedPos = instance.getLastBedLocation();
			lastBedPosNBT = NBTUtil.createPosTag(lastBedPos);
		}
		return lastBedPosNBT;
	}

	@Override
	public void readNBT(Capability<ILastBedLocation> capability, ILastBedLocation instance, EnumFacing side, NBTBase nbt) {
		BlockPos lastBedPos = null;
		if(nbt != null) {
			lastBedPos = NBTUtil.getPosFromTag((NBTTagCompound) nbt);
			instance.setLastBedLocation(lastBedPos);
		}
	}
}
