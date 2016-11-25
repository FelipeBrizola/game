package game;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import game.Car;
import game.Player;
import game.Road;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

public class GameEngine implements GLEventListener, GameConfiguration{

	public interface OnUpdateCallback{
		void onUpdate(GL2 gl, GLU glu);
	}
	
    public interface CameraCallback{
    	void updateCamera(GLU glu);
    }
    
	public void setControls(KeyListener keyListener){
		gameControl = keyListener;
	}
	
	public void setUpdateCallback(OnUpdateCallback callback){
		onUpdateCallback = callback;
	}
	
	public void setCameraCallback(CameraCallback callback){
		cameraCallback = callback;
	}

	private JFrame window;
	private FPSAnimator fpsAnimator;
	
	private OnUpdateCallback onUpdateCallback;
	private CameraCallback cameraCallback;
	private KeyListener gameControl;
	
    public GLU glu;
	private GLCanvas canvas;	

	
	public void start(){
		window = new JFrame();
        canvas = new GLCanvas(getCapabilites());
		glu = new GLU();
		fpsAnimator = new FPSAnimator(canvas, FPS);
		
		//Configure Canvas
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.addKeyListener(gameControl);
		canvas.addGLEventListener(this);
		
		//Configure Window
		window.setLocation(20, 20);
		window.getContentPane().add(canvas);
		window.setUndecorated(true); // Remove title bar
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(canvas);
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
		window.requestFocus();
        canvas.requestFocus();
	}
	
	GLCapabilities getCapabilites() {
		GLCapabilities capabilities = new GLCapabilities(GLProfile.getDefault());
		capabilities.setRedBits(8);
		capabilities.setBlueBits(8);
		capabilities.setGreenBits(8);
		capabilities.setAlphaBits(8);
		return capabilities;
	}
	
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
    public void dispose(GLAutoDrawable drawable) { }
	
    public void init(GLAutoDrawable drawable) {
    	GL2 gl = drawable.getGL().getGL2();
    	drawable.setGL(gl);
    	
        // Global settings.
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); 
		gl.glClearDepth(1.0f);		
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

        // Start animator (which should be a field).
        fpsAnimator.start();
        //setCamera(gl, glu, 100);
    }
    
    public void stop(){
    	if( fpsAnimator.isStarted() )
    		fpsAnimator.stop();
    }
    
    public void restart(){
    	if( !fpsAnimator.isStarted() )
    		fpsAnimator.start();
    }
    
    
    //MOST IMPORTANT M
    public void display(GLAutoDrawable drawable) {
    	GL2 gl = drawable.getGL().getGL2();
    	
    	// Change to projection matrix.
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        
        // Perspective.
        float widthHeightRatio = (float) canvas.getWidth() / (float) canvas.getHeight();
        glu.gluPerspective(45, widthHeightRatio, 1, CAMERA_DISTANCE);
        
        cameraCallback.updateCamera(glu);

        // Change back to model view matrix.
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // Clear color and depth buffers
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        
    	onUpdateCallback.onUpdate(gl, glu);
    	

    }
	
}
