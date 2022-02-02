package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;

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

 double encoderPer100ms = (0.1 * gearRatio * encoderPerRotation) / wheelCircumference;
 double radianstoUnits = (maxUnitsForPosition * (2*Math.PI));


public SwerveModule(int port1, int port2){
    Dmotor = new TalonFX(port1);
    Rmotor = new TalonFX(port2);

    Dmotor.configFactoryDefault();
    Rmotor.configFactoryDefault();
    
    Dmotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,25);
    Rmotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,25);
    Dmotor.config_kF(0, 0.052);
    
    Rmotor.config_kF(0,0);
    Rmotor.config_kP(0,0);
    Rmotor.config_kI(0,0);
    Rmotor.config_kD(0,0.5);



}


public void motormove(double Dvel, double Rpos){
    System.out.println("Dvel"+Dvel);
    
    double CorrectDvel = Dvel * encoderPer100ms; //converting the Dvel that is in meters per second to encoder units per second
    double CorrectRpos = Rpos * radianstoUnits; //converting the Rpos that is in radians to encoder units

    Dmotor.set(ControlMode.Velocity, CorrectDvel);
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
    double modulePosition = encPosition % 2;
    double goalPosition = moduleStates.angle.getRadians();
    
    double anglepath1 = (goalPosition - modulePosition);
    double anglepath2 = -(2 - anglepath1);
    double anglepath3 = (goalPosition-1) - modulePosition;

    if (Math.abs(anglepath1) < (anglepath2) && ( Math.abs(anglepath1) < (anglepath3)));
        double optimizedAngle = anglepath1;
        double optimizedVelocity = moduleVelocity*1;
    if (Math.abs(anglepath2) < (anglepath1) && (Math.abs(anglepath2) < (anglepath3)));
        optimizedAngle = anglepath2;
        optimizedVelocity = moduleVelocity*1;
    if (Math.abs(anglepath3) < (anglepath2) && (Math.abs(anglepath3) < (anglepath1)));
        optimizedAngle = anglepath3;
        optimizedVelocity = moduleVelocity*-1;

    return new SwerveModuleState(optimizedVelocity,new Rotation2d(optimizedAngle));
}



}

