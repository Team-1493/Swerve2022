package frc.robot.subsystems;

import com.ctre.phoenix.Util;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Devices.Stick;

public class DriveSystem extends SubsystemBase{

    SwerveModule[] module = new SwerveModule[4];

    SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
    new Translation2d(0.4064, -0.4064),
    new Translation2d(0.4064, +0.4064),
    new Translation2d(-0.4064, -0.4064),
    new Translation2d(-0.4064, +0.4064));

    







    
    Stick joystick;

    public DriveSystem(Stick m_joystick){
        module[0] = new SwerveModule(1,2);
        module[1] = new SwerveModule(3,4);
        module[2] = new SwerveModule(5,6);
        module[3] = new SwerveModule(7,8);


        joystick=m_joystick;
    }
    

    public void controlmotors(){
        double stickval[] = joystick.getjoyaxis();
        double vy =stickval[1]*19313; 
        double vx =stickval[2]*19313;  
        double omega=stickval[3]*4; // max rotation rate
        ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(vy, vx, omega,  new Rotation2d(0));
        double encPositionRad[] = new double[4];

        SwerveModuleState[] moduleStates = m_kinematics.toSwerveModuleStates(speeds);

        SwerveDriveKinematics.desaturateWheelSpeeds(moduleStates,19313);

        int i = 0;
        while (i<4){
        
     //   encPositionRad[i]=module[i].getTurnPosition_Rad();
        encPositionRad[i]=module[i].getRmotorpos();

     //   module.StatesOptimized[i]=Util.optimize(moduleStates[i],encPositionRad[i]); // Create the StatesOptimized array within the drive system (no .module)

        i++;
        }


       int rotationRate  = (stickval[3]*(maxRotationRate * (wheelDiameter * 2)))*(Math.PI/180);

       int wheelCircumference = (wheelDiameter * Math.PI) /39.37;

       int encoderPer100ms = (0.1 * gearRatio * encoderPerRotation) / wheelCircumference;
     
       
       



      
        module[0].motormove(joystick.getjoyaxis()[3]*19313, joystick.getjoyaxis()[0]*4096);
        module[1].motormove(joystick.getjoyaxis()[3]*19313, joystick.getjoyaxis()[0]*4096);
        module[2].motormove(joystick.getjoyaxis()[3]*19313, joystick.getjoyaxis()[0]*4096);
        module[3].motormove(joystick.getjoyaxis()[3]*19313, joystick.getjoyaxis()[0]*4096);

    }


 
}
