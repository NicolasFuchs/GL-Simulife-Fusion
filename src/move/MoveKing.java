package move;

import java.awt.Point;
import java.util.Random;

import creature.Creature;

public class MoveKing extends Move {

  Random rd;

  public MoveKing(Creature[][] game, Creature c) {
    super(game, c);
    rd = new Random();

    int dx, dy;
    int newX, newY;
    do {
      dx = rd.nextInt(3)-1;
      dy = rd.nextInt(3)-1;
      newX = c.getPosition().x + dx;
      newY = c.getPosition().y + dy;

      if(dx == 0 && dy == 0)
        break;
      System.out.println("Recalc King move: (" + newX + "/" + newY + ")");

    } while (!isValidPoint(newX) || !isValidPoint(newY)
        || game[newY][newX] != null);
    setPosition(new Point(newX, newY));
  }
}
