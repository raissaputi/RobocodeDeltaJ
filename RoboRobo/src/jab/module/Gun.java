package jab.module;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import robocode.Bullet;
import robocode.Event;
import jab.module.Module;

/**
 * Gun
 * 
 * @author jab
 */
public class Gun extends Part {

	public Module bot;

	public Gun(Module bot) {
		this.bot = bot;
		fireRequested = false;
	}

	public void fire() {
		if (fireRequested) {
			bot.setFire(1); // Fire with power 1
			fireRequested = false;
		}
		if (bot.bulletPower > 0 && bot.getGunHeat() == 0) {
			Bullet b = bot.setFireBullet(bot.bulletPower);
			bot.registerBullet(b);
		}
	}

	private boolean fireRequested;

	public void listen(Event e) {
		if (e instanceof MouseEvent) {
			MouseEvent mouseEvent = (MouseEvent) e;
			if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
				handleMouseClick(mouseEvent);
			}
		}
	}

	private void handleMouseClick(MouseEvent e) {
		fireRequested = true;
	}

	public void listenInput(InputEvent e) {
		if (e instanceof MouseEvent) {
			if (e.getID() == MouseEvent.MOUSE_PRESSED) {
				if (((MouseEvent) e).getButton() == MouseEvent.BUTTON3) {
					bot.bulletPower = 3;
				} else if (((MouseEvent) e).getButton() == MouseEvent.BUTTON2) {
					bot.bulletPower = 2;
				} else {
					bot.bulletPower = 1;
				}
			}

			if (e.getID() == MouseEvent.MOUSE_RELEASED) {
				bot.bulletPower = 0;
			}
		}
	}

}
