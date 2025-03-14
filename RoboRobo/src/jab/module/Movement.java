package jab.module;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
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
		moveDirection = 0;
		turnDirection = 0;
		moveAmount = 0;
	}

	public void move() {
		bot.setAhead(moveAmount * moveDirection);
		moveAmount = Math.max(0, moveAmount - 1);
		bot.setTurnRight(45 * turnDirection);
	}

	private int moveDirection;
	private int turnDirection;
	private double moveAmount;

	public void listenInput(InputEvent e) {
		if (e instanceof KeyEvent) {
			KeyEvent keyEvent = (KeyEvent) e;
			if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
				switch (keyEvent.getKeyCode()) {
				case KeyEvent.VK_UP:
					moveDirection = 1;
					moveAmount = Double.POSITIVE_INFINITY;
					break;
				case KeyEvent.VK_DOWN:
					moveDirection = -1;
					moveAmount = Double.POSITIVE_INFINITY;
					break;
				case KeyEvent.VK_RIGHT:
					turnDirection = 1;
					break;
				case KeyEvent.VK_LEFT:
					turnDirection = -1;
					break;
				}
			} else if (keyEvent.getID() == KeyEvent.KEY_RELEASED) {
				switch (keyEvent.getKeyCode()) {
				case KeyEvent.VK_UP:
				case KeyEvent.VK_DOWN:
					moveDirection = 0;
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_LEFT:
					turnDirection = 0;
					break;
				}
			}
		}
	}

}
