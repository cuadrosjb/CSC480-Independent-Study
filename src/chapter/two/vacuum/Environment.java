package chapter.two.vacuum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import chapter.two.vacuum.surface.Floor;
import chapter.two.vacuum.surface.Tile;
import independent.jdbc.mysql.MySQLConnection;



/**
 * @author Jeffrey Cuadros
 * 
 * Environment Class that links the floor, and agent. The main function calls the 
 * cleanRoom() function to clean the floor, and records the result into the database.
 * 
 *
 */
public class Environment {

	/**
	 * Main method where all the calls to the collection Agent classes are made and the call to 
	 * the database to save the results of the moves made 
	 * 
	 * @param args There no need for arguments
	 */
	public static void main(String[] args) {
		
		Connection conn = new MySQLConnection().getConnection();
		PreparedStatement ps;
		
		String  sqlQuery = "Insert into ai_vacuum_cleaner(type, moves) VALUES ('Reflex', ?) ";
		
		try {
			 ps = conn.prepareStatement(sqlQuery);
//			int adding = 0; 
			int trials = 1000;

			for(int i = 0 ; i < trials ; i++){
				ps.setInt(1, cleanRoom());
//				adding += cleanRoom();
				ps.executeUpdate();
			}
			
//			System.out.println("AVG moves : " + adding/trials);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

	
	/**
	 * 
	 * Helps to have a clean interphase in the main functions by encapsulation all 
	 * in one function call
	 * 
	 * 
	 * @return counter Number of movements committed by the agent to accomplish its goal
	 */
	public static int cleanRoom() {
		Floor floor = new Floor();

		Reflex agentReflex = new Reflex();
//		ReflexState agentReflex = new ReflexState();

		// printFloorState(floor);

		int counter = 0;

		while (!floor.isTheFloorClean()) {

			try {
				if (floor.getFloor().get(agentReflex.getY()).get(agentReflex.getX()).isClean() == false) {
					agentReflex.suck(floor.getFloor().get(agentReflex.getY()).get(agentReflex.getX()));
				}else{
					System.out.println("NoOP");
				}

			} catch (Exception e) {
				agentReflex.setBumped(true);

			}
			
			if(counter == 1000) break;

			counter++;
			agentReflex.move();
			

		}
		System.out.println("counter: " + counter);

		return counter;
	}

	/**
	 * 
	 * Prints to the console the current of state of the Floor object.
	 * 
	 * 
	 * @param floor
	 */
	public static void printFloorState(Floor floor) {
		System.out.println("--------------------------------");

		String rowLn = "";
		for (List<Tile> row : floor.getFloor()) {
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
