package models;

public class Item {
	public String name;
	public int price;
	public String category;

	public Item(String na, int pr, String cate) {
		name = na;
		price = pr;
		category = cate;
	}

	public void print() {
		System.out.println("[" + name + "]" + "[" + price + "]" + "[" + category + "]");
	}
}
