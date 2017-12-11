package creature;

import java.awt.*;
import java.util.LinkedList;

import move.Move;
import move.MoveKing;
import move.MoveQueen;

public class Queen extends Creature {
	public String id = "Q";

	public Queen(boolean isBlack, Point position) {
		super(isBlack, position);
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return Color.PINK;
	}

	@Override
	public String getPath() {
		if (isBlack) {
			return "resources/queen_B.png";
		} else {
			return "resources/queen_W.png";

		}
	}

	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public Move setMove(Creature[][] game, Creature c, LinkedList<Creature> list) {
		return new MoveQueen(game, c);
		
	}
}
