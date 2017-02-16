package chapter.two.vacuum;

import java.util.ArrayList;
import java.util.List;
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
 * Reflex agent that maintains the state of all the visited tiles and takes decisions with
 * the information collected
 *
 */
public class ReflexState implements Agent {

	private boolean bumped;
	Stack<Move> listMoves;
	private Memory mem;

	private int x;
	private int y;

	public void suck(Tile t) {
		if (!t.isClean()) {
			t.setClean(true);
		}
	}

	public Memory getMem() {
		return mem;
	}

	public void setMem(Memory mem) {
		this.mem = mem;
	}

	public ReflexState() {
		getPosition();
		listMoves = new Stack<Move>();
		mem = new Memory();
		mem.getMemory().get(y).get(x).setVisited(true);
	}

	public void move() {

		if (listMoves.isEmpty()) {
			new Right();
			listMoves.add(new Right());
			try {
				mem.getMemory().get(y).get(x).setVisited(true);
			} catch (Exception e) {

			}
		} else if (bumped) {

			changeDirection();
			bumped = false;

		} else {
			nextMove();
		}

		System.out.println("x: " + x + " y: " + y);
	}

	public void nextMove() {
		Move move = listMoves.peek();
		y += move.getY();
		x += move.getX();
		// listMoves.add(listMoves.peek());
		try {

			if (mem.getMemory().get(y).get(x).isVisited()) {
				y -= move.getY();
				x -= move.getX();

				if (move instanceof Right) {
					listPossibleMoves(new Down(), new Left(), new Up());
				} else if (move instanceof Left) {
					listPossibleMoves(new Up(), new Right(), new Down());
				} else if (move instanceof Down) {
					listPossibleMoves(new Left(), new Up(), new Right());
				} else if (move instanceof Up) {
					listPossibleMoves(new Right(), new Down(), new Left());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void changeDirection() {
		Move move;

		if (listMoves.peek() instanceof Right) {
			listMoves.pop();
			move = new Left();
			y += move.getY();
			x += move.getX();

			listPossibleMoves(new Down(), new Left(), new Up());

		} else if (listMoves.peek() instanceof Left) {
			listMoves.pop();
			move = new Right();
			y += move.getY();
			x += move.getX();

			listPossibleMoves(new Up(), new Right(), new Down());
		} else if (listMoves.peek() instanceof Down) {
			listMoves.pop();
			move = new Up();
			y += move.getY();
			x += move.getX();

			listPossibleMoves(new Left(), new Up(), new Right());
		} else if (listMoves.peek() instanceof Up) {
			listMoves.pop();
			move = new Down();
			y += move.getY();
			x += move.getX();

			listPossibleMoves(new Right(), new Down(), new Left());
		}
	}

	public void listPossibleMoves(Move one, Move two, Move three) {
		int tmpX = x;
		int tmpY = y;
		List<Move> possibleLocation = new ArrayList<Move>();
		List<Move> refinedLocation = new ArrayList<Move>();
		possibleLocation.add(one);
		possibleLocation.add(two);
		possibleLocation.add(three);
		List<Move> unvistedLocation = new ArrayList<Move>();

		try {
			for (Move m : possibleLocation) {
				try {
					if (mem.getMemory().get(tmpY + m.getY()).get(tmpX + m.getX()).isVisited() == false) {
						unvistedLocation.add(m);
					}
					refinedLocation.add(m);
				} catch (Exception e) {
					System.out.println("x: " + x + " y: " + y);
					System.out.println("Not Adding: " + m.toString());
				}

			}

		} catch (Exception e) {
			System.out.println("x: " + x + " y: " + y);
			System.out.println("2");

		}

		if (unvistedLocation.size() > 0) {
			x += unvistedLocation.get(0).getX();
			y += unvistedLocation.get(0).getY();
			listMoves.add(unvistedLocation.get(0));
			try {
				mem.getMemory().get(y).get(x).setVisited(true);
			} catch (Exception e) {
				System.out.println("x: " + x + " y: " + y);
				System.out.println("3");
			}
		} else {

			x += refinedLocation.get(0).getX();
			y += refinedLocation.get(0).getY();
			listMoves.add(refinedLocation.get(0));
			try {
				mem.getMemory().get(y).get(x).setVisited(true);
			} catch (Exception e) {
				System.out.println("x: " + x + " y: " + y);
				System.out.println("4");
			}
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
