package jab.module;

import robocode.Event;
import robocode.HitWallEvent;
import jab.module.Module;
import java.util.Random;

/**
 * Movement
 * 
 * @author jab
 */
public class Movement extends Part {

	public Module bot;

	public Movement(Module bot) {
		this.bot = bot;
		this.random = new Random();
		this.moveDirection = 1;
	}

	public void move() {
		bot.setMaxVelocity(8);

		double randomDistance = 50 + (random.nextDouble() * 200);

		double randomTurn = -90 + (random.nextDouble() * 180);

		bot.setAhead(randomDistance * moveDirection);
		bot.setTurnRight(randomTurn);
	}

	private int moveDirection;
	private Random random;

	public void listen(Event e) {
		if (e instanceof HitWallEvent) {
			moveDirection *= -1;
			bot.setTurnRight(random.nextInt(180) - 90);
		}
	}

}
