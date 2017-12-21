package move;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import creature.Creature;

public class Move implements IMove {

  private Random               rd;
  private Creature             creature;
  private Creature[][]         game;
  private int                  dx, dy;
  private LinkedList<Creature> list;

  public Move(Creature[][] game, Creature c, LinkedList<Creature> list) {
    rd = new Random();
    this.creature = c;
    this.game = game;
    this.list = list;
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
    return pointCoord >= 0 && pointCoord < game.length;
  }

  public void setPosition(Point position) {
    dx = position.x;
    dy = position.y;
  }

}
