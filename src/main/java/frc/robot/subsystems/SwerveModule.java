package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class SwerveModule{

    
public SwerveModule(int drivePort, int turnPort){
    TalonFX motor1 = new TalonFX(drivePort);
    TalonFX motor2 = new TalonFX(turnPort);

}

}
                        