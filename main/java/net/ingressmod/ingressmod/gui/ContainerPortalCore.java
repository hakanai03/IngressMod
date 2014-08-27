package net.ingressmod.ingressmod.gui;

import net.ingressmod.ingressmod.block.TileEntityPortalCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPortalCore extends Container {
    private IInventory inventoryPlayer;
    private TileEntityPortalCore tileEntity;

    public ContainerPortalCore(IInventory inventoryPlayer, TileEntityPortalCore tileEntity)
    {
        this.inventoryPlayer = inventoryPlayer;
        this.tileEntity      = tileEntity;

		/*
		 * 1*4のインベントリ
		 */
        for (int row = 0; row < 4; ++row)
        {
            this.addSlotToContainer(new Slot(tileEntity, row, 98 + row * 18, 64));
        }

		/*
		 *  3*9のプレイヤーインベントリ
		 */
        for (int col = 0; col < 3; ++col)
        {
            for (int row = 0; row < 9; ++row)
            {
                this.addSlotToContainer(new Slot(this.inventoryPlayer, row + col * 9 + 9, 8 + row * 18, 84 + col * 18));
            }
        }

		/*
		 *  1*9のプレイヤーインベントリ
		 */
        for (int row = 0; row < 9; ++row)
        {
            this.addSlotToContainer(new Slot(this.inventoryPlayer, row, 8 + row * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return tileEntity.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);
        ItemStack srcItemStack = null;

        if (slot != null && slot.getHasStack())
        {
            ItemStack destItemStack = slot.getStack();
            srcItemStack = destItemStack.copy();

            if (slotIndex < 4 && !this.mergeItemStack(destItemStack, 4, 40, false))
            {
                return null;
            }

            if (slotIndex >= 4 && !this.mergeItemStack(destItemStack, 0, 4, false))
            {
                return null;
            }

            if (destItemStack.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return srcItemStack;
    }
}
