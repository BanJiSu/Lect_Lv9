package controller;

import java.util.ArrayList;
import java.util.Scanner;

import models.User;

public class UserManager {

	Scanner sc = new Scanner(System.in);
	ArrayList<User> userList = new ArrayList<User>();
	
	public void join() {
		System.out.println("[����] id �� �Է��ϼ���.");
		String id = sc.next();
		User temp = new User(id, 0);
		userList.add(temp);
		System.out.println("[�޼���] " + temp.id + "�� ������ �����մϴ�.");
	}
}
