package app;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GUI.MyGameGraphicView;
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
    boolean isChessLife;
    JFrame frame = new JFrame();
    String[] options = new String[2];
    options[0] = new String("Chess");
    options[1] = new String("Waterworld");
    if (JOptionPane.showOptionDialog(frame.getContentPane(), "Choose a game",
        "Simulif", 0, JOptionPane.QUESTION_MESSAGE, null, options,
        null) == JOptionPane.YES_OPTION) {
      isChessLife = true;
    } else {
      isChessLife = false;
    }

    myWorld = new MyWorld(8, 8, isChessLife);

    UserInterface userGameMatrixView = new UserInterface(
        new GameMatrixView(myWorld.getNbCols(), myWorld.getNbRows()));
    myWorld.addObserver(userGameMatrixView);
    
    UserInterface userGraphiqueInterface = new UserInterface(
          new MyGameGraphicView(myWorld.getNbCols(), myWorld.getNbRows(), isChessLife));
    myWorld.addObserver(userGraphiqueInterface);

    UserInterface userTextInterface = new UserInterface(new GameTextView());
    myWorld.addObserver(userTextInterface);

    myWorld.init(isChessLife);
    myWorld.updateView();
  }
}
