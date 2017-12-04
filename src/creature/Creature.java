package creature;

import java.awt.Color;
import java.awt.Point;

import ch.eiafr.gl.simulife.model.ICreature;

public class Creature implements ICreature {

	boolean isBlack;
	Point position;

	public enum CreatureType {
		HAMMERSHARK, ICE, ORCA, PENGUIN, SHARK, WHITESHARK, BISHOP, KING, KNIGHT, PAWN, QUEEN, ROOK
	}

	public Creature(Point position) {
		this.position = position;
	}

	public Creature(boolean isBlack, Point position) {
		this.isBlack = isBlack;
		this.position = position;
	}

	public void move(int dx, int dy) {
		position.x = dx;
		position.y = dy;
	}

	public Point getPosition() {
		return position;
	}

	public boolean isBlack() {
		return isBlack;
	}

	public int[] calcMove(Creature[][] game) {
		return null;
	}

	public Color getColor() {
		return Color.BLACK;
	}

	public String getPath() {
		return "empty.gif";
	}
}
