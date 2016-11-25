package game;

import java.util.ArrayList;
import java.util.Random;

import com.jogamp.opengl.GL2;

import game.GameObject;

public class Cenario extends GameObject {

	private static final int TOTAL_PIRAMIDES = 150;

	private ArrayList<Piramide> piramides = new ArrayList<>();
	public Cenario() {
		Random random = new Random();
		for( int i = 0; i < TOTAL_PIRAMIDES; i++){
			Piramide piramide = new Piramide();
			piramide.z = random.nextInt(ROAD_SIZE);
			piramide.x = random.nextInt(2) % 2 == 0? 285 + random.nextInt(1000): -850 - random.nextInt(1000);
			piramide.y = -30;
			piramide.size = 50 +random.nextInt(500);
			piramides.add(piramide);
		}
	}
	@Override
	public void draw(GL2 gl) {
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(0.4f, 0.4f, 0.0f);
		
		gl.glVertex3f( x - (WIDTH_ROAD_SIZE*4), y , z + 2 * ROAD_SIZE);
		gl.glVertex3f( x - (WIDTH_ROAD_SIZE*4), y , z);
		gl.glVertex3f( x + (WIDTH_ROAD_SIZE*4), y , z);
		gl.glVertex3f( x + (WIDTH_ROAD_SIZE*4), y , z + 2 * ROAD_SIZE);
		
		gl.glEnd();
		
		for(Piramide p: piramides){
			p.draw(gl);
		}
	}
	


	
}
