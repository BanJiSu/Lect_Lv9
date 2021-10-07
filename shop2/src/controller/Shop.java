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
				managerMenu();
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
				cartManager();
			}
			else if(sel == 3) {
				um.logout();
				break;
			}
			else if(sel == 4) {
				String delUser = um.secession().id;
				System.out.println(delUser + " Ż�� �Ϸ�");
				break;
			}
		}
	}
	public void shopMenu() {
		while(true) {
			im.printCategory();
			System.out.println("ī�װ� ��ȣ �Է� : (����.-1)");
			int cateNum = sc.nextInt();
			if(cateNum == -1) {
				break;
			}
			im.printItemList(cateNum);
			System.out.println("������ ��ȣ �Է� : ");
			int itemNum = sc.nextInt();
			
			im.addCart(um.userList.get(um.log).id, cateNum, itemNum);
			
		}
	}
	
	public void cartManager() {
		while(true) {
			System.out.println("1.�� ��ٱ���  2.����  3.����  0.�ڷΰ���");
			System.out.print("�޴����� : ");
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
			System.out.println("1.�����۰��� 2.ī�װ����� 3.�������� 4.��ٱ��ϰ��� 0.�ڷΰ��� ");
			System.out.print("�޴����� : ");
			int sel = sc.nextInt();
			if (sel == 1) {
				itemMenu();
			}
			else if (sel == 2) {
				categoryMenu();
			} 
			else if (sel == 3) {
				userMenu();
			}
			else if (sel == 4) {
				cartMenu();
			}
			else if (sel == 0) {
				break;
			}
		}
	}
	public void itemMenu() {
		while(true) {
			System.out.println("1.��ü������  2.�������߰�  3.�����ۻ���  0.�ڷΰ���");
			System.out.print("�޴����� : ");
			int sel = sc.nextInt();
			if(sel == 1) {
				im.printItemList();
			}
			else if(sel == 2) {
				im.addItem();
			}
			else if(sel == 3) {
				im.delItem();
			}
			else if(sel == 0) {
				break;
			}
		}
	}
	public void categoryMenu() {
		while(true) {
			System.out.println("1.��üī�װ�  2.ī�װ��߰�  3.ī�װ�����  0.�ڷΰ���");
			System.out.print("�޴����� : ");
			int sel = sc.nextInt();
			if(sel == 1) {
				im.printCategory();
			}
			else if(sel == 2) {
				im.addCategory();
			}
			else if(sel == 3) {
				im.delCate();
			}
			else if(sel == 0) {
				break;
			}
		}
	}
	public void userMenu() {
		while(true) {
			System.out.println("1.��ü���� 2.�������� 0.�ڷΰ���");
			System.out.print("�޴����� : ");
			int sel = sc.nextInt();
			if(sel == 1) {
				um.printUser();
			}
			else if(sel == 2) {
				um.delUser();
			}
			else if(sel == 0) {
				break;
			}
		}
	}
	public void cartMenu() {
		
	}
	
	public void run() {
		mainMenu();
		
	}

	
}
