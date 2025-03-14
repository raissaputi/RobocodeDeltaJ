package jab.module;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import robocode.util.Utils;
import jab.module.Module;

/**
 * Radar
 * 
 * @author jab
 */
public class Radar extends Part {

	public Module bot;

	public Radar(Module bot) {
		this.bot = bot;
	}

	public void scan() {
		double angle = Utils.normalAbsoluteAngle(Math.atan2(aimX - bot.getX(), aimY - bot.getY()));
		bot.setTurnRadarRightRadians(Utils.normalRelativeAngle(angle - bot.getRadarHeadingRadians()));
	}

	private int aimX;
	private int aimY;

	public void listenInput(InputEvent e) {
		if (e instanceof MouseEvent) {
			aimX = ((MouseEvent) e).getX();
			aimY = ((MouseEvent) e).getY();
		}
	}

}
