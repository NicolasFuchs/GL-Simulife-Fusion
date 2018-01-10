package move;

import creature.Creature;
import creature.HammerheadShark;
import creature.Penguin;
import creature.WhiteShark;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class MoveShark extends Move {

    Random rd;

    public MoveShark(Creature[][] game, Creature c, LinkedList<Creature> list) {
        super(game, c, list);

        HammerheadShark hammer = null;
        WhiteShark white = null;
        if (c instanceof WhiteShark) {
            white = (WhiteShark) c;
        } else {
            hammer = (HammerheadShark) c;
        }
        rd = new Random();

        int dx, dy;
        //int newX, newY;
        int newX = c.getPosition().x;
        int newY = c.getPosition().y;
        do {
            
            
            boolean foundPenguin = false;
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    newX = c.getPosition().x + i;
                    newY = c.getPosition().y + j;
                    if ((i != 0 || j != 0) && isValidPoint(newX) && isValidPoint(newY) && game[newY][newX] instanceof Penguin) {
                        foundPenguin = true;
                        break;
                    }
                }
                if (foundPenguin) break;
            }
            if (foundPenguin) {
                list.remove(game[newY][newX]);
                if (white != null) white.eat();
                else if (hammer != null) hammer.eat();
                break;
            }
            if (isValidPoint(c.getPosition().x-2)) {
                foundPenguin = true;
                newX = c.getPosition().x-2;
                newY = c.getPosition().y;
            } else if (isValidPoint(c.getPosition().x+2)) {
                foundPenguin = true;
                newX = c.getPosition().x+2;
                newY = c.getPosition().y;
            } else if (isValidPoint(c.getPosition().y-2)) {
                foundPenguin = true;
                newX = c.getPosition().x;
                newY = c.getPosition().y-2;
            } else if (isValidPoint(c.getPosition().y+2)) {
                foundPenguin = true;
                newX = c.getPosition().x;
                newY = c.getPosition().y+2;
            }
      

            
            dx = rd.nextInt(3) - 1;
            dy = rd.nextInt(3) - 1;
            newX = c.getPosition().x + dx;
            newY = c.getPosition().y + dy;

            /*if (dx == 0 && dy == 0) break;
            Creature target;
            if (isValidPoint(newX) && isValidPoint(newY) && game[newY][newX] != null) {
                target = game[newY][newX];
                if (target instanceof Penguin) {
                    list.remove(target);
                    if (white != null)
                        white.eat();
                    if (hammer != null)
                        hammer.eat();
                    break;
                }

            }*/

        } while (!isValidPoint(newX) || !isValidPoint(newY) || game[newY][newX] != null);

        if (white != null) {
            if (!white.hunger()) {
                game[c.getPosition().y][c.getPosition().x] = null;
                list.remove(c);
            } else {
                setPosition(new Point(newX, newY));

            }
        }

        if (hammer != null) {
            if (!hammer.hunger()) {
                game[c.getPosition().y][c.getPosition().x] = null;
                list.remove(c);
            } else {
                setPosition(new Point(newX, newY));

            }
        }
    }
}