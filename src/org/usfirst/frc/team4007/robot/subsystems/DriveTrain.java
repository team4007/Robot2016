package org.usfirst.frc.team4007.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
/*import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;*/


import org.usfirst.frc.team4007.robot.RobotMap;
import org.usfirst.frc.team4007.robot.commands.DriveWithJoystick;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Talon roueGauche1,roueDroite1;
	
	
	/*public Encoder encoder1, encoder2;
	public PIDController pid1, pid2;*/
	

	
	RobotDrive drive1;
	
	//private double drivingSpeed = .25;
	
	
	public DriveTrain() {
		super();
		roueGauche1 = new Talon(RobotMap.PWMRoueGauche1);
		roueDroite1 = new Talon(RobotMap.PWMRoueDroite1);
		
		/*encoder1 = new Encoder (0,1);
		pid1 = new PIDController(.1,0,0,encoder1,chenilleGauche);
		encoder2 = new Encoder (2,3);
		pid2 = new PIDController(.1,0,0,encoder2,chenilleDroite);*/
		drive1 = new RobotDrive(roueGauche1, roueDroite1);
		
	}
	
	

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoystick());

    }
    
    public void stop() {
    	roueGauche1.stopMotor();
    	roueDroite1.stopMotor();
    	
    }
    
    public void drive(Joystick joystick) {
    	//drive.arcadeDrive(-joystick.getY(), -joystick.getX());     1 joystick
    	drive1.arcadeDrive(joystick.getRawAxis(1), joystick.getRawAxis(4));     // 2 joysticks
    
    }
}


