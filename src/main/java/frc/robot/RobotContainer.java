// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// This is Endri Hoxha
package frc.robot;


import frc.robot.Devices.Stick;
import frc.robot.commands.Drive;
import frc.robot.commands.IntakeShooter.IntakeBallToLower;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.IntakeConveyor;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;




public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Stick joystick = new Stick();
  private final DriveSystem drive = new DriveSystem(joystick);
  private final Drive DriveCommand = new Drive(drive);
  public final Shooter shooter = new Shooter();
  public final IntakeConveyor intake = new IntakeConveyor();
  public final Command m_intakeBalltoLower  = new IntakeBallToLower(intake);
 



  
  public RobotContainer() {
    drive.setDefaultCommand(DriveCommand);
    configureButtonBindings();
  }

  
  private void configureButtonBindings() {
    new JoystickButton(joystick.getStick(),14).whenPressed(m_intakeBalltoLower); 

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // define an auto command here
    Command m_autoCommand=null;
    return m_autoCommand;

  }
}
