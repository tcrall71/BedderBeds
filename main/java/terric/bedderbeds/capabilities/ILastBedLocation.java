package terric.bedderbeds.capabilities;

import net.minecraft.util.math.BlockPos;

public interface ILastBedLocation {

	public void setLastBedLocation(BlockPos pos);
	public BlockPos getLastBedLocation();

}
