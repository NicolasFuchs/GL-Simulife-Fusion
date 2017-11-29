package world;

import creature.*;
import java.awt.*;

public class PieceFactory {

    public Creature createPiece(Creature.CreatureType type, boolean isBlack, Point p){
        switch (type){
            case BISHOP:
                return new Bishop(isBlack, p);
            case KING:
                return new King(isBlack, p);
            case KNIGHT:
                return new Knight(isBlack, p);
            case PAWN:
                return new Pawn(isBlack, p);
            case QUEEN:
                return new Queen(isBlack, p);
            case ROOK:
                return new Rook(isBlack, p);
        }
        return null;
    }
}
