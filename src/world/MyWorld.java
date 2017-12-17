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

public class MyWorld extends AWorld {

  private int                      nbCols      = 8;
  private int                      nbRows      = 8;
  private static final int         NumberOfIce = 3;

  private Creature[][]             game;
  private boolean                  gameOver    = false;

  private Random                   rd;
  private LinkedList<Creature>     list;

  private PieceFactory             pieceFactory;
  private MoveInvoker              invoker;
  private AbstractCreator          liveCreator;
  private AbstractCreator          deadCreator;
  private String                   raison;

  public MyWorld(int nbCols, int nbRows, boolean isChessMode) {
    rd = new Random();
    list = new LinkedList<>();
    pieceFactory = new PieceFactory();
    invoker = new MoveInvoker();
    raison = "";

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
      WhiteShark white = (WhiteShark) liveCreator.createCreature(
          CreatureType.WHITESHARK,
          new Point(rd.nextInt(game.length), rd.nextInt(game.length)));
      list.add(white);
      HammerheadShark hammer = (HammerheadShark) liveCreator.createCreature(
          CreatureType.HAMMERSHARK,
          new Point(rd.nextInt(game.length), rd.nextInt(game.length)));
      list.add(hammer);
      Penguin peng = (Penguin) liveCreator.createCreature(CreatureType.PENGUIN,
          new Point(rd.nextInt(game.length), rd.nextInt(game.length)));
      Penguin peng1 = (Penguin) liveCreator.createCreature(CreatureType.PENGUIN,
              new Point(rd.nextInt(game.length), rd.nextInt(game.length)));
      list.add(peng);
      list.add(peng1);
    }
    for (Creature c : list) {
      moveCreature(c, c.getPosition().x, c.getPosition().y);
    }
    updateView();
    startSimulation(isChessLife);
  }

  private void startSimulation(boolean isChessLife) {
    while (!gameOver) {
      simulateMove();
      updateView();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      gameOver = isGameOver(isChessLife);
    }
    JFrame frame = new JFrame();
    String[] options = new String[2];
    options[0] = new String("Quit");
    options[1] = new String("Restart");
    if (JOptionPane.showOptionDialog(frame.getContentPane(), "Game Over, " + raison + " restart a new simulation ?",
            "Simulif", 0, JOptionPane.QUESTION_MESSAGE, null, options,
            null) == JOptionPane.YES_OPTION) {
      System.exit(0);
    } else {
      App.main(new String[0]);
    }
  }

  private void simulateMove() {
    for (int i = 0; i < list.size(); i++) {
      Creature c = list.get(i);
      Move m = c.setMove(game, c, list);

     invoker.addMove(m);
      invoker.doOne();
      moveCreature(c, c.getPosition().x, c.getPosition().y);
    }
  }

  public boolean isGameOver(boolean chess) {
    if(chess){
      int king = 0;
      if(list.isEmpty()) return true;
      for(Creature c:list){
        if (c instanceof King) king++;
      }
      if(king > 1){
        raison = "a king is dead";
        return false;
      }
    } else {
      if(list.isEmpty()) return true;
      boolean requinOk = false;
      boolean pinguinsOk = false;
      for(Creature c:list){
        if (c instanceof HammerheadShark || c instanceof WhiteShark) {
          requinOk = true;
        }
        if( c instanceof  Penguin) {
          pinguinsOk = true;
        }
      }
      if(requinOk && pinguinsOk) return false;
      if(!requinOk)raison = "No more Sharks";
      if(!pinguinsOk)raison = "No more Penguin";
    }
    return true;
  }
}
