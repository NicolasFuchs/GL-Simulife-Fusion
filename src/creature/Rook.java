package creature;

import java.awt.*;
import java.util.LinkedList;

import move.Move;
import move.MoveKing;
import move.MoveRook;

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

	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public Move setMove(Creature[][] game, Creature c,
	      LinkedList<Creature> list) {
		return new MoveRook(game, c, list);
		
	}
}
