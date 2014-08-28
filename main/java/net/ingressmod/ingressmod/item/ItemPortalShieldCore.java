package net.ingressmod.ingressmod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemPortalShieldCore extends Item{
	public ItemPortalShieldCore(CreativeTabs tab, String rarity){
		setMaxStackSize(64);
		setUnlocalizedName("portalShield" + rarity);
		setCreativeTab(tab);
		setTextureName("ingressmod:portalShield" + rarity);
	}
}
