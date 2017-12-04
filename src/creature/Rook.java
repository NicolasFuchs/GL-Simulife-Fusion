package creature;

import java.awt.*;

public class Rook extends Creature {

	public String id = "R";

	public Rook(boolean isBlack, Point position) {
		super(isBlack, position);
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return Color.yellow;
	}

	@Override
	public String getPath() {
		if (isBlack) {
			return "resources/rook_B.png";
		} else {
			return "resources/rook_W.png";

		}
	}
}
