package models;

public class Unit {
	String name;
	int level;
	int hp;
	int maxHp;
	int att;
	int def;
	int exp;
	boolean party;
	Item weapon;
	Item armor;
	Item ring;
	
	public Unit(String n, int l, int h, int a, int d, int e) {
		name = n;	level = l;	maxHp = h;	att = a;
		def = d;	exp = e;	hp = maxHp;	
		party = false;
		weapon = null;	
		armor = null;	
		ring = null;
	}
}
