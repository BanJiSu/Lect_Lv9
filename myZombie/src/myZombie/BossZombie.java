package myZombie;

public class BossZombie extends Unit implements Boss{

	int shield;
	BossZombie(String name, int hp, int att, int def, int pos, int shield) {
		super(name, hp, att, def, pos);
		this.shield = shield;
	}
	
	public int getshield() {
		return shield;
	}
	public void setshield(int shield) {
		this.shield = shield;
	}
	
}
