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
		
	}
	
	
}
