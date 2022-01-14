//This is Jacob's Branch
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.Devices.Stick;
import frc.robot.commands.Drive;
import frc.robot.subsystems.DriveSystem;
import edu.wpi.first.wpilibj2.command.Command;




public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Stick joystick = new Stick();
  private final DriveSystem drive = new DriveSystem(joystick);
  private final Drive DriveCommand = new Drive(drive);

  
  public RobotContainer() {

    drive.setDefaultCommand(DriveCommand);

    // Configure the button bindings
    configureButtonBindings();
  }

  
  private void configureButtonBindings() {

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
