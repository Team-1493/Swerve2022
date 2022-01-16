package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class SwerveModule{

    
public SwerveModule(int drivePort, int turnPort){
    TalonFX motor1 = new TalonFX(drivePort);
    TalonFX motor2 = new TalonFX(turnPort);


    // Factory default
    motor1.configFactoryDefault();
    motor2.configFactoryDefault();

    // Inverted - False
    motor1.setInverted(false);
    motor2.setInverted(false);

    // Brake mode
    motor1.setNeutralMode(NeutralMode.Brake);
    motor2.setNeutralMode(NeutralMode.Brake);

    // Peak output both forward and reverse in percentages
    // Forward
    motor1.configPeakOutputForward(1);
    motor2.configPeakOutputForward(1);
    // Reverse
    motor1.configPeakOutputReverse(-1);
    motor2.configPeakOutputReverse(-1);

    // Voltage Saturation
    motor1.configVoltageCompSaturation(10);
    motor2.configVoltageCompSaturation(10);

    // Voltage Compensation
    motor1.configVoltageCompensation(true);
    motor2.configVoltageCompensation(true);
}

}
                        