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

    double

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
        
        int shaft = 6500; //rotations per minute 
        double wheelDiameter = 3.95; // in inches
        int maxRotationRate = 225; //degrees per second
        double gearRatio = 8.1428;
        int encoderPerRotation = 2048; //Number of encoder units per rotation
        int maxUnitsForPosition = 4096;
        
        double wheelCircumference = (wheelDiameter * Math.PI) /39.37; // Calculates the circumference using the diameter and converts to meters

        double rotationRatePosition  = (stickval[0]*(maxRotationRate) * (wheelCircumference));
                //Must move nomncalculated variable out of this class, which is repeated. Must make maxRotationRate & encoderper100ms
        double encoderPer100ms = (0.1 * gearRatio * encoderPerRotation) / wheelCircumference;
        double rotationRateVelocityX = encoderPer100ms * stickval[2];
        double rotationRateVelocityY = encoderPer100ms * stickval[3];

        double radianstoUnits = (maxUnitsForPosition * (2*Math.PI));       
        
        double vy =rotationRateVelocityY; 
        double vx =rotationRateVelocityX;  
        double omega=stickval[3]*rotationRatePosition;
        ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(vy, vx, omega,  new Rotation2d(0));
        double encPosition[] = new double[4];
        SwerveModuleState StatesOptimized[] = new SwerveModuleState[4];


        SwerveModuleState[] moduleStates = m_kinematics.toSwerveModuleStates(speeds);

        SwerveDriveKinematics.desaturateWheelSpeeds(moduleStates,19313);


        int i = 0;
        while (i<4){
        
     //   encPositionRad[i]=module[i].getTurnPosition_Rad();
        encPosition[i]=module[i].getRmotorpos();

        StatesOptimized[i] = module[i].optimize(moduleStates[i],encPosition[i]);
        i++;
        }

        double velocityEncoderUnits = radianstoUnits * encPosition[0];
       


      
        module[0].motormove(joystick.getjoyaxis()[3]*19313, joystick.getjoyaxis()[0]*4096);
        module[1].motormove(joystick.getjoyaxis()[3]*19313, joystick.getjoyaxis()[0]*4096);
        module[2].motormove(joystick.getjoyaxis()[3]*19313, joystick.getjoyaxis()[0]*4096);
        module[3].motormove(joystick.getjoyaxis()[3]*19313, joystick.getjoyaxis()[0]*4096);

    }


 
}
