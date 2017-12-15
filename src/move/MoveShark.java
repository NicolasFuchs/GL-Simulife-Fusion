package move;

import java.awt.Point;
import java.util.Random;

import creature.Creature;

public class MoveShark extends Move {

  Random rd;

  public MoveShark(Creature[][] game, Creature c) {
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

      } while (!isValidPoint(newX) || !isValidPoint(newY)
          || game[newY][newX] != null);
      setPosition(new Point(newX, newY));
    }
  }
