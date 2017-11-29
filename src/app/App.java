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
    
    
    while (game_not_finished) {
      int[] moveOrca = myWorld.calcMove(orca);
      myWorld.moveCreature(orca, moveOrca[0], moveOrca[1]);
      myWorld.updateView();
      waitUpdate();

      for (int i = 0; i < iceList.size(); i++) {
        int[] move_ice = new int[2];
        if (random.nextBoolean()) {
          move_ice = myWorld.addIce(iceList.get(i));
          Ice ice = new Ice(new Point(move_ice[0], move_ice[1]));
          iceList.add(ice);
          if (move_ice != null)
            myWorld.summonCreature(ice, move_ice[0], move_ice[1]);
          myWorld.updateView();
          waitUpdate();
        } else {
          move_ice = myWorld.removeIce(iceList.get(i));
          if (move_ice == null) {
            iceList.remove(i);
          } else {
            myWorld.moveCreature(iceList.get(i), move_ice[0], move_ice[1]);
            myWorld.updateView();
            waitUpdate();
          }
        }

      }

    }

  }

  private static void waitUpdate() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
