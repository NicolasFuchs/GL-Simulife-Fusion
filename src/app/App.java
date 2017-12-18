package app;

import GUI.MyGameGraphicView;
import ch.eiafr.gl.simulife.gui.UserInterface;
import ch.eiafr.gl.simulife.gui.game.GameMatrixView;
import ch.eiafr.gl.simulife.gui.game.GameTextView;
import world.MyWorld;

import javax.swing.*;

public class App {

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
    boolean stepByStep;
    options[0] = new String("Step by step");
    options[1] = new String("Auto");
    if (JOptionPane.showOptionDialog(frame.getContentPane(), "Choose a mode",
            "Simulif", 0, JOptionPane.QUESTION_MESSAGE, null, options,
            null) == JOptionPane.YES_OPTION) {
      stepByStep = true;
    } else {
      stepByStep = false;
    }

    myWorld = new MyWorld(8, 8, isChessLife, stepByStep);

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
