package creature;

import java.awt.Color;
import java.awt.Point;

abstract class Shark extends Creature {

	public Color color = Color.RED;
	int hunger = 10;

	public boolean isHunger() {
		return hunger == 0;
	}

	public Shark(Point position) {
		super(position);
	}

}
