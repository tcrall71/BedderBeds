package terric.bedderbeds;

public class Reference {

	public static final String MODID="terric_bedderbeds";
	public static final String NAME="BedderBeds";
	public static final String VERSION="1.10.2-2.1";
	public static final String ACCEPTED_VERSIONS="[1.10.2]";

	public static final String CLIENT_PROXY_CLASS="terric.bedderbeds.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS="terric.bedderbeds.proxy.ServerProxy";

	public static enum BedderBedItems{
		ROLLEDSLEEPINGBAG_COLORED(MODID + "_rolledsleepingbag", "ItemRolledSleepingBag_Colored"),
		BED_COLORED(MODID + "_bed", "ItemBed_Colored");

		private String unlocalizedName; //default name for unlocalized use, unique
		private String registryName; //model json file
		
		BedderBedItems(String unlocalizedName, String registryName){
			this.unlocalizedName = unlocalizedName;
			this.registryName = registryName;
		}
		
		public String getUnlocalizedName(){
			return unlocalizedName;
		}

		public String getRegistryName(){
			return registryName;
		}
		
	}
	public static enum BedderBedBlocks{
		SLEEPINGBAG_COLORED(MODID + "_sleepingbag_colored", "BlockSleepingBag_Colored"),
		BED_COLORED(MODID + "_bed_colored", "BlockBed_Colored");
		
		private String unlocalizedName;
		private String registryName;
		
		BedderBedBlocks(String unlocalizedName, String registryName){
			this.unlocalizedName = unlocalizedName;
			this.registryName = registryName;
		}
		
		public String getUnlocalizedName(){
			return unlocalizedName;
		}

		public String getRegistryName(){
			return registryName;
		}
		
	}
}
