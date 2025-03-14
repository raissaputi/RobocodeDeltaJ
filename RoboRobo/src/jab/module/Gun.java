package jab.module;

import jab.module.Module;
import robocode.Rules;
import robocode.Bullet;
import java.util.Random;

/**
 * Gun
 * 
 * @author jab
 */
public class Gun extends Part {

	public Module bot;

	public Gun(Module bot) {
		this.bot = bot;
	}

	public void fire() {
		if (bot.enemy == null)
			return;

		long currentTime = System.currentTimeMillis();
		boolean cooledDown = (currentTime - lastBurstTime) > (cooldownTime * 1000);

		// Check if we're in a burst or if we've cooled down enough to start a new burst
		if (burstCount > 0 || cooledDown) {
			// Calculate bullet power - smaller bullets for rapid fire
			double bulletPower = 1.0;

			// Don't waste bullets on dead robots
			if (bot.enemy.energy == 0) {
				burstCount = 0;
				return;
			}

			// Fire if gun is ready
			if (bot.getGunHeat() == 0) {
				// Add slight randomness to simulate recoil
				double randomOffset = (random.nextDouble() - 0.5) * 0.05;
				bot.setTurnGunRight(randomOffset);

				// Fire bullet
				Bullet b = bot.setFireBullet(bulletPower);
				bot.registerBullet(b);

				// Manage burst
				if (burstCount == 0) {
					// Start of new burst
					burstCount = maxBurstCount;
					lastBurstTime = currentTime;
				}

				burstCount--;
			}
		}
	}

	private boolean rifleMode = true;
	private int burstCount = 0;
	private int maxBurstCount = 3;
	private double cooldownTime = 2.0;
	private long lastBurstTime = 0;
	private Random random = new Random();

	public void toggleRifleMode() {
		rifleMode = !rifleMode;
		burstCount = 0;
	}

	public void setBurstSize(int size) {
		if (size > 0 && size <= 10) {
			maxBurstCount = size;
		}
	}

	public void setCooldownTime(double seconds) {
		if (seconds >= 0.5 && seconds <= 5.0) {
			cooldownTime = seconds;
		}
	}

}
