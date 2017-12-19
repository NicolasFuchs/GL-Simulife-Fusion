package GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ch.eiafr.gl.simulife.gui.game.IGameView;
import ch.eiafr.gl.simulife.model.AWorld;
import ch.eiafr.gl.simulife.model.ICreature;

public class MyGameTextView implements IGameView, KeyListener {
  public MyGameTextView() {
    System.out.println("Start game");
  }

  public void drawNextStep(AWorld world) {
    System.out.print("displaying the world\n");

    int row;
    for (row = 0; row < world.getNbRows(); ++row) {
      int col;
      for (col = 0; col < world.getNbCols(); ++col) {
        System.out.print("+---+");
      }

      System.out.println();

      for (col = 0; col < world.getNbCols(); ++col) {
        ICreature creature = world.getCreatureAt(col, row);
        String display = world.getStringFor(creature);
        System.out.print("| " + display + " |");
      }

      System.out.println();
    }

    for (row = 0; row < world.getNbCols(); ++row) {
      System.out.print("+---+");
    }

    System.out.println("\n");
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
    System.out.println("Text released");
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }
}
