package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import game.GameObject;

import com.jogamp.opengl.GL2;

public class Player extends GameObject{

	public float speed = 0;
	public float acceleration = 0;
	public int total_cars_behind = 0;
	public int score = 0;

	public boolean isTurningRight;

	public boolean isTurningLeft;

	public boolean isAccelerating;

	public boolean isBreaking;

	
	public void tilt(float base){
		Random random = new Random();
		x+= ((-5) + random.nextInt(10)) * (base/2);
	}
	public void move(){
		z += speed;
		
		if(isTurningLeft)
			if(speed != 0)
				x +=  5 - speed/30;
		
		if(isTurningRight)
			if(speed != 0)
				x-= 5 - speed/30;
		
		if(isAccelerating){
			if(speed < 10)
				acceleration = 2;
			else
				acceleration = 20/speed;
			if( speed < CAR_MAX_SPEED )
				speed+= acceleration;
		}
		if(isBreaking){
			if( speed <= 1 ) speed = 0;
			if( speed > 1) speed-=1;
		}
			
		
	}

	
	//DRAWING METHODS
	/**
	 * Eight Vertices
	 * 	G   H
	 * 	C   D
	 *E   F
	 *A   B
	 * */
	
	public void vertA(){
		gl.glVertex3f( x , y , z);
	}
	
	public void vertB(){
		gl.glVertex3f( x + CAR_SIZE_X , y, z);
		
	}
	
	public void vertC(){
		gl.glVertex3f( x , y, z + CAR_SIZE_Z);
	}
	
	public void vertD(){
		gl.glVertex3f( x + CAR_SIZE_X, y, z + CAR_SIZE_Z);		
	}
	
	public void vertE(){
		gl.glVertex3f( x , y + CAR_SIZE_Y , z);
	}
	
	public void vertF(){
		gl.glVertex3f( x + CAR_SIZE_X , y + CAR_SIZE_Y, z);
		
	}
	
	public void vertG(){
		gl.glVertex3f( x , y + CAR_SIZE_Y, z + CAR_SIZE_Z);
	}
	
	public void vertH(){
		gl.glVertex3f( x + CAR_SIZE_X, y + CAR_SIZE_Y , z + CAR_SIZE_Z);		
	}
	
	@Override
	public float[][] get2DSquare() {
		float[][] square = new float[4][2];
		
		square[DL][X] = x;
		square[DL][Z] = z;
		
		square[DR][X] = x + CAR_SIZE_X;
		square[DR][Z] = z;
		
		square[UL][X] = x;
		square[UL][Z] = z + CAR_SIZE_Z;
		
		square[UR][X] = x + CAR_SIZE_X;
		square[UR][Z] = z + CAR_SIZE_Z;
		
		return square;
	}


	
	@Override
	public void draw(GL2 gl) {
		this.gl = gl;
		
		//	red, green, blue	
		float[] color;
		color = getColor();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(color[0], color[1], color[2]);
		
			  // Top face
			vertE();
			vertF();
			vertH();
			vertG();
		 
		      // Bottom face
			vertA();
			vertB();
			vertD();
			vertC();
		 
		      // Front face  
			vertC();
			vertD();
			vertH();
			vertG();
		 
		      // Back face (z = -1.0f)
			vertA();
			vertB();
			vertF();
			vertE();
		 
		      // Left face (x = -1.0f)
			vertA();
			vertC();
			vertG();
			vertE();
		 
		      // Right face (x = 1.0f)
			vertB();
			vertD();
			vertH();
			vertF();
			
		gl.glEnd();  // End of drawing color-cube
		
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3f(0f, 0f, 0f);
		gl.glLineWidth(5f);
		
		//First Square
		vertA();
		vertB();
		
		vertB();
		vertF();

		vertF();
		vertE();
		

		vertE();
		vertA();
		
		
		//Ligations
		vertA();
		vertC();

		vertB();
		vertD();
		
		vertE();
		vertG();		

		vertF();
		vertH();
		
		//Second Square
		vertC();
		vertD();

		vertD();
		vertH();

		vertH();
		vertG();

		vertG();
		vertC();	
		
		gl.glEnd();  	
	 
	}
	

	// Apos 50% do jogo completo, player troca de cor;	
	public float[] getColor(){
		float[] ret = new float[3];
		if (this.ROAD_SIZE - z > 0.5 * this.ROAD_SIZE) {
			ret[0] = 0f;
			ret[1] = 0f;
			ret[2] = 1f;
		}
		else{
			ret[0] = 1f;
			ret[1] = 0f;
			ret[2] = 0f;
		}
		return ret;
	}
	
}
