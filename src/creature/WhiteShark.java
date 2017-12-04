package creature;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import ch.eiafr.gl.simulife.model.ICreature;

public class WhiteShark extends Shark {

  boolean        firstMove = true;
  private Random rd;
  private int    row;
  private int    col;
  public String  icon      = "shark.gif";
  public String  id        = "W";

  public WhiteShark(Point position) {
    super(position);
  }

  public void setPosition(int row, int col) {
    this.row = row;
    this.col = col;
  }

@Override
public Color getColor() {
	return Color.GRAY;
}

@Override
public String getPath() {
	// TODO Auto-generated method stub
	return "resources/sharkWhite.png";
}

}
