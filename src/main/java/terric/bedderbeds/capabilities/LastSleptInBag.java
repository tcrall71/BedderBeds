package terric.bedderbeds.capabilities;

/**
 * Default implementation of ILastSleptInBag
 */

public class LastSleptInBag implements ILastSleptInBag{

	private boolean lastSleptInBag = false;
	
	//sets last-slept-in-bag flag to true/false
	@Override
	public void setBag(boolean wasBag) {
		this.lastSleptInBag = wasBag;
		
	}

	//gets boolean value of last-slept-in-bag flag
	@Override
	public boolean getBag() {
		return this.lastSleptInBag;
		
	}

}
