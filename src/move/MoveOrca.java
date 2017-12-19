package move;

import java.awt.Point;
import java.util.Random;

import creature.Creature;

public class MoveOrca extends Move {

  Random rd;

  public MoveOrca(Creature[][] game, Creature c) {
    super(game, c);
    rd = new Random();
    
    int newX, newY;
    do {
      newX = rd.nextInt(game[0].length);
      newY = rd.nextInt(game.length);
    } while (game[newY][newX] != null);
    setPosition(new Point(newX, newY));
  }
}
