package net.ingressmod.ingressmod;

import net.ingressmod.ingressmod.block.BlockPortalParticles;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy{

	@Override
	public void init(){
		MinecraftForge.EVENT_BUS.register(new BlockPortalParticles());
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
		switch(ID){
		case 0:
			break;
		default:
			break;
		}
		return null;
	}
}