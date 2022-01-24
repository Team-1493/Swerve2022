package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    
    int dfr = 1;
    int tfr = 2;
    int dfl = 3;
    int tfl = 4;
    int dbr = 5;
    int tbr = 6;
    int dbl = 7;
    int tbl = 8;

    float robotdim = 5;

    float speedMetersPerSecond;

    float maxVelocityMPS = 10;

    int cfr = 9;
    int cfl = 10;
    int cbr = 11;
    int cbl = 12;

    Stick mJoystick;


    double omegaScale = 1;

    public double heading = 0;

    SwerveDriveKinematics m_kinematics;

    public DriveSystem(Stick joystick){
        module[0] = new SwerveModule(dfr, tfr,cfr);
        module[1] = new SwerveModule(dfl, tfl,cfl);
        module[2] = new SwerveModule(dbr, tbr,cbr);
        module[3] = new SwerveModule(dbl, tbl,cbl);
        
        m_kinematics = new SwerveDriveKinematics(
            new Translation2d(robotdim, -robotdim),
            new Translation2d(robotdim, +robotdim),
            new Translation2d(-robotdim, -robotdim),
            new Translation2d(-robotdim, +robotdim)
        );
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
        double omega = rotation * omegaScale;//placeholder
        heading += omega; 
        SmartDashboard.putNumber("pos 0",pos[0]);
        SmartDashboard.putNumber("pos 1",pos[1]);

        ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(
            pos[1], pos[0], omega,  new Rotation2d(heading));

        SwerveModuleState[] moduleStates = m_kinematics.toSwerveModuleStates(speeds);
        SmartDashboard.putNumber("speed 1", moduleStates[1].speedMetersPerSecond);

        
        SwerveDriveKinematics.desaturateWheelSpeeds(moduleStates,maxVelocityMPS);
        SmartDashboard.putNumber("sat speed 1", moduleStates[1].speedMetersPerSecond);


        double[] encPositionRad = new double[4];
        SwerveModuleState[] moduleStatesOptimized = new SwerveModuleState[4];    
        double[] velocity = new double[4];   
        double[] turnAngle = new double[4];

        for (int i = 0; i < module.length; i++){
            encPositionRad[i] = module[i].getTurnPosition_Rad();
            // optimize module state to minimize the turn rotation needed
            moduleStatesOptimized[i] = optimize(moduleStates[i],encPositionRad[i]);
            velocity[i]=moduleStatesOptimized[i].speedMetersPerSecond * 5290;
            // get the turn motor's rotation setpoint radians
            turnAngle[i]=moduleStatesOptimized[i].angle.getRadians();
            System.out.println("vel set "+i+"   "+velocity[i]);
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
