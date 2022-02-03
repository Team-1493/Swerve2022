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
   Joystick joystick = new Joystick(0);
   
   double RjoystickX = joystick.getRawAxis(2);
   double RjoystickY = joystick.getRawAxis(3);
   double LjoystickX = joystick.getRawAxis(0);
   double LjoystickY = joystick.getRawAxis(1);
   


  public Stick(){}




  public double[] getjoyaxis(){

    double RjoystickX = joystick.getRawAxis(2);
    double RjoystickY = joystick.getRawAxis(3);
    double LjoystickX = joystick.getRawAxis(0);
    double LjoystickY = joystick.getRawAxis(1);

    

    if (Math.abs(RjoystickX) < 0.05) {
      RjoystickX = 0;
    }
    
    if (Math.abs(RjoystickY) < 0.05) {
      RjoystickY = 0;
    }
    
    if (Math.abs(LjoystickX) < 0.05) {
      LjoystickX = 0;
    }
    
    if (Math.abs(LjoystickY) < 0.05) {
        LjoystickY = 0;
    }
    
    double[] joyaxis={RjoystickX, RjoystickY, LjoystickX, LjoystickY};
    return joyaxis;
  }
  



// need this to provide the Joystick instance to the button bindings
public Joystick getStick(){
  return this.mystick;
}



}