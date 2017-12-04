package creature;

import java.awt.*;

public class Knight extends Creature {

	public String id = "C";

	public Knight(boolean isBlack, Point position) {
		super(isBlack, position);
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return Color.MAGENTA;
	}

	@Override
	public String getPath() {
		if (isBlack) {
			return "resources/knight_B.png";
		} else {
			return "resources/knight_W.png";

		}
	}

	@Override
	public String getId() {
		return id;
	}

}
