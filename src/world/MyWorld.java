package world;

import app.App;
import ch.eiafr.gl.simulife.model.AWorld;
import ch.eiafr.gl.simulife.model.ICreature;
import creature.*;
import creature.Creature.CreatureType;
import move.Move;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class MyWorld extends AWorld {

  private int                  nbCols;
  private int                  nbRows;
  private int                  nbOfPenguins;
  private static final int     NumberOfIce = 3;

  private Creature[][]         game;
  private boolean              gameOver    = false;

  private Random               rd;
  private LinkedList<Creature> list;

  private PieceFactory         pieceFactory;
  private MoveInvoker          invoker;
  private AbstractCreator      liveCreator;
  private AbstractCreator      deadCreator;
  private String               raison;
  private boolean              stepByStep;
  private boolean              isSingleMove;
  private boolean              isWaiting;
  private int                  speed;

  public MyWorld(int nbCols, int nbRows, boolean isChessMode,
      boolean stepByStep, boolean isSingleMove, int nbrOfPenguins, int speed) {
    rd = new Random();
    list = new LinkedList<>();
    pieceFactory = new PieceFactory();
    invoker = new MoveInvoker();
    raison = "";

    this.stepByStep = stepByStep;
    this.isSingleMove = isSingleMove;
    this.nbOfPenguins = nbrOfPenguins;
    this.speed = speed;
    this.nbCols = nbCols;
    this.nbRows = nbRows;
    this.game = new Creature[nbRows][nbCols];
  }

  @Override
  public int getNbCols() {
    return nbCols;
  }

  @Override
  public int getNbRows() {
    return nbRows;
  }

  @Override
  public ICreature getCreatureAt(int col, int row) {
    return game[row][col];
  }

  @Override
  public String getStringFor(ICreature creature) {
    if (creature instanceof Creature)
      return ((Creature) creature).getId();
    return "_";
  }

  @Override
  public ImageIcon getIconeFor(ICreature creature) {

    String path = "empty.gif";
    if (creature instanceof Creature)
      path = ((Creature) creature).getPath();

    return new ImageIcon(ClassLoader.getSystemResource(path));
  }

  public void moveCreature(Creature creature, int newCol, int newRow) {
    for (int row = 0; row < getNbRows(); row++) {
      for (int col = 0; col < getNbCols(); col++) {
        if (game[row][col] == creature)
          game[row][col] = null;
      }
    }
    game[newRow][newCol] = creature;
  }

  @Override
  public Color getColorFor(ICreature creature) {
    if (creature instanceof Creature)
      return ((Creature) creature).getColor();

    return Color.WHITE;
  }

  public int[] calcMove(ICreature creature) {

    int[] move = new int[2];
    if (creature instanceof Orca) {
      Orca orc = (Orca) creature;
      move = orc.calcMove(game);
    }
    if (creature instanceof Penguin) {
      Penguin p = (Penguin) creature;
      move = p.calcMove(game);
    }
    if (creature instanceof WhiteShark) {
      WhiteShark w = (WhiteShark) creature;
      move = w.calcMove(game);
    }
    if (creature instanceof HammerheadShark) {
      HammerheadShark h = (HammerheadShark) creature;
      move = h.calcMove(game);
    }

    return move;
  }

  public void init(boolean isChessLife) {
    if (isChessLife) {
      King kW = (King) pieceFactory.createPiece(CreatureType.KING, false,
          new Point(4, 7));
      King kB = (King) pieceFactory.createPiece(CreatureType.KING, true,
          new Point(4, 0));
      Bishop bW1 = (Bishop) pieceFactory.createPiece(CreatureType.BISHOP, false,
          new Point(2, 6));
      Bishop bW2 = (Bishop) pieceFactory.createPiece(CreatureType.BISHOP, false,
          new Point(5, 6));
      Bishop bB1 = (Bishop) pieceFactory.createPiece(CreatureType.BISHOP, true,
          new Point(2, 1));
      Bishop bB2 = (Bishop) pieceFactory.createPiece(CreatureType.BISHOP, true,
          new Point(5, 1));
      Knight kW1 = (Knight) pieceFactory.createPiece(CreatureType.KNIGHT, false,
          new Point(0, 4));
      Knight kW2 = (Knight) pieceFactory.createPiece(CreatureType.KNIGHT, false,
          new Point(7, 4));
      Queen qW = (Queen) pieceFactory.createPiece(CreatureType.QUEEN, false,
          new Point(3, 0));
      Queen qB = (Queen) pieceFactory.createPiece(CreatureType.QUEEN, true,
          new Point(3, 7));
      Pawn pB1 = (Pawn) pieceFactory.createPiece(CreatureType.PAWN, false,
          new Point(0, 7));
      Pawn pB2 = (Pawn) pieceFactory.createPiece(CreatureType.PAWN, false,
          new Point(7, 7));

      list.add(kW);
      list.add(kB);
      list.add(bW1);
      list.add(bW2);
      list.add(bB1);
      list.add(bB2);
      list.add(kW1);
      list.add(kW2);
      list.add(qW);
      list.add(qB);
      list.add(pB1);
      list.add(pB2);
      /*
       * kW = (King) pieceFactory.createPiece(CreatureType.KING, false, new
       * Point(3, 7)); King kB = (King)
       * pieceFactory.createPiece(CreatureType.KING, true, new Point(3, 0));
       * Bishop bW1 = (Bishop) pieceFactory.createPiece(CreatureType.BISHOP,
       * false, new Point(2, 7)); Bishop bW2 = (Bishop)
       * pieceFactory.createPiece(CreatureType.BISHOP, false, new Point(5, 7));
       * Bishop bB1 = (Bishop) pieceFactory.createPiece(CreatureType.BISHOP,
       * true, new Point(2, 0)); Bishop bB2 = (Bishop)
       * pieceFactory.createPiece(CreatureType.BISHOP, true, new Point(5, 0));
       * Knight kW1 = (Knight) pieceFactory.createPiece(CreatureType.KNIGHT,
       * false, new Point(1, 7)); Knight kW2 = (Knight)
       * pieceFactory.createPiece(CreatureType.KNIGHT, false, new Point(6, 7));
       * Knight kB1 = (Knight) pieceFactory.createPiece(CreatureType.KNIGHT,
       * true, new Point(1, 0)); Knight kB2 = (Knight)
       * pieceFactory.createPiece(CreatureType.KNIGHT, true, new Point(6, 0));
       * Queen qW = (Queen) pieceFactory.createPiece(CreatureType.QUEEN, false,
       * new Point(4, 7)); Queen qB = (Queen)
       * pieceFactory.createPiece(CreatureType.QUEEN, true, new Point(4, 0));
       * Pawn pB1 = (Pawn) pieceFactory.createPiece(CreatureType.PAWN, false,
       * new Point(0, 6)); Pawn pB2 = (Pawn)
       * pieceFactory.createPiece(CreatureType.PAWN, false, new Point(1, 6));
       * Pawn pB3 = (Pawn) pieceFactory.createPiece(CreatureType.PAWN, false,
       * new Point(2, 6)); Pawn pB4 = (Pawn)
       * pieceFactory.createPiece(CreatureType.PAWN, false, new Point(3, 6));
       * Pawn pB5 = (Pawn) pieceFactory.createPiece(CreatureType.PAWN, false,
       * new Point(4, 6)); Pawn pB6 = (Pawn)
       * pieceFactory.createPiece(CreatureType.PAWN, false, new Point(5, 6));
       * Pawn pB7 = (Pawn) pieceFactory.createPiece(CreatureType.PAWN, false,
       * new Point(6, 6)); Pawn pB8 = (Pawn)
       * pieceFactory.createPiece(CreatureType.PAWN, false, new Point(7, 6));
       * Pawn pW1 = (Pawn) pieceFactory.createPiece(CreatureType.PAWN, true, new
       * Point(0, 1)); Pawn pW2 = (Pawn)
       * pieceFactory.createPiece(CreatureType.PAWN, true, new Point(1, 1));
       * Pawn pW3 = (Pawn) pieceFactory.createPiece(CreatureType.PAWN, true, new
       * Point(2, 1)); Pawn pW4 = (Pawn)
       * pieceFactory.createPiece(CreatureType.PAWN, true, new Point(3, 1));
       * Pawn pW5 = (Pawn) pieceFactory.createPiece(CreatureType.PAWN, true, new
       * Point(4, 1)); Pawn pW6 = (Pawn)
       * pieceFactory.createPiece(CreatureType.PAWN, true, new Point(5, 1));
       * Pawn pW7 = (Pawn) pieceFactory.createPiece(CreatureType.PAWN, true, new
       * Point(6, 1)); Pawn pW8 = (Pawn)
       * pieceFactory.createPiece(CreatureType.PAWN, true, new Point(7, 1));
       * 
       * list.add(kW); list.add(kB); list.add(bW1); list.add(bW2);
       * list.add(bB1); list.add(bB2); list.add(kW1); list.add(kW2);
       * list.add(kB1); list.add(kB2); list.add(qW); list.add(qB);
       * list.add(pB1); list.add(pB2); list.add(pB3); list.add(pB4);
       * list.add(pB5); list.add(pB6); list.add(pB7); list.add(pB8);
       * list.add(pW1); list.add(pW2); list.add(pW3); list.add(pW4);
       * list.add(pW5); list.add(pW6); list.add(pW7); list.add(pW8);
       */

    } else {
      liveCreator = new LiveCreator();
      deadCreator = new DeadCreator();

      Orca orca = (Orca) liveCreator.createCreature(CreatureType.ORCA,
          new Point(2, 2));
      list.add(orca);

      for (int i = 0; i < NumberOfIce; i++) {
        Ice ice = (Ice) deadCreator.createCreature(CreatureType.ICE,
            new Point(rd.nextInt(game[0].length), rd.nextInt(game.length)));
        list.add(ice);
      }
      WhiteShark white = (WhiteShark) liveCreator.createCreature(
          CreatureType.WHITESHARK,
          new Point(rd.nextInt(game[0].length), rd.nextInt(game.length)));
      list.add(white);
      HammerheadShark hammer = (HammerheadShark) liveCreator.createCreature(
          CreatureType.HAMMERSHARK,
          new Point(rd.nextInt(game[0].length), rd.nextInt(game.length)));
      list.add(hammer);

      for (int i = 0; i < nbOfPenguins; i++) {
        Penguin peng = (Penguin) liveCreator.createCreature(
            CreatureType.PENGUIN,
            new Point(rd.nextInt(game[0].length), rd.nextInt(game.length)));
        list.add(peng);
      }
    }
    for (Creature c : list) {
      moveCreature(c, c.getPosition().x, c.getPosition().y);
    }
    updateView();
    startSimulation(isChessLife);
  }

  private void startSimulation(boolean isChessLife) {
    while (!gameOver) {
      for (int i = 0; i < list.size(); i++) {
        Creature c = list.get(i);
        simulateMove(c);
        updateView();
        if (isChessLife) {
          if (isSingleMove) {
            try {
              Thread.sleep(speed);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
        gameOver = isGameOver(isChessLife);
        if (stepByStep) {
          Scanner keyboard = new Scanner(System.in);
          String readString = keyboard.nextLine();
        }
      }
      try {
        Thread.sleep(speed);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void simulateMove(Creature c) {
    Move m = c.setMove(game, c, list);

    invoker.addMove(m);
    invoker.doOne();
    moveCreature(c, c.getPosition().x, c.getPosition().y);
  }

  public boolean isGameOver(boolean chess) {
    if (chess) {
      int king = 0;
      if (list.isEmpty())
        return true;
      for (Creature c : list) {
        if (c instanceof King)
          king++;
      }
      if (king > 1) {
        raison = "a king is dead";
        return false;
      }
    } else {
      if (list.isEmpty())
        return true;
      boolean requinOk = false;
      boolean pinguinsOk = false;
      for (Creature c : list) {
        if (c instanceof HammerheadShark || c instanceof WhiteShark) {
          requinOk = true;
        }
        if (c instanceof Penguin) {
          pinguinsOk = true;
        }
      }
      if (requinOk && pinguinsOk)
        return false;
      if (!requinOk)
        raison = "No more Sharks";
      if (!pinguinsOk)
        raison = "No more Penguin";
    }
    return true;
  }

  public void setWaiting(boolean isWaiting) {
    this.isWaiting = isWaiting;
  }

}
