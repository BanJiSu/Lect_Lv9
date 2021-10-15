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
		p = new Player("용사", 100, 5, 1, 1);
		monster.add(new Zombie("그냥좀비", 25, 5, 1, 3));
		monster.add(new Zombie("힘쌘좀비", 45, 10, 2, 6));
		monster.add(new Zombie("정예좀비", 65, 15, 3, 9));
		monster.add(new BossZombie("좀비왕",100,20,4,12,50));
	}
	private int chk() {
		for(int i=0; i<monster.size();i++) {
			if(p.getpos() == monster.get(i).getpos()) {
				System.out.println("좀비가 나타났다!!!");
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
			System.out.println("1.공격  2.물약사용(" + p.getcnt() + ")");
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
			System.out.println("사망했다....");
			return false;
		}
		else {
			System.out.println("승리했다!");
			return true;
		}
	}
	private void map(int a) {
		System.out.println("=== 현재 층 : " + p.getpos() + " ===");
		System.out.println("1. 올라간다.");
		if(a == 1) {
			System.out.println("2. 휴식");
			System.out.println("3. 강화");
		}
		System.out.print("선택 : ");
	}
	
	public void run() {
		init();
		int act = 1;
		while(true) {
			if(p.getpos()>=12) {
				System.out.println("던전 탈출!!");
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
					System.out.println("아무것도 없다.");
				}
				act = 1;
			}
			else if(sel == 2 && act == 1) {
				int r = rn.nextInt(50) + 20;
				p.sethp(p.gethp()+r);
				System.out.println("체력 " + r + "만큼 회복");
				if(r <= 30) {
					System.out.println("땅에서 빛이?!!!");
					int r2 = rn.nextInt(3)+1;
					if(r2 == 1) {
						p.sethp(p.gethp()+100);
						System.out.println("추가 체력 100 회복!");
					}
					else if(r2 == 2) {
						p.setatt(p.getatt()+5);
						System.out.println("공격력 5 상승!");
					}
					else if(r2 == 3) {
						p.setdef(p.getdef()+5);
						System.out.println("방어력 5 상승!");
					}
				}
				act = 2;
			}
			else if(sel == 3 && act == 1) {
				int r = rn.nextInt(2)+1;
				if(r == 1) {
					r = rn.nextInt(3)+1;
					p.setatt(p.getatt()+r);
					System.out.println("공격력 " + r + "만큼 상승");
				}
				else if(r == 2) {
					r = rn.nextInt(3)+1;
					p.setdef(p.getdef()+r);
					System.out.println("방어력 " + r + "만큼 상승");
				}
				act = 2;
			}
		}
		
	}
}
