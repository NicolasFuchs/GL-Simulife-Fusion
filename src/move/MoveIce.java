package move;

import java.awt.Point;
import java.util.Random;

import ch.eiafr.gl.simulife.model.ICreature;
import creature.Creature;

public class MoveIce extends Move {

  Random rd;
  Creature c;
  private boolean allpositioncheck = false;

  
  public MoveIce(Creature[][] game, Creature c) {
    super(game, c);
    this.c = c;
    rd = new Random();
    int percent = rd.nextInt(10);
    
    if(percent < 3){
      removeIce(game);
    }else{
      addIce(game);
    }
  }

  public void addIce(ICreature[][] game) {
    int pourc = rd.nextInt(10);
    int row = c.getPosition().x;
    int col = c.getPosition().y;
    if (pourc < 4) {
      int pos = 1;
      int tmpRow = row, tmpCol = col;
      while (game[tmpRow][tmpCol] != null && !allpositioncheck) {
        switch (pos) {

          case 1:
            tmpRow = row - 1;
            tmpCol = col;
            pos++;
            break;

          case 2:
            tmpRow = row;
            tmpCol = col - 1;
            pos++;
            break;
          case 3:
            tmpRow = row;
            tmpCol = col + 1;
            pos++;
            break;

          case 4:
            tmpRow = row + 1;
            tmpCol = col;
            pos = 1;
            allpositioncheck = true;
            break;

          default:
            break;
        }
        if (tmpCol < 0 || tmpCol == game.length || tmpRow == game.length
            || tmpRow < 0) {
          tmpCol = col;
          tmpRow = row;
          if (pos < 4)
            pos++;
          else
            pos = 1;
        }
      }
      if (allpositioncheck) {
        allpositioncheck = false;
      }
      
      setPosition(new Point(tmpRow, tmpCol));
    } else {
      setPosition(new Point(row, col));
    }
  }

  public void removeIce(ICreature[][] game) {
    int pourc = rd.nextInt(10);
    if (pourc < 4) {
      game[c.getPosition().x][c.getPosition().y] = null;
    } else {
      setPosition(new Point(c.getPosition().x, c.getPosition().y));
    }
  }
}
