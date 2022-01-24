package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;

public class SwerveModule{
  //Drive Motor being defined
 TalonFX Dmotor0 = new TalonFX(1);
 //Rotation Motor being defined
 TalonFX Rmotor0 = new TalonFX(2);

 //Setting Drive Motor Defaults
 //This was causing an error, I'll complete setting defaults later



public SwerveModule(int ports1, int port2){
    Dmotor0.configFactoryDefault();
    Rmotor0.configFactoryDefault();
    
    Dmotor0.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,25);
    Rmotor0.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,25);
    Dmotor0.config_kF(0, 0.052);
    
    Rmotor0.config_kF(0,0);
    Rmotor0.config_kP(0,0);
    Rmotor0.config_kI(0,0);
    Rmotor0.config_kD(0,0.5);
    
    int shaft = 6500; //rotations per minute 
    double wheelDiameter = 3.95;
    int maxRotationRate = 225; //degrees per second
    double gearRatio = 8.1428;
    int encoderPerRotation = 2048; //Number of encoder units per rotation






}


public void motormove(double Dvel, double Rpos){
    
    
    Dmotor0.set(ControlMode.Velocity, Dvel);
    Rmotor0.set(ControlMode.Position, Rpos);
    


}

public double getDmotorvel(){
   double Dmotorvel = Dmotor0.getSelectedSensorVelocity();
   return Dmotorvel;
}

public double getDmotorpos(){
    double Dmotorpos = Dmotor0.getSelectedSensorPosition();
    return Dmotorpos;
}

public double getRmotorvel(){
    double Rmotorvel = Rmotor0.getSelectedSensorVelocity();
    return Rmotorvel;
}

public double getRmotorpos(){
    double Rmotorpos = Rmotor0.getSelectedSensorPosition();
    //might need to convert to radians?
    return Rmotorpos;
}

public double getoptimizedpath(double goalpos){
    double Rmotorpos = getRmotorpos();
    double Rmotorangle = ((Rmotorpos/4096) * 360) % 360;
    
    double anglepath1 = (goalpos - Rmotorangle);
    double anglepath2 = -(360 - anglepath1);
    double anglepath3 = (goalpos-180) - Rmotorangle;

    if (Math.abs(anglepath1) < (anglepath2) && ( Math.abs(anglepath1) < (anglepath3)));
        double optimizedpath = anglepath1;
    
    if (Math.abs(anglepath2) < (anglepath1) && (Math.abs(anglepath2) < (anglepath3)));
        optimizedpath = anglepath2;

    if (Math.abs(anglepath3) < (anglepath2) && (Math.abs(anglepath3) < (anglepath1)));
        optimizedpath = anglepath3;

    return optimizedpath;
}



}

