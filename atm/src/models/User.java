package models;

import java.util.ArrayList;

public class User {
	
	private int code; // 1000~9999 ม฿บน x ทฃดý
	private String id;
	private String pw;
	private String name;
	
	private ArrayList<Account> accs = new ArrayList<Account>();
	
//	private int money; // -> ArrayList<Account> accs
	
	public User(int code, String id, String pw, String name) {
		this.code = code;
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	// getter
	public int getCode() {
		return this.code;
	}
	public String getId() {
		return this.id;
	}
	public String getPw() {
		return this.pw;
	}
	public String getName() {
		return this.name;
	}
	public Account getAcc(int index) {
		return this.accs.get(index);
	}
	
	public int getUserAccsSize() {
		return this.accs.size();
	}
	public void addAcc(Account e) {
		this.accs.add(e);
	}
	
	public void removeAcc(Account e) {
		this.accs.remove(e);
		
	}
	
	@Override
	public String toString() {
		String str = "";
		str += this.code + " ";
		str += this.id + "/";
		str += this.pw + " : ";
		str += this.name;
		str += "(" + this.accs.size() + ")";
		return str;
	}
	
}
