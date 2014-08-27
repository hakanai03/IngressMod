package net.ingressmod.ingressmod.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntityPortalCore extends TileEntity implements IInventory{

    private ItemStack[] items = new ItemStack[4];

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        NBTTagList itemsTagList = nbtTagCompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);

        this.items = new ItemStack[this.getSizeInventory()];

        for (int tagCounter = 0; tagCounter < itemsTagList.tagCount(); ++tagCounter)
        {
            NBTTagCompound itemTagCompound = (NBTTagCompound)itemsTagList.getCompoundTagAt(tagCounter);

            byte slotIndex = itemTagCompound.getByte("Slot");

            if (slotIndex >= 0 && slotIndex < this.items.length)
            {
                this.items[slotIndex] = ItemStack.loadItemStackFromNBT(itemTagCompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        NBTTagList itemsTagList = new NBTTagList();

        for (int slotIndex = 0; slotIndex < this.items.length; ++slotIndex)
        {
            if (this.items[slotIndex] != null)
            {
                NBTTagCompound itemTagCompound = new NBTTagCompound();

                itemTagCompound.setByte("Slot", (byte)slotIndex);
                this.items[slotIndex].writeToNBT(itemTagCompound);

                itemsTagList.appendTag(itemTagCompound);
            }
        }

        nbtTagCompound.setTag("Items", itemsTagList);
    }

    @Override
    public int getSizeInventory()
    {
        return 4;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        if (slotIndex >= 0 && slotIndex < this.items.length)
        {
            return this.items[slotIndex];
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int splitStackSize)
    {
        if (this.items[slotIndex] != null)
        {
            if (this.items[slotIndex].stackSize <= splitStackSize)
            {
                ItemStack tmpItemStack = items[slotIndex];
                this.items[slotIndex] = null;

                this.markDirty();

                return tmpItemStack;
            }

            ItemStack splittedItemStack = this.items[slotIndex].splitStack(splitStackSize);

            if (this.items[slotIndex].stackSize == 0)
            {
                this.items[slotIndex] = null;
            }

            this.markDirty();

            return splittedItemStack;
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        return this.items[slotIndex];
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        this.items[slotIndex] = itemStack;

        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }
        this.markDirty();
    }

    @Override
    public String getInventoryName()
    {
        return "container.PortalCore";
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this)
        {
            return false;
        }

        return entityplayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
    }

    @Override
    public void openInventory()
    {
    }

    @Override
    public void closeInventory()
    {
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemstack)
    {
        return true;
    }
}
