package creature;

import java.awt.Color;
import java.util.Random;

import ch.eiafr.gl.simulife.model.ICreature;

public class Ice implements ICreature {

	private Random rd;
	private int row;
	private int col;
	public String icon = "ice.gif";
	public String id = "I";
	public Color color = Color.BLUE;
	private boolean allpositioncheck = false;

	public Ice() {
		rd = new Random();
	}

	public void setPosition(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int[] addIce(ICreature[][] game) {
		int[] move = new int[2];

		int pourc = rd.nextInt(10);
		if (pourc < 4) {
			int pos = 1;
			int tmpRow = row, tmpCol = col;
			while (game[tmpRow][tmpCol] != null && !allpositioncheck) {
				switch (pos) {

				case 1:
					tmpRow = row - 1;
					tmpCol = col;
					pos++;
					break;

				case 2:
					tmpRow = row;
					tmpCol = col - 1;
					pos++;
					break;
				case 3:
					tmpRow = row;
					tmpCol = col + 1;
					pos++;
					break;

				case 4:
					tmpRow = row + 1;
					tmpCol = col;
					pos = 1;
					allpositioncheck = true;
					break;

				default:
					break;
				}
				if (tmpCol < 0 || tmpCol == game.length || tmpRow == game.length || tmpRow < 0) {
					tmpCol=col;
					tmpRow=row;
					if (pos < 4)
						pos++;
					else
						pos = 1;
				}

			}
			if (allpositioncheck) {
				allpositioncheck = false;
				return null;
			}
			move[0] = tmpRow;
			move[1] = tmpCol;
		} else {
			move[0] = row;
			move[1] = col;
		}

		return move;
	}

	public int[] removeIce(ICreature[][] game) {
		int[] move = new int[2];
		// if (firstMove) {

		int pourc = rd.nextInt(10);
		if (pourc < 4) {
			return null;
		} else {
			move[0] = row;
			move[1] = col;
		}

		return move;
	}

}
