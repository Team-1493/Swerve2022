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
   Joystick Rjoystick = new Joystick(0);
   Joystick Ljoystick = new Joystick(1);
   
   double RjoystickX = Rjoystick.getX();
   double RjoystickY = Rjoystick.getY();
   double LjoystickX = Ljoystick.getX();
   double LjoystickY = Ljoystick.getY();
   
   


  public Stick(){}




  public double[] getjoyaxis(){

   double RjoystickX = Rjoystick.getX();
   double RjoystickY = Rjoystick.getY();
   double LjoystickX = Ljoystick.getX();
   double LjoystickY = Ljoystick.getY();


    
    if (Math.abs(RjoystickX) < 0.10) {
      RjoystickX = 0;
    }
    
    if (Math.abs(RjoystickY) < 0.10) {
      RjoystickY = 0;
    }
    
    if (Math.abs(LjoystickX) < 0.10) {
      LjoystickX = 0;
    }
    
    if (Math.abs(LjoystickY) < 0.10) {
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