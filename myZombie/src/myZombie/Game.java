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
	
	
	public void run() {
		init();
		
	}
}
