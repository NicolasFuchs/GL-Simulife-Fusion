package move;

import java.awt.Point;

import creature.Creature;

public class Move implements IMove {

  private Creature     creature;
  private Creature[][] game;
  private int          dx, dy;

  public Move(Creature[][] game, Creature c) {
    creature = c;
    this.game = game;
  }

  @Override
  public void execute() {
    creature.move(dx, dy);
  }

  @Override
  public void undoMove() {
    // TODO Auto-generated method stub
  }

  @Override
  public boolean isValidPoint(int pointCoord) {
    return pointCoord >= 0 && pointCoord <= 7;
  }

  public void setPosition(Point position) {
    dx = position.x;
    dy = position.y;
  }

}
