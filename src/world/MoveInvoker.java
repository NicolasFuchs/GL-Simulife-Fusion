package world;

import java.util.Stack;

import move.IMove;
import move.MovePenguin;

public class MoveInvoker {

  private Stack<IMove> todo = new Stack<>();
  private Stack<IMove> done = new Stack<>();

  public void addMove(IMove move) {
    todo.push(move);
  }

  public void deleteMove(IMove move) {
    todo.remove(move);
  }

  public void doAll() {
    IMove move = null;
    while (!todo.empty()) {
      move = todo.pop();
      move.execute();
      done.push(move);
    }
    MovePenguin.isPlayed=!MovePenguin.isPlayed;
  }
  
  public void doOne() {
	  IMove move=todo.pop();
	  move.execute();
	  done.push(move);
  }

  public void undoAll() {
    IMove move = null;
    while (!done.empty()) {
      move = done.pop();
      move.undoMove();
    }
  }

  public void undo() {
    IMove move = done.pop();
    if (move != null)
      move.undoMove();
  }

}
