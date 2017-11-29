package move;

import creature.Creature;

import java.awt.*;
import java.util.Random;

public class MoveQueen extends Move {

  Random rd;

  public MoveQueen(Creature[][] game, Creature c) {
    super(game, c);
    rd = new Random();

    int dx, dy;
    int newX, newY;
    do {
      int bound = (game.length * 2) - 1;
      if (rd.nextBoolean()) { // diagonal
        dx = rd.nextInt(bound) - game.length + 1;
        dy = rd.nextBoolean() ? dx : -dx;
      } else {
        if(rd.nextBoolean()){ // straight -> x
          dx = rd.nextInt(bound) - game.length + 1;
          dy = 0;
        }else{
          dx = 0;
          dy = rd.nextInt(bound) - game.length + 1;
        }
      }
      newX = c.getPosition().x + dx;
      newY = c.getPosition().y + dy;
    } while (!isValidPoint(newX) || !isValidPoint(newY)
        || game[newY][newX] != null);
    setPosition(new Point(newX, newY));
  }

}
