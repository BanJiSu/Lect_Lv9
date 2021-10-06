package controller;

import java.util.ArrayList;
import java.util.Random;

import models.Account;

public class AccountManager {

	private ArrayList<Account> accs = new ArrayList<>();
	
	public static AccountManager instance = new AccountManager();
	private UserManager um = UserManager.instance;
	
	public void inputMoney(int log) {
		
		int accIdx = selectUserAccs(log, "입금할 ");
		
		System.out.print("입금금액 : ");
		String input = BankManager.sc.next();
		
		try {
			int money = Integer.parseInt(input);
			
			int balance = this.accs.get(accIdx).getMoney();
			balance += money;
			this.accs.get(accIdx).setMoney(balance);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void outMoney(int log) {
		int accIdx = selectUserAccs(log, "출금할 ");
		
		System.out.print("출금금액 : ");
		String input = BankManager.sc.next();
		
		try {
			int money = Integer.parseInt(input);
			
			int balance = this.accs.get(accIdx).getMoney();
			if(money <= balance) {
				balance -= money;
				this.accs.get(accIdx).setMoney(balance);
			}else {
				System.out.println("잔액이 부족합니다.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void dropAccount(int log) {
		if(um.getUserAccSize(log) > 1) {
			int delAccIdx = selectUserAccs(log, "삭제할 ");
			int defaultAccIdx = selectUserAccs(log, "기본 ");
			
			if(delAccIdx != defaultAccIdx) {
				int money = this.accs.get(delAccIdx).getMoney();
				int balance = this.accs.get(defaultAccIdx).getMoney();
				balance += money;
				this.accs.get(defaultAccIdx).setMoney(balance);
				
				Account delAcc = this.accs.get(delAccIdx);
				this.accs.remove(delAcc);
				um.removeAcc(log, delAcc);
			}
		}
	}
	
	public int selectUserAccs(int log, String sub) {
		printUserAccs(log);
		
		int accIdx = -1;
		
		System.out.printf("%s계좌 선택 (1~%d):", sub, um.getUserAccSize(log));
		String input = BankManager.sc.next();
		
		try {
			int idx = Integer.parseInt(input);
			
			int cnt = 0;
			for(int i=0; i<this.accs.size(); i++) {
				if(this.accs.get(i).getUserCode() == getUserCode(log)) {
					cnt++;
					if(idx == cnt) {
						accIdx = i;
					}
				}
			}
			
			return accIdx;
		} catch (Exception e) {
			return accIdx;
		}
	}
	
	public void printUserAccs(int log) {
		// 로그인한 회원의 계좌만 선별해서 계좌정보를 보여줌
		int index = -1;
		int n = 0;
		for(int i=0; i<this.accs.size(); i++) {
			if(this.accs.get(i).getUserCode() == getUserCode(log)) {
				System.out.println(n + 1 + ")" + this.accs.get(i));
				index = i;
				n++;
			}
		}
	}
	
	public int getUserCode(int log) {
		int userCode = um.getUser(log).getCode();
		return userCode;
	}
	
	public void createAcc(int log) {
		if(um.getUserAccSize(log) < Account.max) {
			Account newAcc = new Account(getUserCode(log), ranAccNum());
			this.accs.add(newAcc);
			um.addAcc(log, newAcc);
		}
		else {
			System.out.println("최대 개설개수 초과");
		}
	}
	public int ranAccNum() {
		Random rn = new Random();
		
		boolean check = false;
		while(true) {
			int rNum = rn.nextInt(89999) + 10000;
			for(Account acc : accs) {
				if(rNum == acc.getAccNum())
					check = true;
			}
			
			if(!check)
				return rNum;
		}
	}
	
	public int getAccsSize() {
		return this.accs.size();
	}
	
	public Account getAcc(int index) {
		return this.accs.get(index);
	}
	
	public void addAcc(Account e) {
		this.accs.add(e);
	}
	
	public void moveMoney(int log) {
		System.out.print("이체할 계좌번호 : ");
		String input = BankManager.sc.next();
		
		System.out.print("이체할 금액 : ");
		String input2 = BankManager.sc.next();
		
		try {
			int accNum = Integer.parseInt(input);
			int money = Integer.parseInt(input2);
			
			// 상대방 계좌확인
			int accIdx = -1;
			for(int i=0; i<this.accs.size(); i++) {
				if(this.accs.get(i).getAccNum() == accNum) {
					accIdx = i;
				}
			}
			
			// 내 계좌 확인
			int myAccIdx = selectUserAccs(log, "출금할 ");
			int balance = this.accs.get(myAccIdx).getMoney();
			
			if(balance >= money && myAccIdx != accIdx) {
				// 이체시작
				balance -= money;
				this.accs.get(myAccIdx).setMoney(balance);
				
				balance = this.accs.get(accIdx).getMoney();
				balance += money;
				this.accs.get(accIdx).setMoney(money);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
