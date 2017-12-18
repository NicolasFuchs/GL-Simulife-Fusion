package app;

import GUI.MyGameGraphicView;
import ch.eiafr.gl.simulife.gui.UserInterface;
import ch.eiafr.gl.simulife.gui.game.GameMatrixView;
import ch.eiafr.gl.simulife.gui.game.GameTextView;
import world.MyWorld;

import java.util.ArrayList;

import javax.swing.*;

public class App {

  private static MyWorld myWorld;

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
        "Simulife", 0, JOptionPane.QUESTION_MESSAGE, null, options,
        null) == JOptionPane.YES_OPTION) {
      stepByStep = true;
    } else {
      stepByStep = false;
    }
    // TODO ADD here a input for col and row
    int[] size = new int[2];
    size[0] = 8;
    size[1] = 8;
    if (!isChessLife)
      size = getGridSize();
    myWorld = new MyWorld(size[0], size[1], isChessLife, stepByStep);

    options[0] = new String("Matrix View");
    options[1] = new String("Graphique View");
    if (JOptionPane.showOptionDialog(frame.getContentPane(), "Choose a view",
        "Simulif", 0, JOptionPane.QUESTION_MESSAGE, null, options,
        null) == JOptionPane.YES_OPTION) {
      UserInterface userGameMatrixView = new UserInterface(
          new GameMatrixView(myWorld.getNbCols(), myWorld.getNbRows()));
      myWorld.addObserver(userGameMatrixView);
    } else {
      UserInterface userGraphiqueInterface = new UserInterface(
          new MyGameGraphicView(myWorld.getNbCols(), myWorld.getNbRows(),
              isChessLife));
      myWorld.addObserver(userGraphiqueInterface);
    }

    UserInterface userTextInterface = new UserInterface(new GameTextView());
    myWorld.addObserver(userTextInterface);

    myWorld.init(isChessLife);
    myWorld.updateView();
  }

  private static int[] getGridSize() {
    Integer[] sizes = new Integer[13];
    for (int i = 0; i < sizes.length; i++) {
      sizes[i] = i + 8;
    }
    
    int[] size = new int[2];
    size[0] = sizes[0];
    size[1] = sizes[0];
    
    JComboBox<Integer> cbX = new JComboBox<>(sizes);
    JComboBox<Integer> cbY = new JComboBox<>(sizes);

    JPanel myPanel = new JPanel();
    myPanel.add(new JLabel("x:"));
    myPanel.add(cbX);
    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
    myPanel.add(new JLabel("y:"));
    myPanel.add(cbY);    
    
    int res = JOptionPane.showConfirmDialog(null, myPanel, "Choose grid size",
        JOptionPane.DEFAULT_OPTION);


    // If a string was returned, say so.
    if (res == JOptionPane.DEFAULT_OPTION) {
      size[0] = (int) cbX.getSelectedItem();
      size[1] = (int) cbY.getSelectedItem();
    }
    return size;
  }
}
