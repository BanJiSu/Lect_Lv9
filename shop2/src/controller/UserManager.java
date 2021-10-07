package controller;

import java.util.ArrayList;
import java.util.Scanner;

import models.User;

public class UserManager {

	Scanner sc = new Scanner(System.in);
	ArrayList<User> userList = new ArrayList<User>();
	int log = -1;
	
	public void join() {
		System.out.println("[가입] id 를 입력하세요.");
		String id = sc.next();
		User temp = new User(id, 5000);
		userList.add(temp);
		System.out.println(temp.id + "님 가입을 축하합니다.");
		System.out.println("가입 축하금 5000원 입금");
	}
	
	public boolean login() {
		log = -1;
		System.out.println("[로그인] id 를 입력하세요.");
		String id = sc.next();
		for (int i = 0; i < userList.size(); i++) {
			if (id.equals(userList.get(i).id)) {
				log = i;
				break;
			}
		}
		if (log == -1) {
			System.out.println("없는 id 입니다.");
		}
		else {
			System.out.println(userList.get(log).id + "님 로그인.");
			return true;
		}
		return false;
	}
	public void logout() {
		if(log != -1) {
			System.out.println(userList.get(log).id + "님 로그아웃 성공");
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
