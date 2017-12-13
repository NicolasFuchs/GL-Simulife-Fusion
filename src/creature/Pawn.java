package creature;

import java.awt.*;
import java.util.LinkedList;

import move.Move;
import move.MoveKing;
import move.MovePawn;

public class Pawn extends Creature {

	public String id = "P";

	public Pawn(boolean isBlack, Point position) {
		super(isBlack, position);
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return Color.ORANGE;
	}

	@Override
	public String getPath() {
		if (isBlack) {
			return "resources/pawn_B.png";
		} else {
			return "resources/pawn_W.png";

		}
	}

	@Override
	public String getId() {
		return id;
	}
	@Override
	public Move setMove(Creature[][] game, Creature c, LinkedList<Creature> list) {
		return new MovePawn(game, c,list);
		
	}
}
