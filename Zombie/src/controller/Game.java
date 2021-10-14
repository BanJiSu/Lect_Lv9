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
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
