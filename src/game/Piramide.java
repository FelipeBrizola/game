package game;

import game.GameObject;

import com.jogamp.opengl.GL2;

public class Piramide extends GameObject {

	public int size = 10;
	
	@Override
	public void draw(GL2 gl) {

		this.gl = gl;
		
		gl.glColor3f(0.5f, 0.5f, 0f);
		
		gl.glBegin(GL2.GL_QUADS);
		vertA();
		vertB();
		vertC();
		vertD();
		gl.glEnd();
		
		gl.glBegin(GL2.GL_TRIANGLES); 

		gl.glColor3f(0.5f, 0.5f, 0f);
		// Begin drawing the pyramid with 4 triangles
		vertA();
		vertB();
		vertE();
	 
		vertB();
		vertC();
		vertE();
	 
		vertC();
		vertD();
		vertE();

		vertA();
		vertD();
		vertE();
		

		gl.glEnd();
		
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINES);
		gl.glLineWidth(5f);
		vertA();
		vertB();
		
		vertB();
		vertC();
		
		vertC();		
		vertD();
		
		vertD();
		vertA();
		
		vertA();
		vertE();
		
		vertB();
		vertE();
		
		vertC();
		vertE();
		
		vertD();
		vertE();
		
		
		gl.glEnd();   // Done drawing the pyramid
		
	}
	
	
	
	void vertA(){
		gl.glVertex3f( x , y, z);		
	}
	void vertB(){
		gl.glVertex3f( x + size, y, z);		
		
	}
	void vertC(){
		gl.glVertex3f( x + size, y, z + size);		
		
	}
	void vertD(){
		gl.glVertex3f( x, y, z + size);				
	}
	
	//TOPO
	void vertE(){

		gl.glVertex3f( x+ (size/2), y + (2*size), z + (size/2));			
	}
	

}
