package net.ingressmod.ingressmod;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(
		modid = ingressmod.MODID,
		name = "ingressmod",
		version = ingressmod.VERSION
)



public class ingressmod {
	public static final String MODID = "ingressmod";
	public static final String VERSION = "0.0.1";

	public static final String MODHEAD = "[IngressMod] ";

	public static Block portalBlock;

	@Mod.EventHandler
	public void PreInit(FMLPreInitializationEvent event){
		System.out.print(MODHEAD + "Pre Initialization ...");


		//コンフィグをロードする処理
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try{
			cfg.load();
			//Property portalBlock = cfg.get(cfg.CATEGORY_GENERAL,"PortalBlockId",1500, "Poeral Block ID");

		}catch(Exception e){
			System.out.println(MODHEAD + e);
		}finally{
			cfg.save();
		}


		//クリエイティブタブを追加する
		CreativeTabs tab = new CreativeTabs(CreativeTabs.getNextID() ,"ingressmod"){
			@Override
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem(){
				return Items.nether_star;
			}
		};
		//LanguageRegistry.instance().addStringLocalization("itemGroup.ingressmod", "en_US", "IngressMod");//非推奨らしいのでassets/ingressmod/langを使う


		//ブロックを追加していく
		portalBlock = new BlockPortalCore(Material.glass, tab);
		GameRegistry.registerBlock(portalBlock, portalBlock.getUnlocalizedName());


		//アイテムを追加していく
		Item ingressScanner = new ItemScannerCore(tab);
		GameRegistry.registerItem(ingressScanner, ingressScanner.getUnlocalizedName());

		System.out.println("done.");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
			System.out.println(MODHEAD + "Loading ingress Mod ...");
	}


}
