package creature;

import java.awt.*;

public class Bishop extends Creature {

  public String id = "B";

  public Bishop(boolean isBlack, Point position) {
    super(isBlack, position);
  }
  
	@Override
	public String getPath() {
		if (isBlack) {
			return "resources/bishop_B.png";
		} else {
			return "resources/bishop_W.png";

		}
	}
	 @Override
		public String getId() {
			return id;
		}
	 
		@Override
		public Color getColor() {
			// TODO Auto-generated method stub
			return Color.GREEN;
		}
  

}
