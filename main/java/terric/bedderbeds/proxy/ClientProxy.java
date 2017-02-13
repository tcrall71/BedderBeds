package terric.bedderbeds.proxy;

import java.io.File;

import terric.bedderbeds.init.ModBlocks;
import terric.bedderbeds.init.ModItems;

public class ClientProxy implements CommonProxy{

	@Override
	public void preInit() {
		ModItems.registerVariants();
	}
	
    @Override
	public void init() {
    	ModBlocks.registerRenders();
		ModItems.registerRenders();
	}

	@Override
	public void postInit() {
	}

}
