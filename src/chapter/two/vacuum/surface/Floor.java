package chapter.two.vacuum.surface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Jeffrey Cuadros
 * 
 * Floor Class that creates a list of list of tiles which creates a 4 x 4 surface.
 *
 */
public class Floor {

	List<List<Tile>> floor;

	public Floor() {
		floor = new ArrayList<List<Tile>>();
		createFloor();
	}

	public void createFloor() {
		List<Tile> row;
		for (int j = 0; j < 4; j++) {
			row = new ArrayList<Tile>();
			for (int i = 0; i < 4; i++) {
				if (placeDirt() == 2) {
					row.add(new Tile(false));
				} else {
					row.add(new Tile(true));
				}
			}
			floor.add(row);
		}
	}

	public int placeDirt() {
		Random random = new Random();
		if (random.nextBoolean()) {
			return 1;
		} else {
			return 2;
		}
	}

	public List<List<Tile>> getFloor() {
		return floor;
	}

	public boolean isTheFloorClean() {
		for (List<Tile> lst : floor) {
			for (Tile t : lst) {
				if (t.isClean() == false)
					return false;
			}
		}
		return true;
	}

	public void cleanFloor() {
		for (List<Tile> lst : floor) {
			for (Tile t : lst) {
				t.setClean(true);
			}
		}
	}

	public static void main(String[] args) {
		Floor f = new Floor();

		for (List<Tile> lst : f.getFloor()) {
			for (Tile t : lst) {
				System.out.println(t.isClean());
			}
			System.out.println("\r");
		}

	}

	public void printFloorState() {
		System.out.println("--------------------------------");

		String rowLn = "";
		for (List<Tile> row : floor) {
			for (Tile tile : row) {
				if (tile.isClean()) {
					rowLn += "[ ]";
				} else {
					rowLn += "[X]";
				}
			}
			System.out.println(rowLn + "\r");
			rowLn = "";

		}
	}

}