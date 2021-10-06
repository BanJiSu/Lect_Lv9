package models;

public class Item {
	String name;
	int price;
	String category;

	public Item(String na, int pr, String cate) {
		name = na;
		price = pr;
		category = cate;
	}

	public void print() {
		System.out.println("[" + name + "]" + "[" + price + "]" + "[" + category + "]");
	}
}
