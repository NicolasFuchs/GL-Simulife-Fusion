package creature;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import ch.eiafr.gl.simulife.model.ICreature;

public class Ice extends Creature {

  private Random  rd;
  private int     row;
  private int     col;
  public String   icon             = "ice.gif";
  public String   id               = "I";
  public Color    color            = Color.BLUE;

  public Ice(Point position) {
    super(position);
    rd = new Random();
  }

  public void setPosition(int row, int col) {
    this.row = row;
    this.col = col;
  }

@Override
public Color getColor() {
	return Color.BLUE;
}

@Override
public String getPath() {
	return "resources/ice.gif";
}



  
}
