package game;


import java.util.Random;

import com.jogamp.opengl.GL2;

public class Car extends GameObject {
	
	public int size_x, size_y, size_z;
	public int speed = 0;
	public int carType = 0;

	public boolean isBehind = false;
	public int x_original;
	public int x_next;
	public int x_range;
	public int z_counter;
	public int z_min;
	
	private Random random;
	
	public Car(float player_z) {
		z =  player_z + (float)( CAMERA_DISTANCE * 1.5);
		
		random = new Random();
		
		int pos = random.nextInt(3);
		if(pos == 0) // left
			x = 70.0f;
		else if(pos == 1) //center
			x = -50.0f;
		else
			x = -150.0f; //rigth
	
		x_original = (int)x;
		int carType = random.nextInt(3);
		int great = random.nextInt(10);
		
		switch (carType) {
		case 1: //caminhao
			x_range = 15;
			z_min = 100;
			size_z = 170  + great * 25;
			size_y = 45 + great * 2;
			size_x = 45 + great * 5;
			speed = 12 + (int)(great * 0.3);
			break;
		case 2://Moto
			x_range = 25;
			z_min = 20;
			size_z = 30 + great * 2;
			size_y = 20 + (int)(great * 1.5);
			size_x = 10 + great * 1;
			speed = 10 + (int) (great * 1);	
			x-= 15;
			break;
		default: //carro
			x_range = 20;
			z_min = 50;
			size_z = 50 + (int)(great * 3.0);
			size_x = 30 + (int)(great * 2.0);
			size_y = 25 + (int)(great * 1.0);
			speed = 12 + (int) (great* 0.6);
			x-= size_x/3;
			break;
		}
	}
	
	public void move(){
		if(speed <= MINIMUM_SPEED ) speed = MINIMUM_SPEED;
			z += speed;
		
		//Set New Rote to X
		if( x == x_next ){
			x_next = -x_range + x_original + random.nextInt(x_range * 2);
			z_counter = 0;
		}
		
		//Move X
		else{
			z_counter+=speed;
			if( z_counter > z_min){
				z_counter = 0;
				
				//Move
				if( x > x_next){
					x--;
				}else{
					x++;
				}	
			}
		}
		
	}
	
	@Override
	public void draw(GL2 gl) {
		this.gl = gl;
		gl.glBegin(GL2.GL_QUADS);
		
		float[] color = new float[3];
		color =  getColor(false);
		gl.glColor3f(color[0], color[1], color[2]);
		
			// cima			
			vertE();
			vertF();
			vertH();
			vertG();
		 
		    // baixo
			vertA();
			vertB();
			vertD();
			vertC();
		 
		    // frente  
			vertC();
			vertD();
			vertH();
			vertG();
		 
		    // atras
			vertA();
			vertB();
			vertF();
			vertE();
		 
		    // esquerda
			vertA();
			vertC();
			vertG();
			vertE();
		 
		    // direita
			vertB();
			vertD();
			vertH();
			vertF();
			
		gl.glEnd();  // End of drawing color-cube
	
		gl.glBegin(GL2.GL_LINES);
		boolean isLine = true;
		color = getColor(isLine);
		gl.glColor3f(color[0], color[1], color[2]);
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
	
	private float[] getColor(boolean isLine) {		
		float[] ret = new float[3];
		if (!isLine) {
			if (this.ROAD_SIZE - z > 0.5 * this.ROAD_SIZE) {
				ret[0] = 1f;
				ret[1] = 1f;
				ret[2] = 1f;
			}
			else{
				ret[0] = 0f;
				ret[1] = 0f;
				ret[2] = 0f;
			}
		}
		else {
			if (this.ROAD_SIZE - z > 0.5 * this.ROAD_SIZE) {
				ret[0] = 0f;
				ret[1] = 0f;
				ret[2] = 0f;
			}
			else{
				ret[0] = 1f;
				ret[1] = 1f;
				ret[2] = 1f;
			}
		}
		
		return ret;
	}

	public void vertA(){
		gl.glVertex3f( x , y , z);
	}
	
	public void vertB(){
		gl.glVertex3f( x + size_x , y, z);
	}
	
	public void vertC(){
		gl.glVertex3f( x , y, z + size_z);
	}
	
	public void vertD(){
		gl.glVertex3f( x + size_x, y, z + size_z);		
	}
	
	public void vertE(){
		gl.glVertex3f( x , y + size_y , z);
	}
	
	public void vertF(){
		gl.glVertex3f( x + size_x , y + size_y, z);
		
	}
	
	public void vertG(){
		gl.glVertex3f( x , y + size_y, z + size_z);
	}
	
	public void vertH(){
		gl.glVertex3f( x + size_x, y + size_y , z + size_z);		
	}
	
	public float[][] get2DSquare() {
		float[][] square = new float[4][2];
		
		square[DL][X] = x;
		square[DL][Z] = z;
		
		square[DR][X] = x + size_x;
		square[DR][Z] = z;
		
		square[UL][X] = x;
		square[UL][Z] = z + size_z;
		
		square[UR][X] = x + size_x;
		square[UR][Z] = z + size_z;
		
		return square;
	}
	


	
	
}
