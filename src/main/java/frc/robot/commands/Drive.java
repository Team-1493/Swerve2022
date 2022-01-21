// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;

/** An example command that uses an example subsystem. */
public class Drive extends CommandBase {
  private DriveSystem m_driveSystem;
  Supplier<double[]> m_stickState;
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})




  public Drive(DriveSystem driveSystem, Supplier<double[]> stickState) {
 
    m_driveSystem = driveSystem;
    m_stickState = stickState;

    addRequirements(driveSystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
       m_driveSystem.Drive();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   // m_swervedrive.setMotors(0.,0.,0.,0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  
}
