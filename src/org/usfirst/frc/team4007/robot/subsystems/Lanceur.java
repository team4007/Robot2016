package org.usfirst.frc.team4007.robot.subsystems;

import org.usfirst.frc.team4007.robot.RobotMap;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lanceur extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Jaguar essieuHaut;
	public Jaguar essieuMilieu;
	public Jaguar essieuBas;
	
	private double throwingSpeed = 1.0;
	private double swallowingSpeed = 1.0;
	
	
	public Lanceur(){
		super();
		essieuHaut = new Jaguar(RobotMap.PWMEssieuHaut);
		essieuMilieu = new Jaguar (RobotMap.PWMEssieuMilieu);
		essieuBas = new Jaguar (RobotMap.PWMEssieuBas);
		
	}
	
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void stop(){
    	essieuHaut.stopMotor();
    	essieuMilieu.stopMotor();
    	essieuBas.stopMotor();
    	
    }

    public void lancer(){
    	essieuHaut.set(throwingSpeed);
    	essieuMilieu.set(-throwingSpeed);
    }
    public void gober(){
    	essieuBas.set(swallowingSpeed);
    	
    }

}


