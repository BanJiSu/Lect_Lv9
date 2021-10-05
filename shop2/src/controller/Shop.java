package controller;

import java.util.Scanner;

public class Shop {
	
	Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("그린shop");
			System.out.println("1.가입 2.탈퇴 3.로그인 4.로그아웃" + "\n100.관리자 0.종료 ");
			System.out.print("메뉴선택 : ");
			int sel = sc.nextInt();
			if(sel == 1) {
				
			}
			else if(sel == 2) {
				
			}
			else if(sel == 3) {
				
			}
			else if(sel == 4) {
				
			}
			else if(sel == 100) {
				
			}
			else if(sel == 0) {
				System.out.println("종료되었습니다.");
				break;
			}
		}
	}
	
	public void run() {
		mainMenu();
		
	}

	
}
