package move;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import creature.Creature;

public class MoveBishop extends Move {

  private Random           rd;
  private Creature[][]     game;
  private Creature         c;
  private ArrayList<Point> moves;
  private ArrayList<Point> favMoves;
  private LinkedList<Creature> list;

  public MoveBishop(Creature[][] game, Creature c, LinkedList<Creature> list) {
    super(game, c, list);
    this.c = c;
    this.game = game;
    this.list = list;
    rd = new Random();

    createPossibleMoves();

    chooseRandomMove();
  }
  
  protected void chooseRandomMove() {
    Point destination;
    if (!favMoves.isEmpty()) {
      int index = rd.nextInt(favMoves.size());
      destination = favMoves.get(index);

      // kill enemy
      Creature toKill = game[destination.y][destination.x];
      game[destination.y][destination.x] = null;
      list.remove(toKill);
    } else {
      int index = rd.nextInt(moves.size());
      destination = moves.get(index);
    }
    setPosition(destination);
  }

  protected void createPossibleMoves() {
    moves = new ArrayList<>();
    favMoves = new ArrayList<>();

    boolean[][] possibleGrid = new boolean[game.length][game[0].length];

    int x = c.getPosition().x;
    int y = c.getPosition().y;
    possibleGrid[y][x] = true;
    moves.add(new Point(x, y));

    int[][] deltas = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

    for (int i = 0; i < deltas.length; i++) {
      int dX = deltas[i][0];
      int dY = deltas[i][1];

      int newX = x + dX;
      int newY = y + dY;
      
      if(!isValidPoint(newX) || !isValidPoint(newY))
        continue;
      // do one direction
      do {
        Creature destCreature = game[newY][newX];
        Point dest = new Point(newX, newY);
        if (destCreature != null) {
          if (destCreature.isBlack() != c.isBlack()) {
            favMoves.add(dest);
            possibleGrid[newY][newX] = true;
          }
          break;
        }
        moves.add(dest);
        possibleGrid[newY][newX] = true;
        newX += dX;
        newY += dY;
      } while (newX >= 0 && newX < game[0].length && newY >= 0
          && newY < game.length);
    }
    
    for (int i = 0; i < possibleGrid.length; i++) {
      for (int j = 0; j < possibleGrid[i].length; j++) {
        System.out.print(possibleGrid[i][j] ? "T " : "F ");
      }
      System.out.println();
    }
    System.out.println("Bishop");
  }
}
