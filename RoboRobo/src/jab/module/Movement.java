package jab.module;

import robocode.Event;
import robocode.HitWallEvent;
import jab.module.Module;

/**
 * Movement
 * 
 * @author jab
 */
public class Movement extends Part {

	public Module bot;

	public Movement(Module bot) {
		this.bot = bot;
		moveDirection = 1;
	}

	public void move() {
		// Limit our speed to 5
		bot.setMaxVelocity(8);
		// Start moving in the current direction
		bot.setAhead(10000 * moveDirection);
	}

	private int moveDirection;

	public void listen(Event e) {
		if (e instanceof HitWallEvent) {
			moveDirection *= -1; // Reverse direction when hitting a wall
		}
	}

}
