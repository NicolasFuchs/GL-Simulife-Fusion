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

  
  @Override
  public String getPath() {
  	return "resources/sharkHammer.png";
  }
}
