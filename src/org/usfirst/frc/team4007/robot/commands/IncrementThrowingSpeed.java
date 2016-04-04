package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IncrementThrowingSpeed extends Command{
	
	public IncrementThrowingSpeed(){
		requires(Robot.lanceur);
	}
	 protected void initialize() {
	    	Robot.lanceur.incrementThrowingSpeed();
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	
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
	    	end();
	    	
	    }

}