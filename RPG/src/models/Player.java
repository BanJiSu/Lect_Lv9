package models;

import controller.Guild;
import controller.Inventory;

public class Player {
	public static int money;
	static Guild guild = new Guild();
	static Inventory inven = new Inventory();
	
	
	public Player() {
		// TODO Auto-generated constructor stub
	}


	public void guildMenu() {
		guild.guildMenu();
	}
}
