package terric.bedderbeds.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityBed_Colored extends TileEntity{

	private EnumDyeColor color = EnumDyeColor.BLUE; //default
	
	public TileEntityBed_Colored() {
	}

	public void setColor (EnumDyeColor bedcolor) {
		this.color = bedcolor;
		markDirty();
		IBlockState state = worldObj.getBlockState(pos);
		worldObj.notifyBlockUpdate(pos, state, state, 3);
	}
	
	public EnumDyeColor getColor () {
		return this.color;
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return false;
	}

	public void writeUpdateTag(NBTTagCompound compound) {
		compound.setInteger("Color", this.color.getMetadata()); 
	}
	
	public void readUpdateTag(NBTTagCompound compound) {
		this.color = EnumDyeColor.byMetadata(compound.getInteger("Color"));
	}

//acces to permanently stored info in game file 
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("Color", this.color.getMetadata());
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.color = EnumDyeColor.byMetadata(compound.getInteger("Color"));
	}

//communication between client and server 
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound compound = pkt.getNbtCompound();
		readUpdateTag(compound);
	}
	
	@Override
	@Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound compound = new NBTTagCompound();
		this.writeUpdateTag(compound);
        return new SPacketUpdateTileEntity(this.pos, getBlockMetadata(), compound);
    }
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound compound = super.getUpdateTag();
		writeUpdateTag(compound);
		return compound;
	}
}
