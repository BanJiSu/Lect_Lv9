package myZombie;

public class Player extends Unit implements Pp{

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
			System.out.println("회복약을 마십니다.");
			System.out.println("체력이 100회복 되었습니다.");
			this.sethp(this.gethp()+100);
			System.out.println(this.getname()+"의 남은 체력 : "+this.gethp());
			cnt--;
		}
		else {
			System.out.println("물약이 없습니다.");
		}
	}
	public void attack(Unit target) {
		if(target instanceof BossZombie) {
			if(((BossZombie) target).shield > 0) {
				int dam = (this.getatt() - target.getdef())*(rn.nextInt(150)+50)/100;
				if(dam<=0) {dam = 1;}
				System.out.println(getname()+"의 공격!");
				System.out.println(dam+"의 대미지!");
				((BossZombie)target).setshield(((BossZombie)target).getshield()-dam);
				if(((BossZombie)target).getshield() <= 0) {
					System.out.println(target.getname() + "의 실드 소멸!");
					((BossZombie)target).setshield(0);
				}
				System.out.println(target.getname() + "의 남은 체력 : " + target.gethp() 
				+ " (쉴드 : "+((BossZombie) target).getshield()+")");
			}
			else {
				super.attack(target);
			}
		}
		else {
			super.attack(target);
		}
	}

}
