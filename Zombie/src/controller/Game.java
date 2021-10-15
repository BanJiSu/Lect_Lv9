package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	Random rn = new Random();
	Scanner sc = new Scanner(System.in);
	private Game() {}
	private static Game instance = new Game();
	public static Game getinstance() {
		return instance;
	}
	private Hero player;
	private ArrayList<Unit> enemy = new ArrayList<>(); 
	private void init() {
		player = new Hero("���", 100, 5, 1, 1);
		enemy.add(new Zombie("�׳�����", 25, 5, 1, 3));
		enemy.add(new Zombie("��������", 45, 10, 2, 6));
		enemy.add(new Zombie("��������", 65, 15, 3, 9));
		enemy.add(new ZombieKing("�����",100,20,4,12,50));
	}
	private int chk() {
		for(int i=0;i<enemy.size();i++) {
			if(player.getpos()==enemy.get(i).getpos()) {
				System.out.println("���� ��Ÿ����!!!");
				return i;
			}
		}
		return -1;
	}
	private int die(Unit a) {
		if(player.gethp()<=0) {
			return 1;
		}
		else if(a.gethp()<=0) {
			return 2;
		}
		else {
			return 0;
		}
	}
	
	private boolean fight(Unit enemy) {
		while(true) {
			player.print();
			System.out.println("===== VS =====");
			enemy.print();
			System.out.println("--------------");
			System.out.println("[������ �ұ�? ]");
			System.out.println("1.����   2.����("+player.getcnt()+"�� ����)");
			System.out.print("���� : ");
			int sel = sc.nextInt();
			if(sel == 1) {
				player.attack(enemy);
			}
			else if(sel == 2) {
				player.drink();
			}
			if(die(enemy)!=0) {
				break;
			}
			System.out.println();
			enemy.attack(player);
			if(die(enemy)!=0) {
				break;
			}
			System.out.println();	
		}
		if(die(enemy) == 1) {
			System.out.println("����ߴ�....");
			return false;
		}
		else {
			System.out.println("�¸��ߴ�!");
			return true;
		}
	}
	private void map(int a) {
		System.out.println(" -- ���� �� : " + player.getpos() + " --");
		System.out.println("1. �ö󰣴�.");
		if(a == 1) {
			System.out.println("2. ü��ȸ��");
			System.out.println("3. ���Ⱝȭ");
		}
		System.out.print("���� : ");
	}
	public void run() {
		init();
		int act = 1;
		while(true) {
			if(player.getpos()>=12) {
				System.out.println("���� Ż��!!");
				break;
			}
			map(act);
			int sel = sc.nextInt();
			if(sel == 1) {
				player.setpos(player.getpos()+1);
				int chk = chk();
				if(chk!=-1) {
					boolean a = fight(enemy.get(chk));
					if(a == false) {
						break;
					}
				}
				else {
					System.out.println("�ƹ��ϵ� �Ͼ�� �ʾҴ�..");
				}
				act = 1;
			}
			else if(sel == 2 && act == 1) {
				int rnum = rn.nextInt(40)+20;
				player.sethp(player.gethp()+rnum);
				System.out.println("ü���� "+rnum+" ��ŭ ȸ���ߴ�!");
				act = 2;
			}
			else if(sel == 3 && act == 1) {
				int rnum = rn.nextInt(2)+1;
				if(rnum==1) {
					rnum = rn.nextInt(3)+1;
					player.setatt(player.getatt()+rnum);
					System.out.println("���ݷ��� "+rnum+"��ŭ �����ߴ�!");
				}
				else if(rnum==2) {
					rnum = rn.nextInt(3)+1;
					player.setdef(player.getdef()+rnum);
					System.out.println("������ "+rnum+"��ŭ �����ߴ�!");
				}
				act = 2;
			}
		}
	}
}
