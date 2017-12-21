package move;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

import creature.Creature;

public class MovePenguin extends Move {
    public static boolean isPlayed = true;
    Random rd;

    public MovePenguin(Creature[][] game, Creature c, LinkedList<Creature> list) {

        super(game, c,list);
        if (isPlayed) {
            rd = new Random();

            int dx, dy;
            int newX, newY;
            do {
                dx = rd.nextInt(3) - 1;
                dy = rd.nextInt(3) - 1;
                newX = c.getPosition().x + dx;
                newY = c.getPosition().y + dy;

                if (dx == 0 && dy == 0)
                    break;
                Creature p;
                if (isValidPoint(newX) && isValidPoint(newY) && game[newY][newX] != null) {
                    p = (Creature) game[newY][newX];
                    if (Creature.CreatureType.ICE.equals(p))
                        break;
                }

            } while (!isValidPoint(newX) || !isValidPoint(newY) || game[newY][newX] != null);
            setPosition(new Point(newX, newY));
        }
    }
}
