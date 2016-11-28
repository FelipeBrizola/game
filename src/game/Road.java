package game;


import game.GameObject;

import com.jogamp.opengl.GL2;

public class Road extends GameObject{
		
	public Road(){
		y = 1;
	}

	@Override
	public void draw(GL2 gl) {
		this.gl = gl;
		
		// pista		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(0f, 0f, 0f);
		gl.glVertex3f( x - (WIDTH_ROAD_SIZE/2), y , z + ROAD_SIZE);
		gl.glVertex3f( x - (WIDTH_ROAD_SIZE/2), y , z);
		gl.glVertex3f( x + (WIDTH_ROAD_SIZE/2), y , z);
		gl.glVertex3f( x + (WIDTH_ROAD_SIZE/2), y , z + ROAD_SIZE);

		// listras da pista		
		gl.glColor3f(1f, 1f, 1f);
		for( int i = 0; i < ROAD_SIZE / DETAIL_SIZE; i+=2){			
			
			gl.glVertex3f(-50+ x - (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE + DETAIL_SIZE);
			gl.glVertex3f(-50+ x - (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE );
			gl.glVertex3f(-50+ x + (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE );
			gl.glVertex3f(-50+ x + (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE + DETAIL_SIZE);
			
			gl.glVertex3f(50+ x - (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE + DETAIL_SIZE);
			gl.glVertex3f(50+ x - (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE );
			gl.glVertex3f(50+ x + (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE );
			gl.glVertex3f(50+ x + (WIDTH_ROAD_DETAIL/2), y , i * DETAIL_SIZE + DETAIL_SIZE);
			
		}
		gl.glEnd(); 		
	}
	
}
