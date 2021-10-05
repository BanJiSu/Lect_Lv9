package models;

public class Item {
	public String name;
	int price;
	public String category; // 카테고리 // 육류 , 과자 , 어류 , 과일 등등

	public Item(String na, int pr, String cate) {
		name = na;
		price = pr;
		category = cate;
	}

	public void print() {
		System.out.println("[" + name + "]" + "[" + price + "]" + "[" + category + "]");
	}
}
