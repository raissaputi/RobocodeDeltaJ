package jab.module;

import jab.module.Module;
import robocode.util.Utils;

/**
 * Targeting
 * 
 * @author jabier.martinez
 */
public class Targeting extends Part {

	public Module bot;

	public Targeting(Module bot) {
		this.bot = bot;
	}

	public void target() {
		if (bot.enemy != null) {
			double absoluteBearing = bot.getHeadingRadians() + bot.enemy.bearingRadians;
			bot.setTurnGunRightRadians(Utils.normalRelativeAngle(absoluteBearing - bot.getGunHeadingRadians()));
		}
	}

}
