package move;

import java.awt.Point;

import creature.Creature;

public class Move implements IMove {

  Creature     creature;
  Creature[][] game;
  int          dx, dy;

  public Move(Creature[][] game, Creature c) {
    creature = c;
    this.game = game;
  }

  @Override
  public void execute() {
    //System.out.println("Move-Execute: " +creature.getClass()+  " (" + dx +"/"+dy+")");
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
