package creature;

import java.awt.*;
import java.util.LinkedList;

import move.Move;
import move.MoveKing;
import move.MoveKnight;

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

	
	@Override
	public Move setMove(Creature[][] game, Creature c,
	      LinkedList<Creature> list) {
		return new MoveKnight(game, c);
		
	}
}
