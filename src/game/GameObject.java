package game;

import com.jogamp.opengl.GL2;

public class GameObject implements ColisionProperty, GameConfiguration{
	
	public float x, y, z;	
	public GL2 gl;
			
	public void draw(GL2 gl) {
		this.gl = gl;
	}

	public float randomColor() {
			
		float lower = 0;
		float upper = 1;
		float res = 0;
		res = (float) (Math.random() * (upper - lower) + lower);
		return res;
		
	}
	
	@Override
	public float[][] get2DSquare() {
		return null;
	}
}
