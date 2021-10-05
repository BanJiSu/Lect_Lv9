package controller;

import java.util.ArrayList;
import java.util.Scanner;

import models.User;

public class UserManager {

	Scanner sc = new Scanner(System.in);
	ArrayList<User> userList = new ArrayList<User>();
	
	public void join() {
		System.out.println("[가입] id 를 입력하세요.");
		String id = sc.next();
		User temp = new User(id, 0);
		userList.add(temp);
		System.out.println("[메세지] " + temp.id + "님 가입을 축하합니다.");
	}
}
