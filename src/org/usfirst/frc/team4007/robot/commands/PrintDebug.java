package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrintDebug extends Command {

	boolean run = false;
    public PrintDebug() {

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	run = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//outputDebug();
    	outputWhileHolding(Robot.oi.joystick);
    	run = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return run;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private void outputDebug(){
    	//System.out.println("---------------Debug : BEGIN --------");
    	//System.out.println("Encodeur: " + Robot.lanceur.getEncoderRaw());
    	System.out.println("Vitesse: " + Robot.lanceur.getSpeed());
    	//System.out.println("---------------Debug : END ---------");
    	//SmartDashboard.putString("mode", "TATA");
    }
    
    
    private void outputWhileHolding(Joystick j){
    	if(j.getRawButton(3)){
    		outputDebug();
    	}
    	
    	
    }
}
