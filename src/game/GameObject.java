package game;

import com.jogamp.opengl.GL2;

public class GameObject implements ColisionProperty, GameConfiguration{
	
	public float x, y, z;	
	public GL2 gl;
			
	public void draw(GL2 gl) {
		this.gl = gl;
	}

	@Override
	public float[][] get2DSquare() {
		return null;
	}
}
