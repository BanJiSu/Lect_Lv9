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
			System.out.println("=============== [���θ޴�] ================");
			System.out.println("[1.������] [2.����] [3.�κ��丮]");
			System.out.println("[4.����] [5.�ε�] [0.����]");
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
				System.out.println("������ ���� �մϴ�.");
				break;
			}
		}
		MainGame.sc.close();
	}

	public void run() {
		mainMenu();
		
	}

}
