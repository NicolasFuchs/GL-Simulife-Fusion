package creature;

import java.awt.Point;
import java.util.Random;

import ch.eiafr.gl.simulife.model.ICreature;

public class HammerheadShark extends Shark {

  boolean        firstMove = true;
  private Random rd;
  private int    row;
  private int    col;
  public String  icon      = "sharkHammer.gif";
  public String  id        = "H";

  public HammerheadShark(Point position) {
    super(position);
  }

  public void setPosition(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int[] calcMove(ICreature[][] game) {
    int[] move = new int[2];
    // if (firstMove) {
    rd = new Random();
    do {
      row = rd.nextInt(game.length);
      col = rd.nextInt(game.length);
    } while (game[row][col] != null);
    move[0] = row;
    move[1] = col;

    firstMove = false;

    // }

    // TODO Auto-generated method stub
    return move;
  }

}
