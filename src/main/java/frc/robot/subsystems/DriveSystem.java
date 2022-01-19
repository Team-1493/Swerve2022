package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Devices.Stick;

public class DriveSystem extends SubsystemBase{

    SwerveModule[] module = new SwerveModule[4];

    
    Stick joystick;

    public DriveSystem(Stick m_joystick){
        module[0] = new SwerveModule(1,2);
        module[1] = new SwerveModule(3,4);
        module[2] = new SwerveModule(5,6);
        module[3] = new SwerveModule(7,8);


        joystick=m_joystick;
    }
    

    public void controlmotors(){
        
        joystick.getjoyaxis();
       module[0].motormove(joystick.getjoyaxis()[3]*19313, joystick.getjoyaxis()[0]*20480);
       module[1].motormove(joystick.getjoyaxis()[3]*19313, joystick.getjoyaxis()[0]*20480);
       module[2].motormove(joystick.getjoyaxis()[3]*19313, joystick.getjoyaxis()[0]*20480);
       module[3].motormove(joystick.getjoyaxis()[3]*19313, joystick.getjoyaxis()[0]*20480);


    }
}
