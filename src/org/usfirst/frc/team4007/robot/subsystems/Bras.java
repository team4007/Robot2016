package org.usfirst.frc.team4007.robot.subsystems;

import org.usfirst.frc.team4007.robot.RobotMap;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Bras extends Subsystem {
    
	public Jaguar moteurBras;
	
	public Bras(){
		super();
		moteurBras = new Jaguar (RobotMap.PWMBras);
		
	}

    public void initDefaultCommand() {

    	
    }
    
    
    public void monterBras(){
    	moteurBras.set(-1);
    }
    
    public void descendreBras(){
    	moteurBras.set(1);
    }
    
    public void stop(){
    	moteurBras.stopMotor();
    }
}


