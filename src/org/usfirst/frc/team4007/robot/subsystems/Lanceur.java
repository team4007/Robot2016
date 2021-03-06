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
	
	
	//private int sonarThreshold = 2500;
	public static double throwingSpeed = -1.0;
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
    	//currentRate = encoderMilieu.getRate();
    	isPreparingLaunch = true;
    	
    	//System.out.println("Throwing speed: " + -throwingSpeed);
    	essieuHaut.set(1);
    	essieuMilieu.set(throwingSpeed);
    	//previousRate = currentRate;
    }
    
    public void stopLancer(){
    	isPreparingLaunch = false;
    	essieuHaut.set(0);
    	essieuMilieu.set(0);
    }
    
    public void lancer(){
    	essieuBas.set(1);
    }
    
    /**
     * Permet de lancer le ballon dans le trou
     * du bas
     */
    public void eject(){
    	essieuBas.set(-1);
    	essieuMilieu.set(-throwingSpeed);
    }
    
    public void downStopLancer(){
    	essieuBas.set(0);
    }
    
    /**
     * Methode permettant de gober le ballon ou encore
     * d'envoyer le ballon vers les roues lanceuses
     */
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
    	if(throwingSpeed > -1.0){
    		throwingSpeed -= 0.1;
    		System.out.println("Throwing speed: " + -throwingSpeed);
    	}
    }
    
    public void decrementThrowingSpeed(){
    	if(throwingSpeed < -0.5){
    		throwingSpeed += 0.1;
    		System.out.println("Throwing speed: " + -throwingSpeed);
    	}
    }
}
    




