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
	public Encoder encoderMilieu;
	public Encoder encoderHaut;
	public boolean isFinishedSwallowing = false;
	public boolean isPreparingLaunch = false;
	
	
	private int sonarThreshold = 2500;
	public double throwingSpeed = -1;
	private double swallowingSpeed = .5;
	
	private boolean swallowerRunning = false;
	
	private long currentTime;
	private long previousTime;
	private int deltaTime;
	
	private double currentRate = 0;
	private double previousRate = 0;
	
	
	
	public Lanceur(){
		super();
		
		essieuHaut = new Jaguar (RobotMap.PWMEssieuHaut);
		essieuMilieu = new Jaguar (RobotMap.PWMEssieuMilieu);
		essieuBas = new Jaguar (RobotMap.PWMEssieuBas);
		sonar = new AnalogInput(3);
		
		encoderMilieu = new Encoder(0,1,false, EncodingType.k2X);
		encoderMilieu.setMinRate(.01);
    	encoderMilieu.setDistancePerPulse(0.2094);
    	encoderMilieu.reset();
    	
    	encoderHaut = new Encoder(2,3,false, EncodingType.k2X);
		encoderHaut.setMinRate(.01);
    	encoderHaut.setDistancePerPulse(0.2094);
    	encoderHaut.reset();
    	
    	currentTime = System.currentTimeMillis();
    	currentRate = encoderMilieu.getRaw();
	}
	
	public double getEncoderMilieuRate() {
			
		return encoderMilieu.getRate();
	}
	
	public double getEncoderHautRate() {
		
		return encoderHaut.getRate();
	}
	
	
	public double getSpeedHaut(){
		
		//return encoder.getDistance();
		return essieuHaut.getSpeed();
	}

	public double getSpeedMilieu(){
		
		//return encoder.getDistance();
		return essieuMilieu.getSpeed();
	}
	
	public double getSpeedBas(){
		
		//return encoder.getDistance();
		return essieuBas.getSpeed();
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
    	throwingSpeed = -1.0;
    	currentRate = encoderMilieu.getRate();
    	isPreparingLaunch = true;
    	
    	essieuHaut.set(1);
    	essieuMilieu.set(throwingSpeed);
    	previousRate = currentRate;
    }
    
    public void stopLancer(){
    	isPreparingLaunch = false;
    	essieuHaut.set(0);
    	essieuMilieu.set(0);
    }
    
    public void lancer(){
    	essieuBas.set(-1);
    }
    
    public void downLancer(){
    	essieuBas.set(-1);
    }
    
    public void downStopLancer(){
    	essieuBas.set(0);
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
    	essieuBas.set(swallowingSpeed);
    }
    
    public void stopGober(){
    	essieuBas.set(0);
    }
    
    public void setSwallowerSpeed(double speed){
    	essieuBas.set(speed);
    }
    
    public void incrementThrowingSpeed(){
    	if(isPreparingLaunch && throwingSpeed > -1.0 ){
    		throwingSpeed -= 0.1;
    	}
    	currentRate = encoderMilieu.getRate();
    	
    	essieuHaut.set(1);
    	essieuMilieu.set(throwingSpeed);
    	previousRate = currentRate;
    }
    
    public void decrementThrowingSpeed(){
    	if(isPreparingLaunch && throwingSpeed < -0.5){
    		throwingSpeed += 0.1;
    	}
    	currentRate = encoderMilieu.getRate();
    	
    	essieuHaut.set(1);
    	essieuMilieu.set(throwingSpeed);
    	previousRate = currentRate;
    }
}
    




