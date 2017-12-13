package GUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.eiafr.gl.simulife.gui.game.IGameView;
import ch.eiafr.gl.simulife.model.AWorld;
import ch.eiafr.gl.simulife.model.ICreature;

public class MyGameGraphicView implements IGameView {

  private JFrame     m_Frame = new JFrame("Simulife - GameGraphicView");
  private JPanel     m_ButtonPanel;
  private JPanel[][] m_grid;

  public MyGameGraphicView(int nbCols, int nbRows, boolean isChessLife) {
    this.m_Frame.setDefaultCloseOperation(3);
    this.m_ButtonPanel = new JPanel(new GridLayout(nbRows, nbCols));
    this.m_grid = new JPanel[nbRows][nbCols];

    for (int y = 0; y < nbRows; ++y) {
      for (int x = 0; x < nbCols; ++x) {
        this.m_grid[y][x] = new JPanel(new GridLayout(1, 1));
        if (isChessLife) {
          if ((x + y) % 2 == 0) {
            this.m_grid[y][x].setBackground(Color.LIGHT_GRAY);
          } else {
            this.m_grid[y][x].setBackground(Color.GRAY);
          }
        } else {
          this.m_grid[y][x].setBackground(Color.CYAN);
        }
        this.m_ButtonPanel.add(this.m_grid[y][x]);
      }
    }

    this.m_Frame.add(this.m_ButtonPanel);
    this.m_Frame.setLocation(50, 200);
    // this.m_Frame.setSize(nbCols * 75, nbRows * 95 + 20);
    this.m_Frame.setSize(nbCols * 95, nbRows * 95);
    this.m_Frame.setVisible(true);
  }

  public void drawNextStep(AWorld world) {
    int col;
    int row;
    for (row = 0; row < world.getNbRows(); ++row) {
      for (col = 0; col < world.getNbCols(); ++col) {
        this.m_grid[row][col].removeAll();
        this.m_grid[row][col].repaint();
      }
    }

    for (row = 0; row < world.getNbRows(); ++row) {
      for (col = 0; col < world.getNbCols(); ++col) {
        this.m_grid[row][col].removeAll();
        ICreature creature = world.getCreatureAt(col, row);
        if (creature != null) {
          ImageIcon temp = world.getIconeFor(creature);
          this.m_grid[row][col].add(new JLabel(temp));
          this.m_grid[row][col].validate();
          this.m_grid[row][col].repaint();
        }
      }
    }
    this.m_Frame.repaint();
  }

}
