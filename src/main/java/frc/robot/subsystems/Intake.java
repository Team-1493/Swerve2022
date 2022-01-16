package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.Devices.Stick;

public class Intake{



    public Intake(){
        TalonFX intake_motor1 = new TalonFX();
        TalonFX intake_motor2 = new TalonFX();
    
        // Factory default
        intake_motor1.configFactoryDefault();
        intake_motor2.configFactoryDefault();
    
        // Inverted - False
        intake_motor1.setInverted(false);
        intake_motor2.setInverted(false);
    
        // Brake mode
        intake_motor1.setNeutralMode(NeutralMode.Brake);
        intake_motor2.setNeutralMode(NeutralMode.Brake);
    
        // Peak output both forward and reverse in percentages
        // Forward
        intake_motor1.configPeakOutputForward(1);
        intake_motor2.configPeakOutputForward(1);
        // Reverse
        intake_motor1.configPeakOutputReverse(-1);
        intake_motor2.configPeakOutputReverse(-1);
    
        // Voltage Saturation
        intake_motor1.configVoltageCompSaturation(10);
        intake_motor2.configVoltageCompSaturation(10);
    
        // Voltage Compensation
        intake_motor1.configVoltageCompensation(true);
        intake_motor2.configVoltageCompensation(true);
    
    }

    
    public void IntakeSystem() {
        intake_motor1.set(TalonFXControlMode.PercentOutput,0.5)
        intake_motor2.set(TalonFXControlMode.PercentOutput,0.5)
}

}
                        