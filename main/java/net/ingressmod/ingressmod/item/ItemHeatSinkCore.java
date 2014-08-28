package net.ingressmod.ingressmod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemHeatSinkCore extends Item{
	public ItemHeatSinkCore(CreativeTabs tab, String rarity){
		setMaxStackSize(64);
		setUnlocalizedName("heatSink" + rarity);
		setCreativeTab(tab);
		setTextureName("ingressmod:heatSink" + rarity);
	}
}
