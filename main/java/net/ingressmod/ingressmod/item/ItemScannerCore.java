package net.ingressmod.ingressmod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ItemScannerCore  extends Item{
	public ItemScannerCore(CreativeTabs tab){
		setMaxStackSize(1);
		setUnlocalizedName("scanner");
		setCreativeTab(tab);
		setTextureName("ingressmod:scanner");
	}
}
