package net.ingressmod.ingressmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockPortalCore extends Block{
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
	public boolean onBlockActivated(
			World world,
			int x, int y, int z,
			EntityPlayer entityPlayer,
			int i1, float f1, float f2, float f3){
		System.out.println("[Portal Clecked] World:"
			+ world.toString()
			+ "/Player:" + entityPlayer.toString()
			+ "/x,z,y" + x + "," + z + "," + y);
		return false;
	}
}
