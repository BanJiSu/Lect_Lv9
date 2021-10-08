package controller;

import java.util.ArrayList;
import java.util.Scanner;

import models.Cart;
import models.Item;
import models.User;

public class ItemManager {
	
	Scanner sc = new Scanner(System.in);
	ArrayList<Item> itemList = new ArrayList<Item>();
	ArrayList<String> category = new ArrayList<String>();
	ArrayList<Cart> jangList = new ArrayList<Cart>();
	
	ItemManager() {
		init();
	}
	void init() {
		category.add("����");
		category.add("����");
		category.add("����");
		category.add("�����");
		Item temp = new Item("�����", 1000, category.get(0));
		itemList.add(temp);
		temp = new Item("����", 2000, category.get(1));
		itemList.add(temp);
		temp = new Item("ĭ��", 3600, category.get(0));
		itemList.add(temp);
		temp = new Item("�Ұ��", 6500, category.get(2));
		itemList.add(temp);
		temp = new Item("�ݶ�", 500, category.get(3));
		itemList.add(temp);
		temp = new Item("����", 1800, category.get(1));
		itemList.add(temp);
	}
	public int checkItem(String name) {
		int check = -1;
		for(int i=0; i<itemList.size(); i++) {
			if(itemList.get(i).name.equals(name)) {
				check = i;
				break;
			}
		}
		return check;
	}

	public void printCategory() {
		for(int i=0; i<category.size(); i++) {
			System.out.println("[" + i + "] " + category.get(i));
		}
	}
	public void printItemList() {
		for (int i = 0; i < itemList.size(); i++) {
			System.out.print("[" + i + "]");
			itemList.get(i).print();
		}
	}
	public void printItemList(int cateNum) {
		int n = 0;
		for (int i = 0; i < itemList.size(); i++) {
			if (category.get(cateNum).equals(itemList.get(i).category)) {
				System.out.print("[" + n + "]");
				itemList.get(i).print();
				n += 1;
			}
		}
	}
	
	public void addCart(String usID, int cateNum, int itemNum) {
		int n = 0;
		Cart temp = new Cart();
		temp.userId = usID;
		for (int i = 0; i < itemList.size(); i++) {
			if (category.get(cateNum).equals(itemList.get(i).category)) {
				if (itemNum == n) {
					temp.itemName = itemList.get(i).name;
				}
				n += 1;
			}
		}
		jangList.add(temp);
	}
	public void addItem() {
		System.out.print("�߰��� ������ �̸� �Է� : ");
		String name = sc.next();
		int check = checkItem(name);
		if(check != -1) {
			System.out.println("�ߺ� �������Դϴ�.");
			return;
		}
		System.out.print("������ ���� �Է� : ");
		int money = sc.nextInt();
		printCategory();
		System.out.print("ī�װ� ���� : ");
		int sel = sc.nextInt();
		if(sel < 0 || sel >= category.size()) {
			System.out.println("�߸��Է��ϼ̽��ϴ�.");
			return;
		}
		Item temp = new Item(name, money, category.get(sel));
		itemList.add(temp);
	}
	
	public void addCategory() {
		System.out.println("�߰��� ī�װ� �̸��� �Է��ϼ���. ");
		String name = sc.next();
		category.add(name);
	}
	
	public void printJang(User u) {
		for (int i = 0; i < jangList.size(); i++) {
			if (u.id.equals(jangList.get(i).userId)) {
				jangList.get(i).print();
			}
		}
	}
	public void delCart(User u) {
		printJang(u);
		System.out.println("������ ������ ���� : ");
		int sel = sc.nextInt();
		jangList.remove(sel);
		
	}
	public void delCate() {
		printCategory();
		if(category.size() == 0) {
			System.out.println("������ ī�װ��� �����ϴ�.");
			return;
		}
		System.out.print("������ ī�װ� ���� : ");
		int sel = sc.nextInt();
		if(sel < 0 || sel >= category.size()) {
			System.out.println("�߸��Է��ϼ̽��ϴ�.");
			return;
		}
		category.remove(sel);
	}
	public void delItem() {
		printItemList();
		if(itemList.size() == 0) {
			System.out.println("������ �������� �����ϴ�.");
			return;
		}
		System.out.print("������ ������ ���� : ");
		int sel = sc.nextInt();
		if(sel < 0 || sel >= itemList.size()) {
			System.out.println("�߸��Է��ϼ̽��ϴ�.");
			return;
		}
		itemList.remove(sel);
		System.out.println("�����Ϸ�");
	}
	public void buyCart(User u) {
		printJang(u);
		System.out.println("������ ������ ���� : ");
		int sel = sc.nextInt();
		if(sel < 0 || sel >= jangList.size()) {
			System.out.println("�߸��Է��ϼ̽��ϴ�.");
			return;
		}
		int money = 0;
		for(int i=0; i<itemList.size(); i++) {
			if(jangList.get(sel).itemName.equals(itemList.get(i).name)) {
				money = itemList.get(i).price;
			}
		}
		if(money > u.money) {
			System.out.println("�ݾ׺���");
			return;
		}
		u.money -= money;
		jangList.remove(sel);
		System.out.println("���� �Ϸ�");
		System.out.println("�ܾ� : " + u.money);
	}
	
	
}
