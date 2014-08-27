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

public class BlockPortalCore extends BlockContainer {
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

    /*
     * このブロックで使うTileEntityを返すメソッド.
     */
    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityPortalCore();
    }

    /*
     * ブロックを壊したときに, インベントリに保存されているアイテムを全てドロップするようにする.
     * 引数の末尾２つはこのブロックのblockIDとメタデータ.
     */
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
		/*
		 * TileEntityを取得する.
		 */
        TileEntityPortalCore tileEntity = (TileEntityPortalCore)world.getTileEntity(x, y, z);

        if (tileEntity != null)
        {
			/*
			 * ドロップ処理は別メソッドに.
			 */
            this.dropItem(tileEntity, world, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        }
		/*
		 * 必ずスーパークラスのbreakBlock()を呼ぶこと.
		 * 内部を見ればわかるが, TileEntityを削除する必要があるため.
		 */
        super.breakBlock(world, x, y, z, block, metadata);
    }

    /*
     * ドロップ処理汎用メソッド.
     */
    private void dropItem(IInventory iinventory, World world, int xCoord, int yCoord, int zCoord)
    {
		/*
		 * インベントリを走査する.
		 */
        for (int slotIndex = 0; slotIndex < iinventory.getSizeInventory(); ++slotIndex)
        {
            ItemStack itemStack = iinventory.getStackInSlot(slotIndex);

			/*
			 * ItemStackが空なら次へ
			 */
            if (itemStack == null)
            {
                continue ;
            }

			/*
			 * ItemStackをEntityItem(ドロップ状態のアイテム)にする.
			 */
            EntityItem entityitem = new EntityItem(world, (float)xCoord, (float)yCoord, (float)zCoord, itemStack);

			/*
			 * EntityItemをWorldにスポーンさせる.
			 */
            world.spawnEntityInWorld(entityitem);
        }
    }
}
