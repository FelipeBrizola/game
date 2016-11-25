package game;

import game.GameObject;

import com.jogamp.opengl.GL2;

public class Cube extends GameObject{

	@Override
	public void draw(GL2 gl) {
		this.gl = gl;
		gl.glBegin(GL2.GL_QUADS);                // Begin drawing the color cube with 6 quads
	      // Top face (y = 1.0f)
	      // Define vertices in counter-clockwise (CCW) order with normal pointing out
		gl.glColor3f(0.0f, 1.0f, 0.0f);     // Green
		gl.glVertex3f( 1.0f, 1.0f, -1.0f);
		gl.glVertex3f(-1.0f, 1.0f, -1.0f);
		gl.glVertex3f(-1.0f, 1.0f,  1.0f);
		gl.glVertex3f( 1.0f, 1.0f,  1.0f);
	 
	      // Bottom face (y = -1.0f)
		gl.glColor3f(1.0f, 0.5f, 0.0f);     // Orange
		gl.glVertex3f( 1.0f, -1.0f,  1.0f);
		gl.glVertex3f(-1.0f, -1.0f,  1.0f);
		gl.glVertex3f(-1.0f, -1.0f, -1.0f);
		gl.glVertex3f( 1.0f, -1.0f, -1.0f);
	 
	      // Front face  (z = 1.0f)
		gl.glColor3f(1.0f, 0.0f, 0.0f);     // Red
		gl.glVertex3f( 1.0f,  1.0f, 1.0f);
		gl.glVertex3f(-1.0f,  1.0f, 1.0f);
		gl.glVertex3f(-1.0f, -1.0f, 1.0f);
		gl.glVertex3f( 1.0f, -1.0f, 1.0f);
	 
	      // Back face (z = -1.0f)
		gl.glColor3f(1.0f, 1.0f, 0.0f);     // Yellow
		gl.glVertex3f( 1.0f, -1.0f, -1.0f);
		gl.glVertex3f(-1.0f, -1.0f, -1.0f);
		gl.glVertex3f(-1.0f,  1.0f, -1.0f);
		gl.glVertex3f( 1.0f,  1.0f, -1.0f);
	 
	      // Left face (x = -1.0f)
		gl.glColor3f(0.0f, 0.0f, 1.0f);     // Blue
		gl.glVertex3f(-1.0f,  1.0f,  1.0f);
		gl.glVertex3f(-1.0f,  1.0f, -1.0f);
		gl.glVertex3f(-1.0f, -1.0f, -1.0f);
		gl.glVertex3f(-1.0f, -1.0f,  1.0f);
	 
	      // Right face (x = 1.0f)
		gl.glColor3f(1.0f, 0.0f, 1.0f);     // Magenta
		gl.glVertex3f(1.0f,  1.0f, -1.0f);
		gl.glVertex3f(1.0f,  1.0f,  1.0f);
		gl.glVertex3f(1.0f, -1.0f,  1.0f);
		gl.glVertex3f(1.0f, -1.0f, -1.0f);
		gl.glEnd();  // End of drawing color-cube
	}


	
}
