package world;

import java.awt.Color;
import java.time.chrono.IsoChronology;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;

import ch.eiafr.gl.simulife.model.AWorld;
import ch.eiafr.gl.simulife.model.ICreature;
import creature.*;

public class MyWorld extends AWorld {

  private int             nbCols = 6;
  private int             nbRows = 6;
  private AbstractCreator liveCreator;
  private AbstractCreator deadCreator;
  private boolean         isChessMode;

  private ICreature[][]   game;
  private Random          rd;

  private PieceFactory    pieceFactory;
  private MoveInvoker     invoker;

  public MyWorld(int nbCols, int nbRows, boolean isChessMode) {
    rd = new Random();
    this.nbCols = nbCols;
    this.nbRows = nbRows;
    this.isChessMode = isChessMode;
    this.game = new ICreature[nbCols][nbRows];
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
    if (creature instanceof Orca)
      return ((Orca) creature).id;
    if (creature instanceof Penguin)
      return ((Penguin) creature).id;
    if (creature instanceof WhiteShark)
      return ((WhiteShark) creature).id;
    if (creature instanceof HammerheadShark)
      return ((HammerheadShark) creature).id;
    if (creature instanceof Ice)
      return ((Ice) creature).id;
    if (creature instanceof Bishop)
      return ((Bishop) creature).id;
    if (creature instanceof King)
      return ((King) creature).id;
    if (creature instanceof Knight)
      return ((Knight) creature).id;
    if (creature instanceof Pawn)
      return ((Pawn) creature).id;
    if (creature instanceof Queen)
      return ((Queen) creature).id;
    if (creature instanceof Rook)
      return ((Rook) creature).id;

    return "_";
  }

  @Override
  public ImageIcon getIconeFor(ICreature creature) {

    String path = "empty.gif";
    if (creature instanceof Orca)
      path = "resources/orca.gif";
    if (creature instanceof Penguin)
      path = "resources/pingouin.gif";
    if (creature instanceof WhiteShark)
      path = "resources/shark.gif";
    if (creature instanceof HammerheadShark)
      path = "resources/sharkHammer.gif";
    if (creature instanceof Ice)
      path = "resources/ice.gif";
    
    
    if (creature instanceof Bishop)
      if (((Bishop) creature).isBlack())
        path = "resources/bishop_B.png";
      else
        path = "resources/bishop_W.png";
    if (creature instanceof King)
      if (((King) creature).isBlack())
        path = "resources/king_B.png";
      else
        path = "resources/king_W.png";
    if (creature instanceof Knight)
      if (((Knight) creature).isBlack())
        path = "resources/knight_B.png";
      else
        path = "resources/knight_W.png";
    if (creature instanceof Pawn)
      if (((Pawn) creature).isBlack())
        path = "resources/pawn_B.png";
      else
        path = "resources/pawn_W.png";
    if (creature instanceof Queen)
      if (((Queen) creature).isBlack())
        path = "resources/queen_B.png";
      else
        path = "resources/queen_W.png";
    if (creature instanceof Rook)
      if (((Rook) creature).isBlack())
        path = "resources/rook_B.png";
      else
        path = "resources/rook_W.png";

    return new ImageIcon(ClassLoader.getSystemResource(path));
  }

  public void moveCreature(ICreature creature, int newCol, int newRow) {
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
    if (creature instanceof Ice)
      return Color.BLUE;
    if (creature instanceof WhiteShark)
      return Color.GRAY;
    if (creature instanceof HammerheadShark)
      return Color.DARK_GRAY;
    if (creature instanceof Orca)
      return Color.CYAN;
    if (creature instanceof Penguin)
      return Color.BLACK;
    if (creature instanceof Bishop)
      return Color.GREEN;
    if (creature instanceof King)
      return Color.MAGENTA;
    if (creature instanceof Knight)
      return Color.RED;
    if (creature instanceof Pawn)
      return Color.ORANGE;
    if (creature instanceof Queen)
      return Color.PINK;
    if (creature instanceof Rook)
      return Color.YELLOW;

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

  public int[] addIce(Ice ice) {
    int[] iceChange = ice.addIce(game);
    return iceChange;
  }

  public int[] removeIce(Ice ice) {
    int[] iceChange = ice.removeIce(game);
    return iceChange;
  }

  public void summonCreature(LinkedList<ICreature> list) {
    int row, col;
    while (!list.isEmpty()) {
      ICreature creature = list.poll();
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

  public void summonCreature(Ice ice, int row, int col) {
    ice.setPosition(row, col);
    game[row][col] = ice;
    // TODO Auto-generated method stub

  }

}
