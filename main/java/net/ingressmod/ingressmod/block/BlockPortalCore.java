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

	//�u���b�N���E�N���b�N���ꂽ�Ƃ��ɌĂяo�����
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
     * ���̃u���b�N�Ŏg��TileEntity��Ԃ����\�b�h.
     */
    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityPortalCore();
    }

    /*
     * �u���b�N���󂵂��Ƃ���, �C���x���g���ɕۑ�����Ă���A�C�e����S�ăh���b�v����悤�ɂ���.
     * �����̖����Q�͂��̃u���b�N��blockID�ƃ��^�f�[�^.
     */
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
		/*
		 * TileEntity���擾����.
		 */
        TileEntityPortalCore tileEntity = (TileEntityPortalCore)world.getTileEntity(x, y, z);

        if (tileEntity != null)
        {
			/*
			 * �h���b�v�����͕ʃ��\�b�h��.
			 */
            this.dropItem(tileEntity, world, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        }
		/*
		 * �K���X�[�p�[�N���X��breakBlock()���ĂԂ���.
		 * ����������΂킩�邪, TileEntity���폜����K�v�����邽��.
		 */
        super.breakBlock(world, x, y, z, block, metadata);
    }

    /*
     * �h���b�v�����ėp���\�b�h.
     */
    private void dropItem(IInventory iinventory, World world, int xCoord, int yCoord, int zCoord)
    {
		/*
		 * �C���x���g���𑖍�����.
		 */
        for (int slotIndex = 0; slotIndex < iinventory.getSizeInventory(); ++slotIndex)
        {
            ItemStack itemStack = iinventory.getStackInSlot(slotIndex);

			/*
			 * ItemStack����Ȃ玟��
			 */
            if (itemStack == null)
            {
                continue ;
            }

			/*
			 * ItemStack��EntityItem(�h���b�v��Ԃ̃A�C�e��)�ɂ���.
			 */
            EntityItem entityitem = new EntityItem(world, (float)xCoord, (float)yCoord, (float)zCoord, itemStack);

			/*
			 * EntityItem��World�ɃX�|�[��������.
			 */
            world.spawnEntityInWorld(entityitem);
        }
    }
}
