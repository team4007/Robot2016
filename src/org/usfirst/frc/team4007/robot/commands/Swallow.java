package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class Swallow extends Command {
	
	long time;
	
    public Swallow() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super ();
    	
    	requires(Robot.lanceur);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	 
    	  	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lanceur.gober();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.lanceur.isFinishedSwallowing){
    		System.out.println("Swallow.isFinished()");  
    		return true;
    	}else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Swallow.end()");  
    	
    	//Robot.lanceur.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("Swallow.interrupted()");  
    	end();
    }
}
