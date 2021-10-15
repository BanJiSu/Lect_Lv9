package myZombie;

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
	private Player p;
	private ArrayList<Unit> monster = new ArrayList<>();
	private void init() {
		p = new Player("���", 100, 5, 1, 1);
		monster.add(new Zombie("�׳�����", 25, 5, 1, 3));
		monster.add(new Zombie("��������", 45, 10, 2, 6));
		monster.add(new Zombie("��������", 65, 15, 3, 9));
		monster.add(new BossZombie("�����",100,20,4,12,50));
	}
	private int chk() {
		for(int i=0; i<monster.size();i++) {
			if(p.getpos() == monster.get(i).getpos()) {
				System.out.println("���� ��Ÿ����!!!");
				return i;
			}
		}
		return -1;
	}
	private int die(Unit a) {
		if(p.gethp()<=0) {
			return 1;
		}
		else if(a.gethp()<=0) {
			return 2;
		}
		else {
			return 0;
		}
	}
	
	private boolean fight(Unit monster) {
		while(true) {
			p.print();
			System.out.println("========vs=========");
			monster.print();
			System.out.println("===================");
			System.out.println("1.����  2.������(" + p.getcnt() + ")");
			int sel = sc.nextInt();
			if(sel == 1) {
				p.attack(monster);
			}
			else if (sel == 2) {
				p.drink();
			}
			if(die(monster) != 0) {
				break;
			}
			System.out.println();
			monster.attack(p);
			if(die(monster)!=0) {
				break;
			}
			System.out.println();
			
		}
		if(die(monster) == 1) {
			System.out.println("����ߴ�....");
			return false;
		}
		else {
			System.out.println("�¸��ߴ�!");
			return true;
		}
	}
	private void map(int a) {
		System.out.println("=== ���� �� : " + p.getpos() + " ===");
		System.out.println("1. �ö󰣴�.");
		if(a == 1) {
			System.out.println("2. �޽�");
			System.out.println("3. ��ȭ");
		}
		System.out.print("���� : ");
	}
	
	public void run() {
		init();
		int act = 1;
		while(true) {
			if(p.getpos()>=12) {
				System.out.println("���� Ż��!!");
				break;
			}
			map(act);
			int sel = sc.nextInt();
			if(sel == 1) {
				p.setpos(p.getpos()+1);
				int chk = chk();
				if(chk != -1) {
					boolean a = fight(monster.get(chk));
					if(a == false) {
						break;
					}
				}
				else {
					System.out.println("�ƹ��͵� ����.");
				}
				act = 1;
			}
			else if(sel == 2 && act == 1) {
				int r = rn.nextInt(50) + 20;
				p.sethp(p.gethp()+r);
				System.out.println("ü�� " + r + "��ŭ ȸ��");
				if(r <= 30) {
					System.out.println("������ ����?!!!");
					int r2 = rn.nextInt(3)+1;
					if(r2 == 1) {
						p.sethp(p.gethp()+100);
						System.out.println("�߰� ü�� 100 ȸ��!");
					}
					else if(r2 == 2) {
						p.setatt(p.getatt()+5);
						System.out.println("���ݷ� 5 ���!");
					}
					else if(r2 == 3) {
						p.setdef(p.getdef()+5);
						System.out.println("���� 5 ���!");
					}
				}
				act = 2;
			}
			else if(sel == 3 && act == 1) {
				int r = rn.nextInt(2)+1;
				if(r == 1) {
					r = rn.nextInt(3)+1;
					p.setatt(p.getatt()+r);
					System.out.println("���ݷ� " + r + "��ŭ ���");
				}
				else if(r == 2) {
					r = rn.nextInt(3)+1;
					p.setdef(p.getdef()+r);
					System.out.println("���� " + r + "��ŭ ���");
				}
				act = 2;
			}
		}
		
	}
}
