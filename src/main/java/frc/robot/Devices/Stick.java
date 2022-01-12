// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


// Template for a customizable joystick class
// This extends the WPI Joystick class 

package frc.robot.Devices;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Stick extends SubsystemBase{
  private Joystick mystick;

  public Stick(){

  }


// need this to provide the Joystick instance to the button bindings
public Joystick getStick(){
  return this.mystick;
}
}