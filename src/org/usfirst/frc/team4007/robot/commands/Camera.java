package org.usfirst.frc.team4007.robot.commands;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

/**
 *
 */
public class Camera extends Command {

	CameraServer server;
	int session;
	Image frame;
    NIVision.Point startV = new NIVision.Point(640/2, 480*3/16);
    NIVision.Point endV = new NIVision.Point(640/2, 480*7/16);
    NIVision.Point startH = new NIVision.Point(640*3/8, 480*5/16);
    NIVision.Point endH = new NIVision.Point(640*5/8, 480*5/16);
    
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
    	session= NIVision.IMAQdxOpenCamera("cam2",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
    	NIVision.IMAQdxConfigureGrab(session);
    	NIVision.IMAQdxStartAcquisition(session);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("Video loop");
    	NIVision.IMAQdxGrab(session, frame, 1);
        NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, startV, endV, 0.0f);
        NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, startH, endH, 0.0f);
        CameraServer.getInstance().setImage(frame);
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
