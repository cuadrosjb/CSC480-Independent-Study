package chapter.two.vacuum.action;

public class Down implements Move{

	private int x;
	private int y;
	public Down(){
		x = 0;
		y = 1;
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
