package org.usfirst.frc.team4007.robot.subsystems;

import org.usfirst.frc.team4007.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Bras extends Subsystem {
    
	public DoubleSolenoid sol1, sol2;
	
	public Bras(){
		super();
		sol1 = new DoubleSolenoid(0,1);
        sol2 = new DoubleSolenoid(6,7);
		
	}

    public void initDefaultCommand() {
	
    }
    
    
    public void monter(){
    	sol1.set(DoubleSolenoid.Value.kForward);
    	sol2.set(DoubleSolenoid.Value.kForward);
    }
    
    public void descendre(){
    	sol1.set(DoubleSolenoid.Value.kReverse);
    	sol2.set(DoubleSolenoid.Value.kReverse);
    	
    }
    
    public void stop(){
    	sol1.set(DoubleSolenoid.Value.kOff);
    	sol2.set(DoubleSolenoid.Value.kOff);
    	
    }
}


