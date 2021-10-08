package controller;

import java.util.ArrayList;

import models.Player;
import models.Unit;

public class Guild {
	final int PARTY_SIZE = 4;
	ArrayList<Unit> guildList = new ArrayList<>();
	
	public void guildMenu() {
		while (true) {
			System.out.println("=============== [길드관리] ================");
			System.out.println("1.길드목록 2.길드원추가 3.길드원삭제 4.파티원교체 5.정렬 0.뒤로가기");
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
		System.out.println("[골드 : " + Player.money + "]");
		System.out.println("============= [길드원] =================");
	}
}
