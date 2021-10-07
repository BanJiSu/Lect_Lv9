package controller;

import java.util.ArrayList;
import java.util.Scanner;

import models.User;

public class UserManager {

	Scanner sc = new Scanner(System.in);
	ArrayList<User> userList = new ArrayList<User>();
	int log = -1;
	
	public void join() {
		System.out.println("[����] id �� �Է��ϼ���.");
		String id = sc.next();
		User temp = new User(id, 5000);
		userList.add(temp);
		System.out.println(temp.id + "�� ������ �����մϴ�.");
		System.out.println("���� ���ϱ� 5000�� �Ա�");
	}
	
	public boolean login() {
		log = -1;
		System.out.println("[�α���] id �� �Է��ϼ���.");
		String id = sc.next();
		for (int i = 0; i < userList.size(); i++) {
			if (id.equals(userList.get(i).id)) {
				log = i;
				break;
			}
		}
		if (log == -1) {
			System.out.println("���� id �Դϴ�.");
		}
		else {
			System.out.println(userList.get(log).id + "�� �α���.");
			return true;
		}
		return false;
	}
	public void logout() {
		if(log != -1) {
			System.out.println(userList.get(log).id + "�� �α׾ƿ� ����");
			log = -1;
		}
	}
	
	User secession() {
		User delUser = userList.get(log);
		userList.remove(log);
		log = -1;
		return delUser;
	}
	
	public void printUser() {
		for (int i = 0; i < userList.size(); i++) {
			System.out.print("[" + i + "] ");
			userList.get(i).print();
		}
	}

	
}
