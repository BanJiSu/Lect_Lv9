package controller;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import models.Player;

public class MainGame {

	static Scanner sc = new Scanner(System.in);
	static Random rn = new Random();

	public void mainMenu() {
		Player player = new Player();
		ShopManager sm = new ShopManager();
		FileData fileData = new FileData();
		while (true) {
			System.out.println("=============== [메인메뉴] ================");
			System.out.println("[1.길드관리] [2.상점] [3.인벤토리]");
			System.out.println("[4.저장] [5.로드] [0.종료]");
			int sel = sc.nextInt();
			if (sel == 1) {
				player.guildMenu();
			} 
			else if (sel == 2) {

			}
			else if (sel == 3) {

			} 
			else if (sel == 4) {

			}
			else if (sel == 5) {

			}
			else if (sel == 0) {
				System.out.println("게임을 종료 합니다.");
				break;
			}
		}
		MainGame.sc.close();
	}

	public void run() {
		mainMenu();
		
	}

}
