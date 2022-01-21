package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Devices.Stick;

import java.util.ArrayList;

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

    int cfr = 8;
    int cfl = 9;
    int cbr = 10;
    int cbl = 11;

    Stick mJoystick;


    double omegaScale = 1;

    public double heading = 0;

    
    public DriveSystem(Stick joystick){
        module[0] = new SwerveModule(dfr, tfr,cfr);
        module[1] = new SwerveModule(dfl, tfl,cfl);
        module[2] = new SwerveModule(dbr, tbr,cbr);
        module[3] = new SwerveModule(dbl, tbl,cbl);
        mJoystick = joystick;
    }
    public void Drive(double[] pos,double rotation){
        DriveInput(pos, rotation);
    }
    public void Drive(){
        double[] array = {mJoystick.getStick().getX(),mJoystick.getStick().getY()};
        DriveInput(array, mJoystick.getStick().getZ());
    }
    void DriveInput(double[] pos,double rotation){
        SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
            new Translation2d(robotdim, -robotdim),
            new Translation2d(robotdim, +robotdim),
            new Translation2d(-robotdim, -robotdim),
            new Translation2d(-robotdim, +robotdim)
        );
        double omega = rotation * omegaScale;//placeholder
        heading += omega; 
        ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(
            pos[1], pos[0], omega,  new Rotation2d(heading));

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
            module[i].drive.set(TalonFXControlMode.Velocity, velocity[i]);
            module[i].turn.set(TalonFXControlMode.Position, turnAngle[i]);

        }
    }


    public static SwerveModuleState optimize(SwerveModuleState moduleState, double desired){
        double current = moduleState.angle.getDegrees();
        double out;
        
        if (Math.abs(desired - current) > 180){
            if (current < desired){
                //overflow negative
                double overflowamount = -current;
                out = overflowamount - (360 - desired);
            }else{
                //overflow positive
                double overflowamount = 360 - current;
                out = overflowamount + desired;
            }
        }else{
            out = desired - current;
        }

        moduleState.angle = new Rotation2d(out);
        return moduleState;
    }
    
}
