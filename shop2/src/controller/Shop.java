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
				managerMenu();
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
				cartManager();
			}
			else if(sel == 3) {
				um.logout();
				break;
			}
			else if(sel == 4) {
				String delUser = um.secession().id;
				System.out.println(delUser + " 탈퇴 완료");
				break;
			}
		}
	}
	public void shopMenu() {
		while(true) {
			im.printCategory();
			System.out.println("카테고리 번호 입력 : (종료.-1)");
			int cateNum = sc.nextInt();
			if(cateNum == -1) {
				break;
			}
			im.printItemList(cateNum);
			System.out.println("아이템 번호 입력 : ");
			int itemNum = sc.nextInt();
			
			im.addCart(um.userList.get(um.log).id, cateNum, itemNum);
			
		}
	}
	
	public void cartManager() {
		while(true) {
			System.out.println("1.내 장바구니  2.삭제  3.구입  0.뒤로가기");
			System.out.print("메뉴선택 : ");
			int sel = sc.nextInt();
			if(sel == 1) {
				im.printJang(um.userList.get(um.log));
			}
			else if(sel == 2) {
				im.delCart(um.userList.get(um.log));
			}
			else if(sel == 3) {
				im.buyCart(um.userList.get(um.log));
			}
			else if(sel == 0) {
				break;
			}
		}
	}
	
	public void managerMenu() {
		while (true) {
			System.out.println("1.아이템관리 2.카테고리관리 3.장바구니관리 4.유저관리 0.뒤로가기 ");
			int sel = sc.nextInt();
			if (sel == 1) {
				itemMenu();
			} else if (sel == 2) {
				categoryMenu();
			} else if (sel == 3) {

			} else if (sel == 4) {
				userMenu();
			} else if (sel == 0) {
				break;
			}
		}
	}
	public void itemMenu() {
		
	}
	public void categoryMenu() {
		
	}
	public void userMenu() {
		
	}
	
	public void run() {
		mainMenu();
		
	}

	
}
