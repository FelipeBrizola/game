package game;

import com.jogamp.newt.event.KeyEvent;

public interface GameConfiguration{ 
	
	//Window
	static final int WIDTH = 1024, HEIGHT = 720, FPS = 60;

	//Cenario Settings
	static final int TIME_TO_ADD_CAR = 650;
	static final int MINIMUM_SPEED = 4;
	
	//CAMERA DISTANCE
	static final int CAMERA_DISTANCE = 20000;
	
	//Player Settings
	static final int CAR_MAX_SPEED = 80;
	static final int CAR_SIZE_X = 10, CAR_SIZE_Y = 20, CAR_SIZE_Z = 30;
	static final float COLOR_RED = 0.5f, COLOR_GREEN = 0.0f, COLOR_BLUE = 0.0f;
	
	//Control
	static final int
			CTRL_LEFT = 37, 
			CTRL_ACCELERATE = 38,
			CTRL_RIGHT = 39,
			CTRL_BREAK = 40,
			CTRL_RESTART = KeyEvent.VK_SPACE,
			CTRL_LEAVE = 27,
			PRINT_LOG = KeyEvent.VK_P;
	
	
	//Road Settings
	static final int ROAD_SIZE =  30 * 60 * CAR_MAX_SPEED;
	static final int WIDTH_ROAD_SIZE = 300;
	static final int WIDTH_ROAD_DETAIL = 10;
	static final int TOTAL_DETAILS = 50;
	static final int DETAIL_SIZE = 100;

	
}
