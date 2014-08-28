package net.ingressmod.ingressmod;


import net.ingressmod.ingressmod.block.BlockPortalCore;
import net.ingressmod.ingressmod.block.TileEntityPortalCore;
import net.ingressmod.ingressmod.gui.GuiPortalCoreHandler;
import net.ingressmod.ingressmod.item.ItemHeatSinkCore;
import net.ingressmod.ingressmod.item.ItemPortalShieldCore;
import net.ingressmod.ingressmod.item.ItemResonatorCore;
import net.ingressmod.ingressmod.item.ItemScannerCore;
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
    //proxyの設定
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
        Item resonatorL1 = new ItemResonatorCore(tab,1);
        Item resonatorL2 = new ItemResonatorCore(tab,2);
        Item resonatorL3 = new ItemResonatorCore(tab,3);
        Item resonatorL4 = new ItemResonatorCore(tab,4);
        Item resonatorL5 = new ItemResonatorCore(tab,5);
        Item resonatorL6 = new ItemResonatorCore(tab,6);
        Item resonatorL7 = new ItemResonatorCore(tab,7);
        Item resonatorL8 = new ItemResonatorCore(tab,8);
        Item heatSinkC = new ItemHeatSinkCore(tab,"C");
        Item heatSinkR = new ItemHeatSinkCore(tab,"R");
        Item heatSinkVR = new ItemHeatSinkCore(tab,"VR");
        Item portalShieldC = new ItemPortalShieldCore(tab,"C");
        Item portalShieldR = new ItemPortalShieldCore(tab,"R");
        Item portalShieldVR = new ItemPortalShieldCore(tab,"VR");
        GameRegistry.registerItem(ingressScanner, ingressScanner.getUnlocalizedName());
        GameRegistry.registerItem(resonatorL1, resonatorL1.getUnlocalizedName());
        GameRegistry.registerItem(resonatorL2, resonatorL2.getUnlocalizedName());
        GameRegistry.registerItem(resonatorL3, resonatorL3.getUnlocalizedName());
        GameRegistry.registerItem(resonatorL4, resonatorL4.getUnlocalizedName());
        GameRegistry.registerItem(resonatorL5, resonatorL5.getUnlocalizedName());
        GameRegistry.registerItem(resonatorL6, resonatorL6.getUnlocalizedName());
        GameRegistry.registerItem(resonatorL7, resonatorL7.getUnlocalizedName());
        GameRegistry.registerItem(resonatorL8, resonatorL8.getUnlocalizedName());
        GameRegistry.registerItem(heatSinkC, heatSinkC.getUnlocalizedName());
        GameRegistry.registerItem(heatSinkR, heatSinkR.getUnlocalizedName());
        GameRegistry.registerItem(heatSinkVR, heatSinkVR.getUnlocalizedName());
        GameRegistry.registerItem(portalShieldC, portalShieldC.getUnlocalizedName());
        GameRegistry.registerItem(portalShieldR, portalShieldR.getUnlocalizedName());
        GameRegistry.registerItem(portalShieldVR, portalShieldVR.getUnlocalizedName());

        System.out.println("done.");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        System.out.println(MODHEAD + "Loading ingress Mod ...");

        // TileEntityを登録
        GameRegistry.registerTileEntity(TileEntityPortalCore.class, "TileEntityPortalCore");

        // GuiHandlerを登録
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiPortalCoreHandler());
    }


}
