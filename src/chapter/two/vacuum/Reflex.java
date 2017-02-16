package chapter.two.vacuum;

import java.util.Stack;

import chapter.two.vacuum.action.Down;
import chapter.two.vacuum.action.Left;
import chapter.two.vacuum.action.Move;
import chapter.two.vacuum.action.Right;
import chapter.two.vacuum.action.Up;
import chapter.two.vacuum.surface.Tile;

/**
 * @author Jeffrey Cuadros
 * 
 * Reflex agent that decides it next move from its current location
 *
 */
public class Reflex implements Agent {

	private boolean bumped;
	Stack<Move> listMoves;
	// private boolean[][] env;

	private int x;
	private int y;

	public void suck(Tile t) {
		if (!t.isClean()) {
			t.setClean(true);
		}
	}

	public Reflex() {
		getPosition();
		listMoves = new Stack<Move>();
		// env = new boolean[4][4];
	}

	public void move() {

		if (listMoves.isEmpty()) {
			new Right();
			listMoves.add(new Right());
		} else if (bumped) {

			changeDirection();
			bumped = false;

		} else {
			nextMove();
		}
	}

	public void nextMove() {
		Move move = listMoves.peek();
		y += move.getY();
		x += move.getX();
		listMoves.add(listMoves.peek());
	}

	public void changeDirection() {
		Move move;

		if (listMoves.peek() instanceof Right) {
			listMoves.pop();
			move = new Left();
			y += move.getY();
			x += move.getX();
			move = new Down();
			y += move.getY();
			x += move.getX();
			listMoves.add(new Down());
		} else if (listMoves.peek() instanceof Left) {
			listMoves.pop();
			move = new Right();
			y += move.getY();
			x += move.getX();
			move = new Up();
			y += move.getY();
			x += move.getX();
			listMoves.add(new Up());
		} else if (listMoves.peek() instanceof Down) {
			listMoves.pop();
			move = new Up();
			y += move.getY();
			x += move.getX();
			move = new Left();
			y += move.getY();
			x += move.getX();
			listMoves.add(new Left());
		} else if (listMoves.peek() instanceof Up) {
			listMoves.pop();
			move = new Down();
			y += move.getY();
			x += move.getX();
			move = new Right();
			y += move.getY();
			x += move.getX();
			listMoves.add(new Right());
		}
	}

	public boolean isBumped() {
		return bumped;
	}

	public void setBumped(boolean bumped) {
		this.bumped = bumped;
	}

	public void getPosition() {
		int x = (int) (Math.random() * 4);
		int y = (int) (Math.random() * 4);

		if (x == y) {
			y = (int) (Math.random() * 4);
		}

		System.out.println("x: " + x + " y: " + y);
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public static void main(String[] args) {

	}

}
