package org.usfirst.frc.team4007.robot.subsystems;

import org.usfirst.frc.team4007.robot.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Bras extends Subsystem {
    
	public Solenoid sol1, sol2;
	
	public Bras(){
		super();
		sol1 = new Solenoid(2,0);
        sol2 = new Solenoid(2,7);
		
	}

    public void initDefaultCommand() {
	
    }
    
    
    public void monter(){
    	sol1.set(true);
    	sol2.set(true);
    }
    
    public void descendre(){
    	stop();
    	
    }
    
    public void stop(){
    	sol1.set(false);
    	sol2.set(false);
    	
    }
}


