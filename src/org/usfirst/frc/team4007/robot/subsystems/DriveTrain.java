package org.usfirst.frc.team4007.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

import org.usfirst.frc.team4007.robot.RobotMap;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Jaguar chenilleGauche;
	public Jaguar chenilleDroite;
	RobotDrive drive;
	
	private double drivingSpeed = .25;
	
	
	public DriveTrain() {
		super();
		chenilleGauche = new Jaguar(RobotMap.PWMChenilleGauche);
		chenilleDroite = new Jaguar(RobotMap.PWMChenilleDroite);

		drive = new RobotDrive(chenilleGauche, chenilleDroite);
	}
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	stop();
    }
    
    public void stop() {
    	chenilleGauche.stopMotor();
    	chenilleDroite.stopMotor();
    }
    
    public void drive(Joystick joystick) {
    	drive.arcadeDrive(joystick);
    	
    }
}


