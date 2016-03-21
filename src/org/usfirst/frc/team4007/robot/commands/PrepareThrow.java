package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrepareThrow extends Command {
	static long delay = 2;
	long time;
	boolean oppositeImpulse = false;
	
	// TODO : Comment eviter le relachement du bouton accidentel?
	static long errorDelay = 500;
	long errorAcc = 0;
	
	
    public PrepareThrow() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super ("Throw");
    	requires(Robot.lanceur);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = System.currentTimeMillis();
    	oppositeImpulse = false;
    	System.out.println("Throw.initialize()");   
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	errorAcc += Robot.deltaTime;
	
    	if(System.currentTimeMillis() - time < delay){
    		/** Permet de donner une impulsion pour sortir le ballon juste
    		 * de preparer les essieux superieurs
    		 */
    		if (errorAcc > errorDelay) {
    			System.out.println("errorAcc = " + errorAcc);
    			errorAcc = 0;
    			Robot.lanceur.eject();
    		}
    	}else{
    		if(!oppositeImpulse){
    			Robot.lanceur.gober();
    			oppositeImpulse = true;
    			Timer.delay(0.01);
    		}
    		
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
