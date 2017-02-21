package Characters;

public abstract class Enemy {
	int xPos;
	int yPos;
	String type;
	String subType;
	public abstract void move();
	public abstract void attack();
	public abstract String type();
}
