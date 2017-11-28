package creature;

import java.awt.Color;
import java.util.Random;

import ch.eiafr.gl.simulife.model.ICreature;

public class Orca implements ICreature {

	private static Orca instance = null;

	boolean firstMove = true;
	private static Random rd;
	private int row;
	private int col;
	public String icon = "orca.gif";
	public String id = "O";
	public Color color = Color.GREEN;

	public void setPosition(int row,int col) {
		this.row=row;
		this.col=col;
	}
	public int[] calcMove(ICreature[][] game) {
		int[] move = new int[2];

		do {
			row = rd.nextInt(game.length);
			col = rd.nextInt(game.length);
		} while (game[row][col] != null);

		move[0] = row;
		move[1] = col;
		return move;
	}

	public static Orca getInstance() {
		if (instance == null) {
			instance = new Orca();
			rd = new Random();
		}
		return instance;
	}

}
