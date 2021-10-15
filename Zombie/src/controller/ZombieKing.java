package controller;

public class ZombieKing extends Unit{

	int shield;
	ZombieKing(String name, int hp, int att, int def, int pos, int shield) {
		super(name, hp, att, def, pos);
		this.shield = shield;
	}
	public int getshield() {
		return shield;
	}
	public void setshield(int shield) {
		this.shield = shield;
	}
	public void attack(Unit target) {
		if(rn.nextInt(100)>74) {
			int dam = (this.getatt() - target.getdef())*(rn.nextInt(150)+50)/100;
			if(dam<=0) {dam = 1;}
			dam*=2;		// 증감연산자 기억하자
			System.out.println(getname()+"의 필살기!!!!!!");
			System.out.println(dam+"의 대미지!");
			target.sethp(target.gethp()-dam);
			System.out.println(target.getname()+"의 남은 체력 : "+target.gethp());
		}else {
			super.attack(target);
		}
	}
	
}
