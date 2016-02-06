package org.usfirst.frc.team4007.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	public static int PWMRoueGauche = 0;
	public static int PWMRoueDroite = 1;
	
	public static int PWMEssieuHaut = 2;
	public static int PWMEssieuMilieu = 3;
	public static int PWMEssieuBas = 4;
	
	public static int ANALOGINPUTSonar = 0;
	
		
}
