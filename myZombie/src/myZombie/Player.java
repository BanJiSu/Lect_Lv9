package myZombie;

public class Player extends Unit{

	private int cnt = 3;
	public Player(String name, int hp, int att, int def, int pos) {
		super(name, hp, att, def, pos);
		// TODO Auto-generated constructor stub
	}
	public int getcnt() {
		return cnt;
	}
	public void drink() {
		if(cnt>0) {
			System.out.println("ȸ������ ���ʴϴ�.");
			System.out.println("ü���� 100ȸ�� �Ǿ����ϴ�.");
			this.sethp(this.gethp()+100);
			System.out.println(this.getname()+"�� ���� ü�� : "+this.gethp());
			cnt--;
		}
		else {
			System.out.println("������ �����ϴ�.");
		}
	}
	public void attack(Unit target) {
		if(target instanceof BossZombie) {
			if(((BossZombie) target).shield > 0) {
					
			}
		}
	}
	public void bossatt(Boss boss) {
		Unit unit = (Unit) boss;
	}

}
