package chapter.two.vacuum.surface;

/**
 * @author Jeffrey Cuadros
 * 
 * Space where the vacuum is located in the current state
 *
 */
public class Square {
	private boolean visited;
	
	public Square(){
		visited = false;
	}
	
	public Square(boolean visited){
		this.visited = visited;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
