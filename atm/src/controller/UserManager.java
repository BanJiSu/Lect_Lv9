package controller;

import java.util.ArrayList;
import java.util.Random;

import models.Account;
import models.User;

public class UserManager {
	
	// User �迭�� ����
	private ArrayList<User> users = null;
	
	public static UserManager instance = new UserManager();
	
	private UserManager() {
		this.users = new ArrayList<>();
	}
	
	// ��� �޼ҵ� ����
	
	// ����
	public void joinUser() {
		System.out.print("id : ");
		String id = BankManager.sc.next();
		System.out.println("pw : ");
		String pw = BankManager.sc.next();
		
		boolean check = false;
		for(User user : users) {
			if(id.equals(user.getId()))
				check = true;
		}
		if(!check) {
			System.out.print("name : ");
			String name = BankManager.sc.next();
			
			this.users.add(new User(randomCode(), id, pw, name));
		}
		else {
			System.out.println("�ߺ��� ���̵� �Դϴ�.");
		}
	}
	
	public void addUser(User e) {
		this.users.add(e);
	}
	
	public void removeUser() {
		System.out.print("id : ");
		String id = BankManager.sc.next();
		System.out.println("pw : ");
		String pw = BankManager.sc.next();
		
		User delUser = null;
		for(User user : users) {
			if(id.equals(user.getId()) && pw.equals(user.getPw())) {
				delUser = user;
			}
		}
		this.users.remove(delUser);
	}
	
	private int randomCode() {
		Random rn = new Random();
		
		while(true) {
			int rCode = rn.nextInt(8999) + 1000;
			
			boolean check = false;
			for(User user : users) {
				if(rCode == user.getCode())
					check = true;
			}
			if(!check)
				return rCode;
		}
	}
	
	public void removeAcc(int log, Account e) {
		this.users.get(log).removeAcc(e);
	}
	
	// Ż��
	// ������
	public int getUsersSize() {
		return this.users.size();
	}
	
	// ȸ����ȸ
	public User getUser(int index) {
		return this.users.get(index);
	}
	public int getUserAccSize(int log) {
		return this.users.get(log).getUserAccsSize();
	}
	public void addAcc(int log, Account e) {
		this.users.get(log).addAcc(e);
	}
}
