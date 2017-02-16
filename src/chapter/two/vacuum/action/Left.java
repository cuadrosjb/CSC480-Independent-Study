package chapter.two.vacuum.action;

public class Left implements Move {
	private int x;
	private int y;
	public Left(){
		x = -1;
		y = 0;
	}
	
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

}
