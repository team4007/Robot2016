package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrepareThrow extends Command {
	static long delta = 2;
	long time;
    public PrepareThrow() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super ("Throw");
    	requires(Robot.lanceur);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = System.currentTimeMillis();
    	System.out.println("Throw.initialize()");   
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(System.currentTimeMillis() - time < delta){
    		Robot.lanceur.downLancer();
    	}else{
    	Robot.lanceur.downStopLancer();
    	Robot.lanceur.preparerLancer();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lanceur.stopGober();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    	
    }
}
