package game;

import game.GameObject;

import com.jogamp.opengl.GL2;

// Utiliza malha de poligonos para representar objetos fora da pista

public class Building extends GameObject {

	public int size = 10;
	
	@Override
	public void draw(GL2 gl) {

		this.gl = gl;
		
		float red = getColor();
		float green = getColor();
		float blue = getColor();
		
		gl.glBegin(GL2.GL_QUADS); 
		gl.glColor3f(red, green, blue);
		// frente		
		vertA();
		vertB();
		vertF();
		vertE();
		
		// baixo			
		vertA();
		vertB();
		vertC();
		vertD();
		
		// direito
		vertB();
		vertC();
		vertH();
		vertF();
		
		// esquerdo
		vertB();
		vertD();
		vertG();
		vertE();
		
		// cima
		vertE();
		vertF();
		vertH();
		vertG();
		
		// fundo
		vertD();
		vertC();
		vertH();
		vertG();
		gl.glEnd();
		
		// desenha linhas ao entorno do predio. linhas ao fundo do cubo nao precisam ser criadas		
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINES);
		
		vertA();
		vertB();
		
		vertB();
		vertC();
		
		vertA();
		vertE();
		
		vertB();
		vertF();
		
		vertE();
		vertF();
		
		vertF();
		vertB();
		
		
		gl.glEnd();

	
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
	
	void vertE(){
		gl.glVertex3f( x, y + size, z);				
	}
	void vertF(){
		gl.glVertex3f( x + size, y + size, z);				
	}
	void vertG(){
		gl.glVertex3f( x, y + size, z + size);				
	}
	void vertH(){
		gl.glVertex3f( x + size, y + size, z + size);				
	}
	
	public float getColor() {
		// com 50% da prova os predios ficam piscantes
		if (this.ROAD_SIZE - z > 0.5 * this.ROAD_SIZE)
			return 0.5f;
		
		return (float)this.randomColor();
	}	

}
