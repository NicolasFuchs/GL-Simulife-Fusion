package creature;

import java.awt.*;

public class Queen extends Creature {
  public String id = "Q";

  public Queen(boolean isBlack, Point position) {
    super(isBlack, position);
  }
  
  @Override
  public Color getColor() {
  	// TODO Auto-generated method stub
  	return Color.PINK;
  }
    
  
	@Override
	public String getPath() {
		if (isBlack) {
			return "resources/queen_B.png";
		} else {
			return "resources/queen_W.png";

		}
	}
}
