package game;

import java.util.ArrayList;
import java.util.Random;

import com.jogamp.opengl.GL2;

import game.GameObject;

public class Cenario extends GameObject {

	private static final int TOTAL_BUILDINGS = 150;

	
	//	Adiciona predios aleatoriamente ao cenario
	private ArrayList<Building> builds = new ArrayList<>();
	public Cenario() {
		Random random = new Random();
		for( int i = 0; i < TOTAL_BUILDINGS; i++){
			Building b = new Building();
			b.z = random.nextInt(ROAD_SIZE);
			b.x = random.nextInt(2) % 2 == 0? 285 + random.nextInt(1000): -850 - random.nextInt(1000);
			b.y = -30;
			b.size = 50 +random.nextInt(500);
			builds.add(b);
		}
	}
	
	// Desenha o cenario ao redor da pista e adiciona os predios
	@Override
	public void draw(GL2 gl) {
		
		float[] color = getColor();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(color[0], color[1], color[2]);
		
		gl.glVertex3f( x - (WIDTH_ROAD_SIZE*4), y , z + 2 * ROAD_SIZE);
		gl.glVertex3f( x - (WIDTH_ROAD_SIZE*4), y , z);
		gl.glVertex3f( x + (WIDTH_ROAD_SIZE*4), y , z);
		gl.glVertex3f( x + (WIDTH_ROAD_SIZE*4), y , z + 2 * ROAD_SIZE);
		
		gl.glEnd();
		
		for(Building b: builds){
			b.draw(gl);
		}
	}

	private float[] getColor() {
		float[] ret = new float[3];
		if (this.ROAD_SIZE - this.z > 0.2 * this.ROAD_SIZE) {
			// branco			
			ret[0] = 1.0f;
			ret[1] = 1.0f;
			ret[2] = 1.0f;
		}
		else{
			// preto			
			ret[0] = 0.0f;
			ret[1] = 0.0f;
			ret[2] = 0.0f;
		}
		return ret;
	}
	
}
