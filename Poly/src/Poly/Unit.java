package Poly;

abstract class Unit {
	int curhp;
	int maxhp;
	int power;
	String name;
	String state = "�븻";
	Unit(){};
	Unit(String na , int max , int pw){
		name = na;
		maxhp = max;
		curhp = max;
		power = pw;
	}
	
}
