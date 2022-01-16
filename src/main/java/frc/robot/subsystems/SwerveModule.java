package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class SwerveModule{

    
public SwerveModule(int drivePort, int turnPort){
    TalonFX DriveMotor = new TalonFX(drivePort);
    TalonFX TurnMotor = new TalonFX(turnPort);


    // Factory default
    DriveMotor.configFactoryDefault();
    TurnMotor.configFactoryDefault();

    // Inverted - False
    DriveMotor.setInverted(false);
    TurnMotor.setInverted(false);

    // Brake mode
    DriveMotor.setNeutralMode(NeutralMode.Brake);
    TurnMotor.setNeutralMode(NeutralMode.Brake);

    // Peak output both forward and reverse in percentages
    // Forward
    DriveMotor.configPeakOutputForward(1);
    TurnMotor.configPeakOutputForward(1);
    // Reverse
    DriveMotor.configPeakOutputReverse(-1);
    TurnMotor.configPeakOutputReverse(-1);

    // Voltage Saturation
    DriveMotor.configVoltageCompSaturation(10);
    TurnMotor.configVoltageCompSaturation(10);

    // Voltage Compensation
    DriveMotor.configVoltageCompensation(true);
    TurnMotor.configVoltageCompensation(true);
}

}
                        