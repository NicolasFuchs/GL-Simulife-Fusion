package creature;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

import move.Move;
import move.MoveShark;

public class HammerheadShark extends Shark {

	boolean firstMove = true;
	private Random rd;
	private int row;
	private int col;
	public String icon = "sharkHammer.gif";
	public String id = "H";

	public HammerheadShark(Point position) {
		super(position);
	}

	public void setPosition(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String getPath() {
		return "resources/sharkHammer.png";
	}

	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public Move setMove(Creature[][] game, Creature c,
	      LinkedList<Creature> list) {
		return new MoveShark(game, c,list);
		
	}
}
