package Poly;

abstract class Unit {
	int curhp;
	int maxhp;
	int power;
	String name;
	String state = "³ë¸»";
	Unit(){};
	Unit(String na , int max , int pw){
		name = na;
		maxhp = max;
		curhp = max;
		power = pw;
	}
	
}
