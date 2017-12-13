package creature;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;

import move.Move;
import move.MoveKing;
import move.MoveShark;

abstract class Shark extends Creature {

  public Color color  = Color.RED;
  int          hunger = 10;

  public boolean isHunger() {
    return hunger == 0;
  }

  public Shark(Point position) {
    super(position);
  }

  @Override
  public Move setMove(Creature[][] game, Creature c,
      LinkedList<Creature> list) {
    return new MoveShark(game, c);

  }
}
