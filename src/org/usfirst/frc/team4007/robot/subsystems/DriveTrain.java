package org.usfirst.frc.team4007.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Jaguar;
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
	public Jaguar roueGauche;
	public Jaguar roueDroite;
	/*public Encoder encoder1, encoder2;
	public PIDController pid1, pid2;*/
	

	
	RobotDrive drive;
	
	private double drivingSpeed = .25;
	
	
	public DriveTrain() {
		super();
		roueGauche = new Jaguar(RobotMap.PWMRoueGauche);
		roueDroite = new Jaguar(RobotMap.PWMRoueDroite);
		/*encoder1 = new Encoder (0,1);
		pid1 = new PIDController(.1,0,0,encoder1,chenilleGauche);
		encoder2 = new Encoder (2,3);
		pid2 = new PIDController(.1,0,0,encoder2,chenilleDroite);*/
		drive = new RobotDrive(roueGauche, roueDroite);
	}
	
	

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoystick());

    }
    
    public void stop() {
    	roueGauche.stopMotor();
    	roueDroite.stopMotor();
    }
    
    public void drive(Joystick joystick) {
    	//drive.arcadeDrive(-joystick.getY(), -joystick.getX());     1 joystick
    	drive.arcadeDrive(joystick.getRawAxis(1), joystick.getRawAxis(4));     // 2 joysticks
    	
    }
}


