package models;

public class User {
	public String id;
	public int money;

	public User(String id, int mo) {
		this.id = id;
		money = mo;
	}

	public void print() {
		System.out.println("[" + id + "] " + "±Ý¾× : " + money + "¿ø");
	}
}
