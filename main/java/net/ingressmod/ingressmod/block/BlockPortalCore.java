package net.ingressmod.ingressmod.block;

import net.ingressmod.ingressmod.ingressmod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPortalCore extends BlockContainer{
    public static int guiId = 0;

	public BlockPortalCore(Material material,CreativeTabs tab){
		super(material);
		setStepSound(Block.soundTypeGlass);
		setBlockName("PortalBlock");
		setBlockTextureName("ingressmod:portalblock");
		setBlockUnbreakable();
		setCreativeTab(tab);
		setLightLevel(1.0F);
	}

	//ブロックが右クリックされたときに呼び出される
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float dx, float dy, float dz)
    {
        if (!world.isRemote)
        {
            player.openGui(ingressmod.instance, guiId, world, x, y, z);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityPortalCore();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        TileEntityPortalCore tileEntity = (TileEntityPortalCore)world.getTileEntity(x, y, z);

        if (tileEntity != null)
        {
            this.dropItem(tileEntity, world, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        }
        super.breakBlock(world, x, y, z, block, metadata);
    }

    private void dropItem(IInventory iinventory, World world, int xCoord, int yCoord, int zCoord)
    {
        for (int slotIndex = 0; slotIndex < iinventory.getSizeInventory(); ++slotIndex)
        {
            ItemStack itemStack = iinventory.getStackInSlot(slotIndex);

            if (itemStack == null)
            {
                continue ;
            }

            EntityItem entityitem = new EntityItem(world, (float)xCoord, (float)yCoord, (float)zCoord, itemStack);

            world.spawnEntityInWorld(entityitem);
        }
    }
}
