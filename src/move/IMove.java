package move;

public interface IMove {
  
  public void execute();

  public void undoMove();
  
  public boolean isValidPoint(int pointCoord);

}
