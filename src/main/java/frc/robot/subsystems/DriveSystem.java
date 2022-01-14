package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Devices.Stick;

public class DriveSystem extends SubsystemBase{
    SwerveModule module1 = new SwerveModule(1,2);
    SwerveModule module2 = new SwerveModule(3,4);
    SwerveModule module3 = new SwerveModule(5,6);
    SwerveModule module4 = new SwerveModule(7,8);

    public DriveSystem(Stick joystick){

    }
    
}
