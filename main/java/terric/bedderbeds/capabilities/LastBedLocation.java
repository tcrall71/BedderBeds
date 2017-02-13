package terric.bedderbeds.capabilities;

import net.minecraft.util.math.BlockPos;

/**
 * Default implementation of ILastBedLocation
 */

public class LastBedLocation implements ILastBedLocation{

	private BlockPos lastBedLocation = new BlockPos(0, 0, 0);
		
	//sets last bed location
	@Override
	public void setLastBedLocation(BlockPos pos) {
		this.lastBedLocation = pos;
	}

	//gets last bed location
	@Override
	public BlockPos getLastBedLocation() {
		return this.lastBedLocation;
	}
}
