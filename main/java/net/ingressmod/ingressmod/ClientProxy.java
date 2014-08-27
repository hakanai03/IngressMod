package net.ingressmod.ingressmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy{
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