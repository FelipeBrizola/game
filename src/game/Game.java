package game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import game.ColisionDetector;
import game.GameConfiguration;
import game.GameEngine;
import game.GameEngine.CameraCallback;
import game.GameEngine.OnUpdateCallback;
import game.Car;
import game.Cenario;
import game.Player;
import game.Road;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;



public class Game implements GameConfiguration {
	
	GameEngine engine = new GameEngine();
	Object carsLock = new Object();
	
	TextRenderer renderer;
		
	//Objects
	Player player = new Player();
	Road road = new Road();	
	ArrayList<Car> cars = new ArrayList<>();
	Cenario cenario = new Cenario();
	
	ActionListener actionAddCar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(player.speed > 10)
				cars.add(new Car(player.z));
		}
	};
	Timer timerAddCar = new Timer(TIME_TO_ADD_CAR, actionAddCar);
		
	
	//Game Lifecycle Methos
	public Game() { 
	
        engine.setUpdateCallback(updateCallback);
        engine.setControls(gameControl);
        engine.setCameraCallback(cameraCallback);
        engine.start();
        
		timerAddCar.start();
	}

	OnUpdateCallback updateCallback = new OnUpdateCallback() {
		
		@Override
		public void onUpdate(GL2 gl, GLU glu) {
			synchronized (carsLock) {
				timerAddCar.setDelay((int)(TIME_TO_ADD_CAR - 5 * player.speed));
				road.draw(gl);
				cenario.draw(gl);
				player.move();
				player.draw(gl);
				
				for( Car car: cars ){
					for( Car car_aux: cars ){
						if( !car_aux.equals(car) && ColisionDetector.isColiding(car, car_aux)){
							//Se os carros baterem o de traz freia
							if( car.z > car_aux.z ){
								car_aux.speed--;
								if( car.speed == car_aux.speed)
									car.speed++;
							}else{
								car.speed--;
								if( car_aux.speed == car.speed)
									car_aux.speed++;
							}
						}
					}
					car.move();
					car.draw(gl);
					
					if( ColisionDetector.isColiding(player, car)){
						playerLose();
						return;
					}
					
					if( car.z < player.z && !car.isBehind){
						car.isBehind = true;
						player.score+= player.speed;
						player.total_cars_behind++;
					}
				}
				
				if( player.z > ROAD_SIZE)
					playerWin();
				
				if( player.x > -CAR_SIZE_X + WIDTH_ROAD_SIZE/2 || player.x < (-WIDTH_ROAD_SIZE)/2){
					playerLose();
				}
				
				DisplayInformation();
				
				
			}			
			
		}

		private void DisplayInformation() {
			renderer = new TextRenderer(
					new Font("SansSerif", Font.BOLD, 25));
			
			// Display Score
			renderer.beginRendering(850, 850);
			renderer.setColor(1.0f, 1.0f, 1.0f, 0.8f);
			renderer.draw("Score: "+player.score, 20, 775);
			renderer.endRendering();
			
			// Display total Cars Behind
			renderer.beginRendering(850, 850);
			renderer.setColor(1.0f, 1.0f, 1.0f, 0.8f);
			renderer.draw("Cars: "+player.total_cars_behind, 20, 750);
			renderer.endRendering();	
			
			
			// Display Distance Left
			renderer.beginRendering(850, 850);
			renderer.setColor(1.0f, 1.0f, 1.0f, 0.8f);
			renderer.draw("Left: "+((int)((ROAD_SIZE - player.z)/CAR_MAX_SPEED))+ " m", 20, 800);
			renderer.endRendering();	
			
			//Display Velocity
			renderer.beginRendering(850, 850);
			renderer.setColor(1.0f, 1.0f, 1.0f, 0.8f);
			renderer.draw("Speed: "+(int)(player.speed * 3)+ " km/h", 20, 700);
			renderer.endRendering();	
			
			
		}

		private void playerLose() {
			engine.stop();
			renderer = new TextRenderer(
					new Font("SansSerif", Font.BOLD, 60));
			renderer.beginRendering(850, 850);
			renderer.setColor(1.0f, 0.0f, 0.0f, 0.8f);
			renderer.draw("Game Over!", 280, 700);
			renderer.endRendering();
		
		}

		private void playerWin() {
			engine.stop();
			renderer = new TextRenderer(
					new Font("SansSerif", Font.BOLD, 60));
			renderer.beginRendering(850, 850);
			renderer.setColor(0.0f, 1.0f, 0.0f, 0.8f);
			renderer.draw("You Win!", 280, 700);
			renderer.endRendering();
		}
		
	};
	

	private void restartGame() {
		synchronized (carsLock) {
			cars.clear();
			player = new Player();
			engine.restart();
		}
	}
	
	//Camera Callback
	CameraCallback cameraCallback = new CameraCallback() {		
		@Override
		public void updateCamera(GLU glu) {
			float x_aux = (player.x) / 7;
	        glu.gluLookAt(
	        		player.x +(CAR_SIZE_X/2) + x_aux , player.y +35, player.z - 50, 	//WHERE
	        		player.x+(CAR_SIZE_X/2) + x_aux, player.y+35, player.z,	//AT 
	        		0, 1, 0 	//SKY
	        		);
		}
	};
	
		
	//Game Control
	KeyListener gameControl = new KeyListener() {
		
		@Override public void keyTyped(KeyEvent e) { }
		@Override public void keyReleased(KeyEvent e) { 
			switch(e.getKeyCode()){
			case CTRL_LEAVE:
				//leave game
				System.exit(0);
				break;
			
			case CTRL_LEFT:
				player.isTurningLeft = false;
				break;
				
			case CTRL_RIGHT:
				player.isTurningRight = false;					
				break;
				
			case CTRL_ACCELERATE:
				player.isAccelerating = false;
				break;
				
			case CTRL_BREAK:
				player.isBreaking = false;
				break;
		}
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()){
				case CTRL_LEAVE:
					//leave game
					System.exit(0);
					break;
				
				case CTRL_LEFT:
					player.isTurningLeft = true;
					break;
					
				case CTRL_RIGHT:
					player.isTurningRight = true;					
					break;
					
				case CTRL_ACCELERATE:
					player.isAccelerating = true;
					break;
					
				case CTRL_BREAK:
					player.isBreaking = true;
					break;
					
				case CTRL_RESTART:
					restartGame();
					break;
				case PRINT_LOG:
					
					break;
			}
		}
	};
	
}
