package world;

import java.awt.Point;

import ch.eiafr.gl.simulife.model.ICreature;
import creature.*;
import creature.Creature.CreatureType;

public class LiveCreator implements AbstractCreator {

  @Override
  public Creature createCreature(CreatureType type, Point p) {
    switch (type) {
      case HAMMERSHARK:
        return new HammerheadShark(p);
      case ORCA:
        return Orca.getInstance(p);
      case PENGUIN:
        return new Penguin(p);
      case WHITESHARK:
        return new WhiteShark(p);
    }
    return null;
  }

}
