package Game;

public class MissileThread extends Thread {
	
	private Missile myMissile;
	private int Xspeed, Yspeed, MaxX, MaxY;
	
	public MissileThread(Missile theMissile, int theXspeed, int theYspeed, int maxX, int maxY) {
		myMissile = theMissile;
		Xspeed = theXspeed;
		Yspeed = theYspeed;
		MaxX = maxX;
		MaxY = maxY;
	}

	public void run() {
		System.out.println("Missile started");
		try {
			while (true) {
				Thread.sleep(10);
				myMissile.x += Xspeed;
				myMissile.y += Yspeed;
				if ((myMissile.x > MaxX) || (myMissile.y > MaxY)) {
					myMissile.active = false;
					// Die die die
					System.out.println("Missile died");
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
