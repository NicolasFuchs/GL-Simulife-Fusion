package move;

import creature.Creature;

import java.awt.*;
import java.util.Random;

public class MovePawn extends Move {

  Random rd;

  public MovePawn(Creature[][] game, Creature c) {
    super(game, c);
    rd = new Random();

    int dy;
    int newX = c.getPosition().x;
    int newY = c.getPosition().y;
    do {
      if (c.getPosition().y == 0)
        break;
      dy = rd.nextInt(2);
      newX = c.getPosition().x;
      newY = c.getPosition().y - dy;
    } while (!isValidPoint(newX) || !isValidPoint(newY)
        || game[newY][newX] != null);
    setPosition(new Point(newX, newY));
  }
}
