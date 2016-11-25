package game;

public class ColisionDetector {
	
	public static final int DL = 0;
	public static final int DR = 1;
	public static final int UL = 2;
	public static final int UR = 3;
	
	public static final int X = 0;
	public static final int Z = 1;
	
	
	

	/**
	 * Check if game object is coliding with a rectangle
	 * */
	public static boolean isColiding(GameObject a, GameObject b){		
		
		float[][] retA = a.get2DSquare();
		float[][] retB = b.get2DSquare();
		
		if( retA == null || retB == null)
			return false;
		
		for( int i = 0; i < 4; i++){
			if( isInside(retA[i], retB) )
				return true;
		}
		
		return false;	
	}

	private static boolean isInside(float[] point, float[][] square) {
		if( point[X] > square[DL][X] && 
				point[X] < square[UR][X] &&
				point[Z] > square[DL][Z] &&
				point[Z] < square[UR][Z])
			return true;
		
		return false;
	}
	
	
}
