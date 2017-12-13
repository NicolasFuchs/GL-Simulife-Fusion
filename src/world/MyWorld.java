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

  private int                      nbCols      = 8;
  private int                      nbRows      = 8;
  private static final int         NumberOfIce = 10;

	private boolean isChessMode;
	private Creature[][] game;
	private boolean gameOver = false;

	private Random rd;
	private LinkedList<Creature> list;
	private static Orca orca;
	private static WhiteShark[] whiteSharks;
	private static HammerheadShark[] hammerheadSharks;
	private static Penguin[] penguins;
	private static ArrayList<Ice> iceList;
	private static boolean game_not_finished;
	private static int loop_id;

	private PieceFactory pieceFactory;
	private MoveInvoker invoker;
	private AbstractCreator liveCreator;
	private AbstractCreator deadCreator;

	public MyWorld(int nbCols, int nbRows, boolean isChessMode) {
		rd = new Random();
		list = new LinkedList<>();
		pieceFactory = new PieceFactory();
		invoker = new MoveInvoker();

    this.nbCols = nbCols;
    this.nbRows = nbRows;
    this.isChessMode = isChessMode;
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

  /*
   * public void summonCreature(LinkedList<Creature> list) { int row, col; while
   * (!list.isEmpty()) { Creature creature = list.poll(); do { row =
   * rd.nextInt(game.length); col = rd.nextInt(game.length); } while
   * (game[row][col] != null); game[row][col] = creature;
   * 
   * if (creature instanceof Orca) { Orca orc = (Orca) creature;
   * orc.setPosition(row, col); ; } if (creature instanceof Penguin) { Penguin p
   * = (Penguin) creature; p.setPosition(row, col); } if (creature instanceof
   * WhiteShark) { WhiteShark w = (WhiteShark) creature; w.setPosition(row,
   * col); } if (creature instanceof HammerheadShark) { HammerheadShark h =
   * (HammerheadShark) creature; h.setPosition(row, col); } if (creature
   * instanceof Ice) ((Ice) creature).setPosition(row, col); }
   * 
   * }
   */
  public void summonCreature(Ice ice, int row, int col) {
    ice.setPosition(row, col);
    game[row][col] = ice;
  }

			Orca orca = (Orca) liveCreator.createCreature(CreatureType.ORCA, new Point(2, 2));
			list.add(orca);

			for (int i = 0; i < NumberOfIce; i++) {
				Ice ice = (Ice) deadCreator.createCreature(CreatureType.ICE,
						new Point(rd.nextInt(game.length), rd.nextInt(game.length)));
				list.add(ice);
			}
			WhiteShark white = (WhiteShark) liveCreator.createCreature(CreatureType.WHITESHARK,
					new Point(rd.nextInt(game.length), rd.nextInt(game.length)));
			list.add(white);
			HammerheadShark hammer = (HammerheadShark) liveCreator.createCreature(CreatureType.HAMMERSHARK,
					new Point(rd.nextInt(game.length), rd.nextInt(game.length)));
			list.add(hammer);
			Penguin peng = (Penguin) liveCreator.createCreature(CreatureType.PENGUIN,
					new Point(rd.nextInt(game.length), rd.nextInt(game.length)));
			list.add(peng);
		}

		startSimulation(isChessLife);
	}

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
      list.add(peng);
    }
    for (Creature c : list) {
      moveCreature(c, c.getPosition().x, c.getPosition().y);
    }
    updateView();
	private void reloadGame() {
		for (Creature c : list) {
			// System.out.println("MyWorld-reload: " +c.getClass()+ " (" +
			// c.getPosition().x +"/"+c.getPosition().y+")");
			moveCreature(c, c.getPosition().x, c.getPosition().y);

		}
	}

	private void simulateMove() {
		for (Creature c : list) {
			Move m = c.setMove(game, c);
			invoker.addMove(m);
			invoker.doOne();
			reloadGame();
		}
  private void reloadGame() {
    for (Creature c : list) {
      // System.out.println("MyWorld-reload: " +c.getClass()+ " (" +
      // c.getPosition().x +"/"+c.getPosition().y+")");
      moveCreature(c, c.getPosition().x, c.getPosition().y);
    }
  }

  private void simulateMove() {
    for (int i = 0; i < list.size(); i++) {
      Creature c = list.get(i);
      Move m = c.setMove(game, c, list);
      invoker.addMove(m);
      invoker.doOne();
      reloadGame();
    }
  }

  public boolean isGameOver() {
    // do all tests
    return false;
  }
}
