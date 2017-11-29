package app;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import ch.eiafr.gl.simulife.gui.UserInterface;
import ch.eiafr.gl.simulife.gui.game.GameGraphicView;
import ch.eiafr.gl.simulife.gui.game.GameMatrixView;
import ch.eiafr.gl.simulife.gui.game.GameTextView;
import ch.eiafr.gl.simulife.model.ICreature;
import creature.*;
import world.MyWorld;

public class App {

  private static final int         NumberOfIce = 10;
  private static Orca              orca;
  private static WhiteShark[]      whiteSharks;
  private static HammerheadShark[] hammerheadSharks;
  private static Penguin[]         penguins;
  private static ArrayList<Ice>    iceList;
  private static boolean           game_not_finished;
  private static int               loop_id;
  private static Random            random;
  private static MyWorld           myWorld;

  public static void main(String[] args) {
    boolean isChessLife = false;

    if (args.length > 0) {
      String arg = args[0];
      if (arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("false"))
        isChessLife = Boolean.valueOf(arg);
    }
    
    myWorld = new MyWorld(8, 8, isChessLife);

    UserInterface userGraphiqueInterface = new UserInterface(
        new GameGraphicView(myWorld.getNbCols(), myWorld.getNbRows()));
    myWorld.addObserver(userGraphiqueInterface);

    UserInterface userGameMatrixView = new UserInterface(
        new GameMatrixView(myWorld.getNbCols(), myWorld.getNbRows()));
    myWorld.addObserver(userGameMatrixView);

    UserInterface userTextInterface = new UserInterface(new GameTextView());
    myWorld.addObserver(userTextInterface);

    myWorld.init(isChessLife);
    myWorld.updateView();
  }
}
