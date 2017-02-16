package chapter.two.vacuum;

import java.util.ArrayList;
import java.util.List;

import chapter.two.vacuum.surface.Square;

public class Memory {
	List<List<Square>> mem;

	public Memory() {
		mem = new ArrayList<List<Square>>();
		createMemory();
	}



	public void createMemory() {
		List<Square> row;
		for (int j = 0; j < 4; j++) {
			row = new ArrayList<Square>();
			for (int i = 0; i < 4; i++) {
				row.add(new Square());

			}
			mem.add(row);
		}
	}

	public List<List<Square>> getMemory() {
		return mem;
	}

	
	public void printMemoryState() {
		System.out.println("------------");

		String rowLn = "";
		for (List<Square> row : mem) {
			for (Square square : row) {
				if (square.isVisited()) {
					rowLn += "[ ]";
				} else {
					rowLn += "[X]";
				}
			}
			System.out.println(rowLn + "\r");
			rowLn = "";

		}
	}


	public static void main(String[] args) {
		Memory f = new Memory();

		f.printMemoryState();

	}
}
