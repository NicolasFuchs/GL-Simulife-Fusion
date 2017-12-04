package creature;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import ch.eiafr.gl.simulife.model.ICreature;

public class Orca extends Creature {

	private static Orca instance = null;

	boolean firstMove = true;
	private static Random rd;
	private int row;
	private int col;
	public String icon = "orca.gif";
	public String id = "O";
	public Color color = Color.GREEN;

	public Orca(Point position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return Color.CYAN;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getPath() {
		return "resources/orca.png";
	}

	public void setPosition(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public static Orca getInstance(Point p) {
		if (instance == null) {
			instance = new Orca(p);
			rd = new Random();
		}
		return instance;
	}

}
