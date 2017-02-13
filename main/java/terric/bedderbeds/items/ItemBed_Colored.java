package terric.bedderbeds.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import terric.bedderbeds.Reference;
import terric.bedderbeds.blocks.BlockBed_Colored;
import terric.bedderbeds.init.ModBlocks;

public class ItemBed_Colored extends Item {

	private static String name = "bed_";
	
	public ItemBed_Colored() {
		setUnlocalizedName(Reference.BedderBedItems.BED_COLORED.getUnlocalizedName());
		setRegistryName(Reference.BedderBedItems.BED_COLORED.getRegistryName());
        setMaxDamage(0);
        setHasSubtypes(true);
		setCreativeTab(CreativeTabs.DECORATIONS);
	}

	public String getName() {
		return name;
	}
	
    /* 
	* Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    @Override
	public String getUnlocalizedName(ItemStack stack)
    {
        int i = stack.getMetadata();
        return super.getUnlocalizedName() + "." + EnumDyeColor.byMetadata(i).getUnlocalizedName();
    }
    
    public static String getNameFromMeta(int meta) {
    	return name + EnumDyeColor.byMetadata(meta).getUnlocalizedName();
    }
    
    // returns a list of items with the same ID, but different meta
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        for (int i = 0; i < 16; ++i)
        {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }
    
    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return EnumActionResult.SUCCESS;
        }
        else if (facing != EnumFacing.UP)
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();
            boolean flag = block.isReplaceable(worldIn, pos);

           if (!flag)
            {
            	pos = pos.up();
            }

            int i = MathHelper.floor_double((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            EnumFacing enumfacing = EnumFacing.getHorizontal(i);
            BlockPos blockpos = pos.offset(enumfacing);

            if (playerIn.canPlayerEdit(pos, facing, stack) && playerIn.canPlayerEdit(blockpos, facing, stack))
            {
                boolean flag1 = worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);
                boolean flag2 = flag || worldIn.isAirBlock(pos);
                boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

                if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isFullyOpaque() && worldIn.getBlockState(blockpos.down()).isFullyOpaque())
                {
                    EnumDyeColor bedColor = EnumDyeColor.byMetadata(stack.getMetadata());
                    IBlockState iblockstate1 = ModBlocks.blockbed_colored.getDefaultState().withProperty(BlockBed_Colored.COLOR, bedColor).withProperty(BlockBed_Colored.OCCUPIED, Boolean.valueOf(false)).withProperty(BlockBed_Colored.FACING, enumfacing).withProperty(BlockBed_Colored.PART, BlockBed_Colored.EnumPartType.FOOT);
                    //create new bed
                    if (worldIn.setBlockState(pos, iblockstate1, 11))
                    {
                        IBlockState iblockstate2 = iblockstate1.withProperty(BlockBed_Colored.PART, BlockBed_Colored.EnumPartType.HEAD);
                        worldIn.setBlockState(blockpos, iblockstate2, 11);
                    }

                    SoundType soundtype = iblockstate1.getBlock().getSoundType();
                    worldIn.playSound((EntityPlayer)null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    --stack.stackSize;
                    return EnumActionResult.SUCCESS;
                }
                else
                {
                    return EnumActionResult.FAIL;
                }
            }
            else
            {
                return EnumActionResult.FAIL;
            }
        }
    }
	


}
