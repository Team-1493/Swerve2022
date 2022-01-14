package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Devices.Stick;

public class DriveSystem extends SubsystemBase{
    SwerveModule module = new SwerveModule();
    Stick joystick;

    public DriveSystem(Stick m_joystick){
        joystick=m_joystick;
    }
    

    public void controlmotors(){
        
        joystick.getjoyaxis();
        module.motormove(joystick.getjoyaxis()[3]*19313, 0);



    }
}
