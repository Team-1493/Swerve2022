package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;


public class SwerveModule{
  //Drive Motor being defined
 TalonFX Dmotor0 = new TalonFX(1);
 //Rotation Motor being defined
 TalonFX Rmotor0 = new TalonFX(2);

 //Setting Drive Motor Defaults
 //This was causing an error, I'll complete setting defaults later



public SwerveModule(){
    Dmotor0.configFactoryDefault();
    Rmotor0.configFactoryDefault();
    Dmotor0.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,25);
    //    Rmotor0.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,25);
    Dmotor0.config_kF(0, 0.052);
}


public void motormove(double Dvel, double Rpos){
    Dmotor0.set(ControlMode.Velocity, Dvel);
//    Rmotor0.set(ControlMode.Position, Rpos);



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
    return Rmotorpos;
}



}

