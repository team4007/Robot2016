package org.usfirst.frc.team4007.robot;

import org.usfirst.frc.team4007.robot.commands.LiftUp;
import org.usfirst.frc.team4007.robot.commands.PrepareLift;
import org.usfirst.frc.team4007.robot.commands.PrintDebug;
import org.usfirst.frc.team4007.robot.commands.StopLiftUp;
import org.usfirst.frc.team4007.robot.commands.StopPrepareLift;
import org.usfirst.frc.team4007.robot.commands.StopThrow;
import org.usfirst.frc.team4007.robot.commands.Swallow;
import org.usfirst.frc.team4007.robot.commands.Throw;
import org.usfirst.frc.team4007.robot.commands.PrepareThrow;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {


	public Joystick joystick;
	
	public OI() {
		joystick = new Joystick(0);


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
		
		jbLB.whenActive(new Swallow()); 
		
		jbSTART.whenActive(new PrepareLift());
		jbSTART.whenInactive(new StopPrepareLift());
		
		jbBACK.whenActive(new LiftUp());
		jbBACK.whenInactive(new StopLiftUp());
		
		jbRB.whenActive(new PrepareThrow());
		jbRB.whenInactive(new StopThrow ());
		
		jbA.whenActive(new Throw());
		jbA.whenInactive(new StopThrow());
		
		jbY.whenActive(new PrintDebug());
	}

	
}

