package controller;

import java.util.Scanner;

public class Shop {
	
	Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("�׸�shop");
			System.out.println("1.���� 2.Ż�� 3.�α��� 4.�α׾ƿ�" + "\n100.������ 0.���� ");
			System.out.print("�޴����� : ");
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
				System.out.println("����Ǿ����ϴ�.");
				break;
			}
		}
	}
	
	public void run() {
		mainMenu();
		
	}

	
}
