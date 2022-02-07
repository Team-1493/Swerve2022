package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
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
 int encoderPerRotation = 2048; //Number of encoder units per rotation

 TalonFX Dmotor;
 TalonFX Rmotor;
String tname="";
 double encoderPer100ms = (0.1 * gearRatio * encoderPerRotation) / wheelCircumference;
 double radianstoUnits = maxUnitsForPosition/ (2*Math.PI);


public SwerveModule(int port1, int port2)
{
    tname="turnMotor"+port2;
    Dmotor = new TalonFX(port1);
    Rmotor = new TalonFX(port2);

    Dmotor.configFactoryDefault();
    Rmotor.configFactoryDefault();
    
    Dmotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,25);
    Rmotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,25);
    Dmotor.config_kF(0, 0.052); //original 0.052
    
    Rmotor.config_kF(0,0);  //original 0
    Rmotor.config_kP(0,0.003);    //original 0.5
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
    double Rmotorpos = Rmotor.getSelectedSensorPosition();
    //might need to convert to radians?
    return Rmotorpos;
}

public SwerveModuleState optimize(SwerveModuleState moduleStates, double encPosition){
    double moduleVelocity = moduleStates.speedMetersPerSecond;
    double goalPosition = (encPosition * 180/Math.PI) % 360;  //Was module position
    double modulePosition = moduleStates.angle.getDegrees();  //Was goal position
    
    double anglepath1 = Math.abs((Math.abs(goalPosition - modulePosition)-180));
    SmartDashboard.putNumber("anglepath1",anglepath1);

    if (anglepath1 < 91);
        double optimizedAngle = anglepath1;
        double optimizedVelocity = Math.abs(moduleVelocity);
    if (anglepath1 > 90);
        optimizedAngle = anglepath1 + 180;
        optimizedVelocity = -1 * (Math.abs(moduleVelocity));

    return new SwerveModuleState(optimizedVelocity,new Rotation2d(optimizedAngle));
}



}

