package controller;

import java.util.Scanner;

public class Shop {
	
	Scanner sc = new Scanner(System.in);
	ItemManager im = new ItemManager();
	UserManager um = new UserManager();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("그린shop");
			System.out.println("1.가입  2.로그인" + "\n100.관리자 0.종료 ");
			System.out.print("메뉴선택 : ");
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
				System.out.println("종료되었습니다.");
				break;
			}
		}
	}
	public void loginMenu() {
		while(true) {
			System.out.println("1.쇼핑 2.장바구니 3.로그아웃 4.탈퇴");
			System.out.print("메뉴선택 : ");
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
