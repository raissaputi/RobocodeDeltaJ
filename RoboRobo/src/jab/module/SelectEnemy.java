package jab.module;

import robocode.ScannedRobotEvent;
import robocode.Event;
import jab.module.Module;
import java.util.Iterator;
import jab.module.BotInfo;

/**
 * Select enemy
 * 
 * @author jabier.martinez
 */
public class SelectEnemy extends Part {

	public Module bot;

	public SelectEnemy(Module bot) {
		this.bot = bot;
		closestDistance = Double.MAX_VALUE;
		closestEnemy = null;
		target = null;
	}

	public void select() {
		Iterator<BotInfo> iterator = bot.botsInfo.values().iterator();
		double minDistance = Double.MAX_VALUE;
		BotInfo selected = null;
		while (iterator.hasNext()) {
			BotInfo botInfo = iterator.next();
			if ((!botInfo.teammate) && minDistance > botInfo.distance) {
				selected = botInfo;
				minDistance = botInfo.distance;
			}
		}
		bot.enemy = selected;
	}

	private double closestDistance;
	private String closestEnemy;
	private String target;

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTarget() {
		return target;
	}

	public void listen(Event e) {
		if (e instanceof ScannedRobotEvent) {
			ScannedRobotEvent scannedEvent = (ScannedRobotEvent) e;
			double distance = scannedEvent.getDistance();
			if (distance < closestDistance) {
				closestDistance = distance;
				closestEnemy = scannedEvent.getName();
			}
		}
	}

}
