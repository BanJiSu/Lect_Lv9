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
		User temp = new User(id, 0);
		userList.add(temp);
		System.out.println(temp.id + "�� ������ �����մϴ�.");
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
	
	public void secession() {
		System.out.println("[Ż��] id�� �Է��ϼ���.");
	}

	
}
