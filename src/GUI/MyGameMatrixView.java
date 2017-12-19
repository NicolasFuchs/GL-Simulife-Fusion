package GUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D.Float;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ch.eiafr.gl.simulife.gui.game.IGameView;
import ch.eiafr.gl.simulife.model.AWorld;

public class MyGameMatrixView implements IGameView, KeyListener {
  private JFrame                     m_Frame     = new JFrame(
      "Simulife - GrameMatrix View");
  private MyGameMatrixView.GridPanel pan;
  private boolean                    keyReleased = false;

  public MyGameMatrixView(int nbCols, int nbRows) {
    this.m_Frame.addKeyListener(this);
    this.m_Frame.setSize(800, 800);
    this.pan = new MyGameMatrixView.GridPanel(nbCols, nbRows);
    this.m_Frame.getContentPane().add(this.pan);
    this.m_Frame.setDefaultCloseOperation(3);
    this.m_Frame.setVisible(true);
  }

  public void drawNextStep(AWorld world) {
    this.pan.setWorld(world);
    this.pan.repaint();
  }

  class GridPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final int         PADDING          = 0;
    private final int         nbrOfRows;
    private final int         nbrOfCols;
    private AWorld            world            = null;

    public GridPanel(int nbCols, int nbRows) {
      this.nbrOfRows = nbRows;
      this.nbrOfCols = nbCols;
    }

    private void draw(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      float squareWidth = (float) (this.getWidth() - (this.nbrOfCols + 1) * 0)
          / (float) this.nbrOfCols;
      float squareHeight = (float) (this.getHeight() - (this.nbrOfRows + 1) * 0)
          / (float) this.nbrOfRows;
      if (this.world != null) {
        for (int r = 0; r < this.nbrOfRows; ++r) {
          for (int c = 0; c < this.nbrOfCols; ++c) {
            g2d.setColor(
                this.world.getColorFor(this.world.getCreatureAt(c, r)));
            g2d.fill(new Float(0.0F + (float) c * (squareWidth + 0.0F),
                0.0F + (float) r * (squareHeight + 0.0F), squareWidth,
                squareHeight));
          }
        }

      }
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      this.draw(g);
    }

    public void setWorld(AWorld world) {
      this.world = world;
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
    keyReleased = true;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  public boolean isKeyReleased() {
    return keyReleased;
  }

  public void setKeyReleased(boolean keyReleased) {
    this.keyReleased = keyReleased;
  }

}
