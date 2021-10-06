package models;

public class Account {
	
	public static final int max = 3;
	
	private int userCode;
	private int accNum;
	private int money;
	
	public Account(int userCode, int accNum) {
		this.userCode = userCode;
		this.accNum = accNum;
		this.money = 1000;	// welcome
	}
	public Account(int userCode, int accNum, int money) {
		this.userCode = userCode;
		this.accNum = accNum;
		this.money = money;
	}
	
	// getter
	public int getUserCode() {
		return this.userCode;
	}
	public int getAccNum() {
		return this.accNum;
	}
	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.accNum + " : " + this.money;
	}
}
