package move;

import ch.eiafr.gl.simulife.model.ICreature;

public interface IMove {
  
  public void execute();

  public void undoMove();

}
