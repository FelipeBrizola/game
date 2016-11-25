package game;

public interface ColisionProperty {
	public static final int DL = 0;
	public static final int DR = 1;
	public static final int UL = 2;
	public static final int UR = 3;
	
	public static final int X = 0;
	public static final int Z = 1;
	public float[][] get2DSquare();
}
