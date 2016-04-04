package org.usfirst.frc.team4007.robot.commands;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4007.robot.Robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Point;
import com.ni.vision.NIVision.RGBValue;
import com.ni.vision.NIVision.Rect;
import com.ni.vision.NIVision.ShapeMode;

/**
 *
 */
public class Camera extends Command {

	CameraServer server;
	int session;
	Image frame;
	
	int frameDelay = 100;
	long frameAcc = 0;
	
	/**
	 * Modification des ratios pour descendre la ligne
	 */
	
	/**
	 * x et y centre en date du 20160331
	 */
	int x = 280;
	int y = 180;
	int largeur = 160;
	int hauteur = 120;
	
	int left = x - largeur / 2;
	int right = x + largeur / 2;
	int top = y - hauteur / 2;
	int bottom = y + hauteur / 2;
	
    NIVision.Point startV = new NIVision.Point(x, top);
    NIVision.Point endV = new NIVision.Point(x, bottom);
    NIVision.Point startH = new NIVision.Point(left, y);
    NIVision.Point endH = new NIVision.Point(right, y);
    
    Point p;
    
    NIVision.Rect rect = new Rect(top, left, hauteur, largeur);
    
    public Camera() {
    	System.out.println("new object");
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	server = CameraServer.getInstance();
           server.setQuality(50);
           //server.startAutomaticCapture("cam2");
       
           frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);          
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//System.out.println("Configuration");
    	session= NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
    	NIVision.IMAQdxConfigureGrab(session);
    	NIVision.IMAQdxStartAcquisition(session);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("Video loop");
    	frameAcc += Robot.deltaTime;
    	//System.out.println("2222"+frameAcc);
    	if (frameAcc >= frameDelay){
    		frameAcc = 0;
    		//System.out.println(frameAcc);
	    	NIVision.IMAQdxGrab(session, frame, 1);
	        NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, startV, endV, 0.0f);
	        NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, startH, endH, 0.0f);
	        NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 0x00FF00);
	        CameraServer.getInstance().setImage(frame);
    	}
    	
        if(this.isCanceled())
    	{	
    		System.out.println("Command finished");
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
