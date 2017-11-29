package creature;

import java.awt.Color;
import java.awt.Point;

abstract class Shark extends Creature {

  private int  id;
  public Color color  = Color.RED;
  int          hunger = 10;

  public boolean isHunger() {
    return hunger == 0;
  }

  public Shark(Point position) {
    super(position);
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

}
