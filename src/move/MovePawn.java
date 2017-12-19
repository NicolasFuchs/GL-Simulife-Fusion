package move;

import creature.Creature;
import creature.Pawn;
import creature.Rook;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class MovePawn extends Move {

  Random rd;

  public MovePawn(Creature[][] game, Creature c, LinkedList<Creature> list) {
    super(game, c);
    int x = c.getPosition().x;
    int y = c.getPosition().y;
    if (y == 0) {
      setPosition(new Point(x, y));
      return;
    }

    rd = new Random();
    boolean isAttacking = false;
    boolean isAttackingLeft = isAttackingPositionLeft(game, c);
    boolean isAttackingRight = isAttackingPositionRight(game, c);

    if (isAttackingLeft || isAttackingRight)
      isAttacking = rd.nextBoolean();

    if (isAttacking) {
      if (isAttackingLeft) {
        Creature toKill = game[y - 1][x - 1];
        game[y - 1][x - 1] = null;
        list.remove(toKill);
        setPosition(new Point(x - 1, y - 1));
      } else if (isAttackingRight) {
        Creature toKill = game[y - 1][x + 1];
        game[y - 1][x + 1] = null;
        list.remove(toKill);
        setPosition(new Point(x + 1, y - 1));
      }
    } else {
      int dy;
      int newX = c.getPosition().x;
      int newY = c.getPosition().y;
      do {
        if (c.getPosition().y == 0)
          break;
        dy = rd.nextInt(2);
        newX = c.getPosition().x;
        newY = c.getPosition().y - dy;
        if (dy == 0)
          break;
        System.out.println("Recalc Pawn move: (" + newX + "/" + newY + ")");
      } while (!isValidPoint(newX) || !isValidPoint(newY)
          || game[newY][newX] != null);
      setPosition(new Point(newX, newY));
    }
  }

  private boolean isAttackingPositionLeft(Creature[][] game, Creature c) {
    int x = c.getPosition().x;
    int y = c.getPosition().y;
    if (y == 0)
      return false;
    if (x == 0)
      return false;
    
    Creature target = game[y - 1][x - 1];
    if (target == null)
      return false;
    if (target instanceof Pawn)
      return false;
    if (target instanceof Rook)
      return false;
    return true;
  }

  private boolean isAttackingPositionRight(Creature[][] game, Creature c) {
    int x = c.getPosition().x;
    int y = c.getPosition().y;
    if (y == 0)
      return false;
    if (x == (game.length - 1))
      return false;

    Creature target = game[y - 1][x + 1];
    if (target == null)
      return false;
    if (target instanceof Pawn)
      return false;
    if (target instanceof Rook)
      return false;
    return true;
  }
}
