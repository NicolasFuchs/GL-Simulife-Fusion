package move;

import java.awt.Point;
import java.util.Random;

import creature.Creature;

public class MoveKnight extends Move {

  Random rd;

  public MoveKnight(Creature[][] game, Creature c) {
    super(game, c);
    rd = new Random();

    int dx, dy;
    int newX, newY;
    do {
      int moveInt = rd.nextInt(8);

      switch (moveInt) {
        case 0:
          dx = 2;
          dy = 1;
          break;
        case 1:
          dx = 2;
          dy = -1;
          break;
        case 2:
          dx = -2;
          dy = 1;
          break;
        case 3:
          dx = -2;
          dy = -1;
          break;
        case 4:
          dx = 1;
          dy = 2;
          break;
        case 5:
          dx = 1;
          dy = -2;
          break;
        case 6:
          dx = -1;
          dy = 2;
          break;
        case 7:
          dx = -1;
          dy = -2;
          break;
        default:
          dx = 0;
          dy = 0;
      }

      newX = c.getPosition().x + dx;
      newY = c.getPosition().y + dy;
    } while (!isValidPoint(newX) || !isValidPoint(newY)
        || game[newY][newX] != null);

    setPosition(new Point(newX, newY));
  }
}
