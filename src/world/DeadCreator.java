package world;

import java.awt.Point;

import ch.eiafr.gl.simulife.model.ICreature;
import creature.Creature;
import creature.Creature.CreatureType;
import creature.Ice;

public class DeadCreator implements AbstractCreator {

  @Override
  public Creature createCreature(CreatureType type, Point p) {
    switch (type) {
      case ICE:
        return new Ice(p);
    }
    return null;
  }

}
