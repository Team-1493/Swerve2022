package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveModule{
  //Drive Motor being defined
 
 //Rotation Motor being defined

 //Setting Drive Motor Defaults
 //This was causing an error, I'll complete setting defaults later
 
 int maxUnitsForPosition = 4096;
 double wheelDiameter = 3.95; // in inches
 double wheelCircumference = (wheelDiameter * Math.PI) /39.37;
 double gearRatio = 8.1428;
 int encoderPerRotation = 4096; //Number of encoder units per rotation

 TalonFX Dmotor;
 TalonFX Rmotor;
 CANCoder canCoder;
String tname="";
 double encoderPer100ms = (0.1 * gearRatio * encoderPerRotation) / wheelCircumference;
 double radianstoUnits = maxUnitsForPosition/ (2*Math.PI);


public SwerveModule(int port1, int port2, int port3)
{
    tname="turnMotor"+port2;
    Dmotor = new TalonFX(port1);
    Rmotor = new TalonFX(port2);
    canCoder = new CANCoder(port3);

    canCoder.configFactoryDefault();
    canCoder.configSensorDirection(false);



    Dmotor.configFactoryDefault();
    Rmotor.configFactoryDefault();
    
    Dmotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,25);

    Rmotor.configRemoteFeedbackFilter(canCoder, 0);
    Rmotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.RemoteSensor0, 0, 25);


   Dmotor.config_kF(0, 0.052); //original 0.052
    
    Rmotor.config_kF(0,0);  //original 0
    Rmotor.config_kP(0,0.4);    //original 0.5
    Rmotor.config_kI(0,0);  //original 0
    Rmotor.config_kD(0,0);  //original 0



}


public void motormove(double Dvel, double Rpos){

    
    double CorrectDvel = Dvel * encoderPer100ms; //converting the Dvel that is in meters per second to encoder units per second
    double CorrectRpos = Rpos * radianstoUnits; //converting the Rpos that is in radians to encoder units
    SmartDashboard.putNumber(tname+" CorrectRPos", CorrectRpos);
//Dmotor.set(ControlMode.Velocity, CorrectDvel);
   Rmotor.set(ControlMode.Position, CorrectRpos);
    


}

public double getDmotorvel(){
   double Dmotorvel = Dmotor.getSelectedSensorVelocity();
   return Dmotorvel;
}

public double getDmotorpos(){
    double Dmotorpos = Dmotor.getSelectedSensorPosition();
    return Dmotorpos;
}

public double getRmotorvel(){
    double Rmotorvel = Rmotor.getSelectedSensorVelocity();
    return Rmotorvel;
}

public double getRmotorpos(){
    double Rmotorpos = canCoder.getPosition();
    
    return Rmotorpos;
}

public SwerveModuleState optimize(SwerveModuleState moduleStates, double encPosition){
    double moduleVelocity = moduleStates.speedMetersPerSecond;
    double modulePosition = (encPosition * 180/Math.PI) % 360; 
    double goalPosition = moduleStates.angle.getDegrees();  
    double optimizedAngle=0;
    double optimizedVelocity=0;


    double angleDifference = (goalPosition - modulePosition);

    
    SmartDashboard.putNumber("goalPosition",goalPosition);
    SmartDashboard.putNumber("modulePosition",modulePosition);
    SmartDashboard.putNumber("moduleVelocity",moduleVelocity);

    SmartDashboard.putNumber("AngleDifference",angleDifference);

    if (Math.abs(angleDifference) <= 90){
        optimizedAngle = angleDifference;
        optimizedVelocity = Math.abs(moduleVelocity);
    }

    if (angleDifference > 90){
        optimizedAngle = (goalPosition - 180);
        optimizedVelocity =  -1 * Math.abs(moduleVelocity);
    }

    if (angleDifference < -90){
        optimizedAngle = (goalPosition + 180);
        optimizedVelocity =  -1 * Math.abs(moduleVelocity);
    }

    return new SwerveModuleState(optimizedVelocity,new Rotation2d((optimizedAngle)*(Math.PI/180)));
}



}

