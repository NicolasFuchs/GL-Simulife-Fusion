package move;

import ch.eiafr.gl.simulife.model.ICreature;

public class Move implements IMove {
  
  int dx;
  int dy;
  protected ICreature creature;
  
  public Move(ICreature c, int dx, int dy) {
    creature = c;
    this.dx = dx;
    this.dy = dy;
}
  @Override
  public void execute() {
    //creature.move(dx,dy);
  }
  
  @Override
  public void undoMove() {
    // TODO Auto-generated method stub
  }

}
