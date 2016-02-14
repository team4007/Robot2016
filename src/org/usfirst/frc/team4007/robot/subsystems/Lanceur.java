package org.usfirst.frc.team4007.robot.subsystems;

import org.usfirst.frc.team4007.robot.RobotMap;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Compressor;


/**
 *
 */
public class Lanceur extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Jaguar essieuHaut;
	public Jaguar essieuMilieu;
	public Jaguar essieuBas;
	public AnalogInput sonar;
	public Encoder encoder;
	public boolean isFinishedSwallowing = false;
	
	private int sonarThreshold = 2500;
	private double throwingSpeed = 1;
	private double swallowingSpeed = .5;
	
	private boolean swallowerRunning = false;
	
	private long currentTime;
	private long previousTime;
	private int deltaTime;
	
	private int currentEncodeur;
	private int previousEncodeur;
	
	
	public Lanceur(){
		super();
		
		essieuHaut = new Jaguar (RobotMap.PWMEssieuHaut);
		essieuMilieu = new Jaguar (RobotMap.PWMEssieuMilieu);
		essieuBas = new Jaguar (RobotMap.PWMEssieuBas);
		sonar = new AnalogInput(3);
		
		encoder = new Encoder(0,1,false, EncodingType.k2X);
		encoder.setMinRate(.01);
    	encoder.setDistancePerPulse(0.2094);
    	encoder.reset();
    	
    	currentTime = System.currentTimeMillis();
    	currentEncodeur = encoder.getRaw();
	}
	
	public int getEncoderRaw() {
			
		return currentEncodeur;
	}
	
	public double getSpeed(){
		
		//return encoder.getDistance();
		return encoder.getRate();
	}
	


	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//stop();
    }
    
    //NE PAS APPELLER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    /*public void stop(){
    	essieuHaut.stopMotor();
    	essieuMilieu.stopMotor();
    	essieuBas.stopMotor();
    	
    	swallowerRunning = false;
    	
    }*/

    public void preparerLancer(){
    	essieuHaut.set(-1);
    	essieuMilieu.set(throwingSpeed);
    }
    public void stopLancer(){
    	essieuHaut.set(0);
    	essieuMilieu.set(0);
    }
    
    public void lancer(){
    	essieuBas.set(-1);
    }
    
    public void gober(){
    	
    	/*if(sonar.getValue() < sonarThreshold){
    		isFinishedSwallowing = false;
    		essieuBas.set(-swallowingSpeed);
    	}else{
    		isFinishedSwallowing = true;
    		essieuBas.set(1);
    		essieuMilieu.set(-1);
    		Timer.delay(0.01);
    		essieuBas.set(0);
    		essieuMilieu.set(0);
    	}*/
		
		//System.out.println("Gobeur active!");
    	essieuBas.set(-1);
    }
    /*public void stopGober(){
    	essieuBas.set(0);
    }*/
    
    public void setSwallowerSpeed(double speed){
    	essieuBas.set(speed);
    }
}
    




