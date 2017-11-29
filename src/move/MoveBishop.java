package move;

import java.awt.Point;
import java.util.Random;

import creature.Creature;

public class MoveBishop extends Move {

  Random rd;

  public MoveBishop(Creature[][] game, Creature c) {
    super(game, c);
    rd = new Random();

    int dx, dy;
    int newX, newY;
    do {
      int bound = (game.length * 2) - 1;
      dx = rd.nextInt(bound) - game.length + 1;
      dy = rd.nextBoolean() ? dx : -dx;
      newX = c.getPosition().x + dx;
      newY = c.getPosition().y + dy;
    } while (!isValidPoint(newX) || !isValidPoint(newY)
        || game[newY][newX] != null);
    setPosition(new Point(newX, newY));
  }
}
