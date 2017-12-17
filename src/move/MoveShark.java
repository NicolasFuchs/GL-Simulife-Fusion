package move;

import creature.Creature;
import creature.HammerheadShark;
import creature.WhiteShark;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class MoveShark extends Move {

	Random rd;

	public MoveShark(Creature[][] game, Creature c, LinkedList<Creature> list) {
		super(game, c);

		HammerheadShark hammer = null;
		WhiteShark white = null;
		if (c.getId() == "W") {
			white = (WhiteShark) c;
		} else {
			hammer = (HammerheadShark) c;
		}
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
			Creature target;
			if (isValidPoint(newX) && isValidPoint(newY) && game[newY][newX] != null) {
				target = game[newY][newX];
				if (target.getId() == "P") {
					list.remove(target);
					if (white != null)
						white.eat();
					if (hammer != null)
						hammer.eat();
					break;
				}

			}

		} while (!isValidPoint(newX) || !isValidPoint(newY) || game[newY][newX] != null);
		setPosition(new Point(newX, newY));
		if (white != null) {
			if (!white.hunger()) {
				game[(int) c.getPosition().getY()][(int) c.getPosition().getX()] = null;
				list.remove(c);
			}
		}

		if (hammer != null) {
			if (!hammer.hunger()) {
				game[newY][newX]=null;
				game[newX][newY]=null;
				game[(int) c.getPosition().getX()][(int) c.getPosition().getY()] = null;
				game[(int) c.getPosition().getY()][(int) c.getPosition().getX()] = null;
				list.remove(c);
			}
		}
	}
}
