
package org.usfirst.frc.team4007.robot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team4007.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4007.robot.subsystems.Bras;
import org.usfirst.frc.team4007.robot.subsystems.Lanceur;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//IMPORTANT
	public static final Lanceur lanceur = new Lanceur();
	CameraServer server;
	int session;
    Image frame;
    NIVision.Point startV = new NIVision.Point(640/2, 480*3/16);
    NIVision.Point endV = new NIVision.Point(640/2, 480*7/16);
    NIVision.Point startH = new NIVision.Point(640*3/8, 480*5/16);
    NIVision.Point endH = new NIVision.Point(640*5/8, 480*5/16);
    
    Servo serv;
    
	public static OI oi;

	public static Bras bras = new Bras();
	public static DriveTrain driveTrain = new DriveTrain();
	
	//public PowerDistributionPanel pdp;
	public DigitalInput valve;
	public Relay spike;

    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
//        chooser.addObject("My Auto", new MyAutoCommand());
        /*server = CameraServer.getInstance();
        server.setQuality(50);
        server.startAutomaticCapture("cam2");*/
        
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera("cam2",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        
        //pdp = new PowerDistributionPanel();
        valve = new DigitalInput(9);
        spike = new Relay(0);
        
        serv = new Servo(5);
        
        SmartDashboard.putData("Auto mode", chooser);
        
    }
    
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
   

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        NIVision.IMAQdxStartAcquisition(session);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        NIVision.IMAQdxGrab(session, frame, 1);
        NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, startV, endV, 0.0f);
        NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, startH, endH, 0.0f);
        CameraServer.getInstance().setImage(frame);
        
        if(valve.get()){
        	spike.set(Relay.Value.kOff);
        }else{
        	spike.set(Relay.Value.kReverse);
        }
        
        //System.out.println("POV: " + oi.joystick.getPOV());
        switch(oi.joystick.getPOV()){
        	case 0:
        		//Vue lancer
        		serv.set(.6);
        	break;
        	case 270:
        		//Vue conduite
        		serv.set(0.83);
        	break;
        	case 180:
        		//Vue interne
        		serv.set(1);
        	break;
        	default:
        	break;        
        }
        
        	
        Timer.delay(0.005);
       // System.out.println("Amperage:" + pdp.getTotalCurrent());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
