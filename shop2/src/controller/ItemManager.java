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
		category.add("과자");
		category.add("생선");
		category.add("육류");
		category.add("음료수");
		Item temp = new Item("새우깡", 1000, category.get(0));
		itemList.add(temp);
		temp = new Item("고등어", 2000, category.get(1));
		itemList.add(temp);
		temp = new Item("칸쵸", 3600, category.get(0));
		itemList.add(temp);
		temp = new Item("소고기", 6500, category.get(2));
		itemList.add(temp);
		temp = new Item("콜라", 500, category.get(3));
		itemList.add(temp);
		temp = new Item("새우", 1800, category.get(1));
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
		System.out.print("추가할 아이템 이름 입력 : ");
		String name = sc.next();
		int check = checkItem(name);
		if(check != -1) {
			System.out.println("중복 아이템입니다.");
			return;
		}
		System.out.print("아이템 가격 입력 : ");
		int money = sc.nextInt();
		printCategory();
		System.out.print("카테고리 선택 : ");
		int sel = sc.nextInt();
		if(sel < 0 || sel >= category.size()) {
			System.out.println("잘못입력하셨습니다.");
			return;
		}
		Item temp = new Item(name, money, category.get(sel));
		itemList.add(temp);
	}
	
	public void addCategory() {
		System.out.println("추가할 카테고리 이름을 입력하세요. ");
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
		System.out.println("삭제할 아이템 선택 : ");
		int sel = sc.nextInt();
		jangList.remove(sel);
		
	}
	public void delCate() {
		printCategory();
		if(category.size() == 0) {
			System.out.println("삭제할 카테고리가 없습니다.");
			return;
		}
		System.out.print("삭제할 카테고리 선택 : ");
		int sel = sc.nextInt();
		if(sel < 0 || sel >= category.size()) {
			System.out.println("잘못입력하셨습니다.");
			return;
		}
		category.remove(sel);
	}
	public void delItem() {
		printItemList();
		if(itemList.size() == 0) {
			System.out.println("삭제할 아이템이 없습니다.");
			return;
		}
		System.out.print("삭제할 아이템 선택 : ");
		int sel = sc.nextInt();
		if(sel < 0 || sel >= itemList.size()) {
			System.out.println("잘못입력하셨습니다.");
			return;
		}
		itemList.remove(sel);
		System.out.println("삭제완료");
	}
	public void buyCart(User u) {
		printJang(u);
		System.out.println("구입할 아이템 선택 : ");
		int sel = sc.nextInt();
		if(sel < 0 || sel >= jangList.size()) {
			System.out.println("잘못입력하셨습니다.");
			return;
		}
		int money = 0;
		for(int i=0; i<itemList.size(); i++) {
			if(jangList.get(sel).itemName.equals(itemList.get(i).name)) {
				money = itemList.get(i).price;
			}
		}
		if(money > u.money) {
			System.out.println("금액부족");
			return;
		}
		u.money -= money;
		jangList.remove(sel);
		System.out.println("구매 완료");
		System.out.println("잔액 : " + u.money);
	}
	
	
}
