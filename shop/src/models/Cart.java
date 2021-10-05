package models;

public class Cart {
	public String userId; // 구입한 유저 id
	public String itemName; // 구입한 아이템

	public void print() {
		System.out.println("[" + userId + "]" + "아이템 : " + itemName);
	}
}
