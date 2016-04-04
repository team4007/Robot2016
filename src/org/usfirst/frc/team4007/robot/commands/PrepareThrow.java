package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrepareThrow extends Command {
	static long delay = 30;
	long previousTime = 0;
	boolean oppositeImpulse = false;
	long currentTime;
	long deltaTime = 0;
	long accTime = 0;
	
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
    	
    	currentTime = System.currentTimeMillis();
    	previousTime = System.currentTimeMillis();
    	
    	oppositeImpulse = false;
    	System.out.println("Throw.initialize()");  
		accTime = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentTime = System.currentTimeMillis();
    	deltaTime = currentTime - previousTime;
    	
    	accTime += deltaTime;
    	errorAcc += Robot.deltaTime;
	
    	System.out.println("accTime : " + accTime);
    	if(accTime < delay){
    		/** Permet de donner une impulsion pour sortir le ballon juste
    		 * de preparer les essieux superieurs
    		 */
    		System.out.println("PrepareThrow Premier passage");

    		
    		//if (errorAcc > errorDelay) {
    			//System.out.println("PrepareThrow : Dans l'errorDelay");
    			errorAcc = 0;
    			Robot.lanceur.eject();
    			
    			//Robot.lanceur.essieuMilieu.set(1.0);
    		//}
    	}else{
    		if(!oppositeImpulse){
    			Robot.lanceur.eject();
    			oppositeImpulse = true;
    			//Timer.delay(0.01);
    			System.out.println("PrepareThrow : !oppositeImpulse");
    		}
    		
	    	Robot.lanceur.downStopLancer();
	    	Robot.lanceur.preparerLancer();
    	}
    	
    	previousTime = currentTime;
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
