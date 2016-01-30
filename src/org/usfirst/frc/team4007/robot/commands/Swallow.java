package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Swallow extends Command {

	int counter = 0;
	
    public Swallow() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super ("Swallow");
    	requires(Robot.lanceur);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Swallow.initialize()"); 
    	Robot.lanceur.gober();   	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	counter++;
   
    	System.out.println("Swallow.execute(" + counter + ")");  
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Swallow.isFinished()");  
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Swallow.end()");  
    	counter = 0;
    	Robot.lanceur.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("Swallow.interrupted()");  
    	end();
    }
}
