package controller;

import java.util.ArrayList;

import models.Player;
import models.Unit;

public class Guild {
	final int PARTY_SIZE = 4;
	ArrayList<Unit> guildList = new ArrayList<>();
	
	public void guildMenu() {
		while (true) {
			System.out.println("=============== [������] ================");
			System.out.println("1.����� 2.�����߰� 3.�������� 4.��Ƽ����ü 5.���� 0.�ڷΰ���");
			int sel = MainGame.sc.nextInt();
			if (sel == 1) {
				printAllUnitStaus();
			}
			else if (sel == 2) {
			
			} 
			else if (sel == 3) {
				
			} 
			else if (sel == 4) {
				
			}
			else if(sel == 0){
				break;
			}
		}
	}
	
	public void printAllUnitStaus() {
		System.out.println("======================================");
		System.out.println("[��� : " + Player.money + "]");
		System.out.println("============= [����] =================");
	}
}
