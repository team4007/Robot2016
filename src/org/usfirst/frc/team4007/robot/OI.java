package org.usfirst.frc.team4007.robot;


import org.usfirst.frc.team4007.robot.commands.Camera;
import org.usfirst.frc.team4007.robot.commands.DecrementThrowingSpeed;
import org.usfirst.frc.team4007.robot.commands.DownThrow;
import org.usfirst.frc.team4007.robot.commands.IncrementThrowingSpeed;
import org.usfirst.frc.team4007.robot.commands.LiftUp;
import org.usfirst.frc.team4007.robot.commands.PrepareLift;
import org.usfirst.frc.team4007.robot.commands.PrintDebug;
import org.usfirst.frc.team4007.robot.commands.StopDownThrow;
import org.usfirst.frc.team4007.robot.commands.StopSwallow;
import org.usfirst.frc.team4007.robot.commands.StopThrow;
import org.usfirst.frc.team4007.robot.commands.Swallow;
import org.usfirst.frc.team4007.robot.commands.SwitchDrive;
import org.usfirst.frc.team4007.robot.commands.Throw;
import org.usfirst.frc.team4007.robot.triggers.DoubleButton;
import org.usfirst.frc.team4007.robot.commands.PrepareThrow;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {


	public Joystick joystick;
	
//	public Camera cam;
	public PrintDebug pd;

	public OI() {
		joystick = new Joystick(0);

		pd = new PrintDebug();

		//xbox mapping
		JoystickButton jbA = new JoystickButton(joystick, 1),
				jbB = new JoystickButton(joystick, 2),
				jbX = new JoystickButton(joystick, 3),
				jbY = new JoystickButton(joystick, 4),
				jbLB = new JoystickButton(joystick, 5),
				jbRB = new JoystickButton(joystick, 6),
				jbBACK = new JoystickButton(joystick, 7),
				jbSTART = new JoystickButton(joystick, 8),
				jbLS = new JoystickButton(joystick, 9),
				jbRS = new JoystickButton(joystick, 10);
		
		new DoubleButton(joystick, 2, 3).whenActive(new DownThrow());
		new DoubleButton(joystick, 2, 3).whenInactive(new StopDownThrow());
		
		new DoubleButton (joystick, 1, 3).whenActive(new SwitchDrive());
		
		
		
		
		
		
		jbLB.whenActive(new Swallow()); 
		jbLB.whenInactive(new StopSwallow());
		
		jbRB.whenActive(new PrepareThrow());
		jbRB.whenInactive(new StopThrow());
		
		jbSTART.whenActive(new PrepareLift());
		//jbSTART.whenInactive(new StopPrepareLift());
		
		jbBACK.whenActive(new LiftUp());
		//jbBACK.whenInactive(new StopLiftUp());
		
		/*jbA.whenActive(new Throw());
		jbA.whenInactive(new StopThrow());*/
		
		jbLS.whenActive(new PrintDebug());
		
		jbY.whenReleased(new IncrementThrowingSpeed());
		jbA.whenReleased(new DecrementThrowingSpeed());
	}

	
}

