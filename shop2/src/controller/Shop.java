package controller;

import java.util.Scanner;

public class Shop {
	
	Scanner sc = new Scanner(System.in);
	ItemManager im = new ItemManager();
	UserManager um = new UserManager();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("�׸�shop");
			System.out.println("1.����  2.�α���" + "\n100.������ 0.���� ");
			System.out.print("�޴����� : ");
			int sel = sc.nextInt();
			if(sel == 1) {
				um.join();
			}
			else if(sel == 2) {
				if(um.login()) {
					loginMenu();
				}
			}
			else if(sel == 100) {
				
			}
			else if(sel == 0) {
				System.out.println("����Ǿ����ϴ�.");
				break;
			}
		}
	}
	public void loginMenu() {
		while(true) {
			System.out.println("1.���� 2.��ٱ��� 3.�α׾ƿ� 4.Ż��");
			System.out.print("�޴����� : ");
			int sel = sc.nextInt();
			if(sel == 1) {
				shopMenu();
			}
			else if(sel == 2) {
				
			}
			else if(sel == 3) {
				
			}
			else if(sel == 4) {
				
			}
		}
	}
	public void shopMenu() {
		
	}
	
	public void run() {
		mainMenu();
		
	}

	
}
