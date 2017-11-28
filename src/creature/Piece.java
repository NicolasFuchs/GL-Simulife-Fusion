package creature;

import java.awt.Point;

import ch.eiafr.gl.simulife.model.ICreature;
import move.Move;
import world.PieceFactory;

public class Piece extends PieceFactory implements ICreature {
    boolean isBlack;
    Point position;
    Move[] moves;

    public enum PieceType {
        BISHOP, KING, KNIGHT, PAWN, QUEEN, ROOK
    }

    public Piece(boolean isBlack, Point position) {
        this.isBlack = isBlack;
        this.position = position;
    }

    public void move(int dx, int dy) {
        if(position.x == 7 && dx > 0)
            dx = -dx;
        if(position.x == 0 && dx < 0)
            dx = -dx;
        if(position.y == 7 && dy > 0)
            dy = -dy;
        if(position.y == 0 && dy < 0)
            dy = -dy;
        position.x += dx;
        position.y += dy;
    }

    public Point getPosition(){
        return position;
    }

    public boolean isBlack(){
        return isBlack;
    }
}
