package world;

import java.awt.Color;
import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;

import ch.eiafr.gl.simulife.model.AWorld;
import ch.eiafr.gl.simulife.model.ICreature;
import creature.*;
import creature.Creature.CreatureType;
import move.*;

public class MyWorld extends AWorld {

  private int                      nbCols      = 6;
  private int                      nbRows      = 6;
  private static final int         NumberOfIce = 10;

  private boolean                  isChessMode;
  private Creature[][]             game;
  private boolean                  gameOver    = false;

  private Random                   rd;
  private LinkedList<Creature>     list;
  private static Orca              orca;
  private static WhiteShark[]      whiteSharks;
  private static HammerheadShark[] hammerheadSharks;
  private static Penguin[]         penguins;
  private static ArrayList<Ice>    iceList;
  private static boolean           game_not_finished;
  private static int               loop_id;

  private PieceFactory             pieceFactory;
  private MoveInvoker              invoker;
  private AbstractCreator          liveCreator;
  private AbstractCreator          deadCreator;

  public MyWorld(int nbCols, int nbRows, boolean isChessMode) {
    rd = new Random();
    list = new LinkedList<>();
    pieceFactory = new PieceFactory();
    invoker = new MoveInvoker();

    this.nbCols = nbCols;
    this.nbRows = nbRows;
    this.isChessMode = isChessMode;
    this.game = new Creature[nbCols][nbRows];
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
    return game[col][row];
  }

  @Override
  public String getStringFor(ICreature creature) {
	if(creature instanceof Creature)
		return ((Creature) creature).getId();
    return "_";
  }

  @Override
  public ImageIcon getIconeFor(ICreature creature) {

    String path = "empty.gif";
    if(creature instanceof Creature)
    	path=((Creature) creature).getPath();
    

    return new ImageIcon(ClassLoader.getSystemResource(path));
  }

  public void moveCreature(Creature creature, int newCol, int newRow) {
    for (int col = 0; col < getNbCols(); col++) {
      for (int row = 0; row < getNbRows(); row++) {
        if (game[col][row] == creature)
          game[col][row] = null;
      }
    }
    game[newCol][newRow] = creature;
  }

 @Override
  public Color getColorFor(ICreature creature) {
	if(creature instanceof Creature)
		return  ((Creature) creature).getColor();

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

  /*public void summonCreature(LinkedList<Creature> list) {
    int row, col;
    while (!list.isEmpty()) {
      Creature creature = list.poll();
      do {
        row = rd.nextInt(game.length);
        col = rd.nextInt(game.length);
      } while (game[row][col] != null);
      game[row][col] = creature;

      if (creature instanceof Orca) {
        Orca orc = (Orca) creature;
        orc.setPosition(row, col);
        ;
      }
      if (creature instanceof Penguin) {
        Penguin p = (Penguin) creature;
        p.setPosition(row, col);
      }
      if (creature instanceof WhiteShark) {
        WhiteShark w = (WhiteShark) creature;
        w.setPosition(row, col);
      }
      if (creature instanceof HammerheadShark) {
        HammerheadShark h = (HammerheadShark) creature;
        h.setPosition(row, col);
      }
      if (creature instanceof Ice)
        ((Ice) creature).setPosition(row, col);
    }

  }
*/
  public void summonCreature(Ice ice, int row, int col) {
    ice.setPosition(row, col);
    game[row][col] = ice;
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
    } else {
      liveCreator = new LiveCreator();
      deadCreator = new DeadCreator();

      Orca orca = (Orca) liveCreator.createCreature(CreatureType.ORCA,
          new Point(2, 2));
      list.add(orca);

      for (int i = 0; i < NumberOfIce; i++) {
        Ice ice = (Ice) deadCreator.createCreature(CreatureType.ICE,
            new Point(rd.nextInt(game.length), rd.nextInt(game.length)));
        list.add(ice);
      }
      
      list.add(new HammerheadShark(new Point(rd.nextInt(game.length), rd.nextInt(game.length))));
      list.add(new WhiteShark(new Point(rd.nextInt(game.length), rd.nextInt(game.length))));
    }

    startSimulation(isChessLife);
  }

  private void startSimulation(boolean isChessLife) {
    while (!gameOver) {
      createMoves();
      invoker.doAll();
      reloadGame();
      updateView();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      gameOver = isGameOver();
    }
  }


  private void reloadGame() {
    for (Creature c : list) {
      // System.out.println("MyWorld-reload: " +c.getClass()+ " (" +
      // c.getPosition().x +"/"+c.getPosition().y+")");
      moveCreature(c, c.getPosition().x, c.getPosition().y);
    
    }
  }

  private void createMoves() {
    for (Creature c : list) {
      Move m = null;
      
     m= c.setMove(game,c,list);
     invoker.addMove(m);
    }

  }

  public boolean isGameOver() {
    // do all tests
    return false;
  }
}
