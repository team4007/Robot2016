package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;
import org.usfirst.frc.team4007.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Commande permettant d'inverser les commandes du robot
 */
public class SwitchDrive extends Command {

	static int switchAcc = 0;
	static int buttonDelay = 200; 
	
    public SwitchDrive() {
        // Use requires() here to declare subsystem dependencies

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switchAcc += Robot.deltaTime;
    	
    	if (switchAcc > buttonDelay) {
    		switchAcc = 0;
    		//DriveTrain.direction = -DriveTrain.direction;  	
    	}  
    	// Math Le delais ne fonctionne pas
    	DriveTrain.direction = -DriveTrain.direction;  	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
