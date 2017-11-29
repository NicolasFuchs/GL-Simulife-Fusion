package world;

import java.awt.Point;

import creature.Creature;
import creature.Creature.CreatureType;

public interface AbstractCreator {
  
  public Creature createCreature(CreatureType type, Point p);

}
