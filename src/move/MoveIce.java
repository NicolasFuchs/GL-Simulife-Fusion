package move;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

import ch.eiafr.gl.simulife.model.ICreature;
import creature.Creature;
import creature.Ice;
import creature.Creature.CreatureType;
import world.AbstractCreator;
import world.DeadCreator;

public class MoveIce extends Move {

    Random rd;
    Creature c;
    private boolean allpositioncheck = false;
    private AbstractCreator deadCreator;
    private LinkedList<Creature> list;

    public MoveIce(Creature[][] game, Creature c, LinkedList<Creature> list) {
        super(game, c, list);
        this.c = c;
        this.list = list;
        deadCreator = new DeadCreator();
        rd = new Random();
        int percent = rd.nextInt(10);

        if (percent < 3) {
            removeIce(game);
        } else {
            addIce(game);
        }
    }

    public void addIce(ICreature[][] game) {
        int pourc = rd.nextInt(10);
        int row = c.getPosition().y;
        int col = c.getPosition().x;
        if (pourc < 4) {
            int pos = 1;
            int tmpRow = row, tmpCol = col;
            do {
                switch (pos) {

                case 1:
                    tmpRow = row - 1;
                    tmpCol = col;
                    pos++;
                    break;

                case 2:
                    tmpRow = row;
                    tmpCol = col - 1;
                    pos++;
                    break;
                case 3:
                    tmpRow = row;
                    tmpCol = col + 1;
                    pos++;
                    break;

                case 4:
                    tmpRow = row + 1;
                    tmpCol = col;
                    pos = 1;
                    allpositioncheck = true;
                    break;

                default:
                    break;
                }
                if (tmpCol < 0 || tmpCol == game[0].length || tmpRow == game.length || tmpRow < 0) {
                    tmpCol = col;
                    tmpRow = row;
                    if (pos < 4)
                        pos++;
                    else
                        pos = 1;
                }
                Creature p;

            } while (game[tmpRow][tmpCol] != null && !allpositioncheck);
            if (allpositioncheck) {
                allpositioncheck = false;
            }

            // add ice to list
            Ice ice = (Ice) deadCreator.createCreature(CreatureType.ICE, new Point(tmpCol, tmpRow));
            list.add(ice);

        }
    }

    public void removeIce(ICreature[][] game) {
        int pourc = rd.nextInt(10);
        if (pourc < 6) {
            game[c.getPosition().y][c.getPosition().x] = null;

            // remove ice to list
            list.remove(c);

        }
    }
}
