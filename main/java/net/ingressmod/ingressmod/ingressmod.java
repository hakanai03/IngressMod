package net.ingressmod.ingressmod;


import net.ingressmod.ingressmod.block.BlockPortalCore;
import net.ingressmod.ingressmod.block.TileEntityPortalCore;
import net.ingressmod.ingressmod.gui.GuiPortalCoreHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.common.network.NetworkRegistry;


@Mod(
		modid = ingressmod.MODID,
		name = "ingressmod",
		version = ingressmod.VERSION
)



public class ingressmod {
	//proxy�̐ݒ�
	@SidedProxy(clientSide = "net.ingressmod.ingressmod.ClientProxy", serverSide = "net.ingressmod.ingressmod.CommonProxy")
	public static ClientProxy proxy;

	public static final String MODID = "ingressmod";
	public static final String VERSION = "0.0.1";

	public static final String MODHEAD = "[IngressMod] ";

	public static Block portalBlock;

	@Instance("ingressmod")
	public static ingressmod instance;


	@Mod.EventHandler
	public void PreInit(FMLPreInitializationEvent event){
		System.out.print(MODHEAD + "Pre Initialization ...");

		instance = this;
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);


		//�R���t�B�O�����[�h���鏈��
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try{
			cfg.load();
			//Property portalBlock = cfg.get(cfg.CATEGORY_GENERAL,"PortalBlockId",1500, "Poeral Block ID");
		}catch(Exception e){
			System.out.println(MODHEAD + e);
		}finally{
			cfg.save();
		}


		//�N���G�C�e�B�u�^�u��ǉ�����
		CreativeTabs tab = new CreativeTabs(CreativeTabs.getNextID() ,"ingressmod"){
			@Override
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem(){
				return Items.nether_star;
			}
		};
		//LanguageRegistry.instance().addStringLocalization("itemGroup.ingressmod", "en_US", "IngressMod");//�񐄏��炵���̂�assets/ingressmod/lang���g��


		//�u���b�N��ǉ����Ă���
		portalBlock = new BlockPortalCore(Material.glass, tab);
		GameRegistry.registerBlock(portalBlock, portalBlock.getUnlocalizedName());


		//�A�C�e����ǉ����Ă���
		Item ingressScanner = new ItemScannerCore(tab);
		GameRegistry.registerItem(ingressScanner, ingressScanner.getUnlocalizedName());

		System.out.println("done.");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
        System.out.println(MODHEAD + "Loading ingress Mod ...");

        // TileEntity��o�^
        GameRegistry.registerTileEntity(TileEntityPortalCore.class, "TileEntityPortalCore");

        // GuiHandler��o�^
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiPortalCoreHandler());
	}


}
