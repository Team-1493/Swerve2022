package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Devices.Stick;

import com.ctre.phoenix.Util;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
public class DriveSystem extends SubsystemBase{
    SwerveModule[] module = new SwerveModule[4];
    
    int dfr = 0;
    int tfr = 1;
    int dfl = 2;
    int tfl = 3;
    int dbr = 4;
    int tbr = 5;
    int dbl = 6;
    int tbl = 7;

    float robotdim = 5;

    float speedMetersPerSecond;

    float maxVelocityMPS;

    public DriveSystem(Stick joystick){
        module[0] = new SwerveModule(dfr, tfr,?,?);
        module[1] = new SwerveModule(dfl, tfl,?,?);
        module[2] = new SwerveModule(dbr, tbr,?,?);
        module[3] = new SwerveModule(dbl, tbl,?,?);
        

        SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
            new Translation2d(robotdim, -robotdim),
            new Translation2d(robotdim, +robotdim),
            new Translation2d(-robotdim, -robotdim),
            new Translation2d(-robotdim, +robotdim)
        );
        ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(
            vy, vx, omega,  new Rotation2d(heading));

        SwerveModuleState[] moduleStates = m_kinematics.toSwerveModuleStates(speeds);

        
        SwerveDriveKinematics.desaturateWheelSpeeds(moduleStates,maxVelocityMPS);


        double[] encPositionRad = new double[4];
        SwerveModuleState[] moduleStatesOptimized = new SwerveModuleState[4];    
        double[] velocity = new double[4];   
        double[] turnAngle = new double[4];

        for (int i = 0; i < module.length; i++){
            encPositionRad[i] = module[i].getTurnPosition_Rad();
            // optimize module state to minimize the turn rotation needed
            moduleStatesOptimized[i] = optimize(moduleStates[i],encPositionRad[i]);
            velocity[i]=moduleStatesOptimized[i].speedMetersPerSecond;
            // get the turn motor's rotation setpoint radians
            turnAngle[i]=moduleStatesOptimized[i].angle.getRadians();
        }
    }


    public static SwerveModuleState optimize(SwerveModuleState moduleState, double desired){
        double current = moduleState.angle.getDegrees();
        double out;
        
        if (Math.abs(desired - current) < Math.abs(current - desired)){
            out = desired - current;
        }else{
            out = -(current - desired);
        }

        moduleState.angle = new Rotation2d(out);
        return moduleState;
    }
    
}
