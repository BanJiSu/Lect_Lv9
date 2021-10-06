package controller;

import models.Bank;

public class Main {
	
	public static void main(String[] args) {	// 시스템 실행을 하기 위한 기본 메서드
		
		// 은행 브랜드를 지정하고,
		Bank.instance.setBrand("Green");
		// 은행 시스탬이 run() 하도록 호출
		BankManager.instance.run();
		
	}
}
