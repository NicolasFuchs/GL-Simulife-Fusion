package creature;

import java.awt.*;

public class King extends Creature {
  
  public String id = "K";
    public King(boolean isBlack, Point position) {
        super(isBlack, position);
    }
    
    @Override
    public Color getColor() {
    	// TODO Auto-generated method stub
    	return Color.MAGENTA;
    }
    
	@Override
	public String getPath() {
		if (isBlack) {
			return "resources/king_B.png";
		} else {
			return "resources/king_W.png";

		}
	}
      
}
