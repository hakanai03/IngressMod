package net.ingressmod.ingressmod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemResonatorCore extends Item{
	public ItemResonatorCore(CreativeTabs tab,Integer level){
		setMaxStackSize(64);
		setUnlocalizedName("resonator" + level.toString());
		setCreativeTab(tab);
		setTextureName("ingressmod:resonatorL" + level.toString());
	}
}

