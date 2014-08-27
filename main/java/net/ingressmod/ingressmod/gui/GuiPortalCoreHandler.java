package net.ingressmod.ingressmod.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.ingressmod.ingressmod.block.BlockPortalCore;
import net.ingressmod.ingressmod.block.TileEntityPortalCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiPortalCoreHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if(ID == BlockPortalCore.guiId && tileEntity != null)
        {
            return new ContainerPortalCore(player.inventory, (TileEntityPortalCore)tileEntity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if(ID == BlockPortalCore.guiId && tileEntity != null){
            return new GuiContainerPortalCore(player.inventory, (TileEntityPortalCore)tileEntity);
        }
        return null;
    }
}
