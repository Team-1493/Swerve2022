package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.Joystick;


import frc.robot.Devices.Stick;

public class Intake{
    TalonFX BottomIntakeMotor = new TalonFX(0);
    TalonFX MiddleIntakeMotor = new TalonFX(1);
    Joystick mystick = new Joystick(0);


    public Intake(){
    
        // Factory default
        BottomIntakeMotor.configFactoryDefault();
        MiddleIntakeMotor.configFactoryDefault();
        
        // Inverted - False
        BottomIntakeMotor.setInverted(false);
        MiddleIntakeMotor.setInverted(false);
    
        // Brake mode
        BottomIntakeMotor.setNeutralMode(NeutralMode.Brake);
        MiddleIntakeMotor.setNeutralMode(NeutralMode.Brake);
    
        // Peak output both forward and reverse in percentages
        // Forward
        BottomIntakeMotor.configPeakOutputForward(1);
        MiddleIntakeMotor.configPeakOutputForward(1);
        // Reverse
        BottomIntakeMotor.configPeakOutputReverse(-1);
        MiddleIntakeMotor.configPeakOutputReverse(-1);
    
        // Voltage Saturation
        BottomIntakeMotor.configVoltageCompSaturation(10);
        MiddleIntakeMotor.configVoltageCompSaturation(10);
    
        // Voltage Compensation
        BottomIntakeMotor.configVoltageCompensation(true);
        MiddleIntakeMotor.configVoltageCompensation(true);
    
    }

    
    public void IntakeSystem() {

        if (mystick.getRawButton(1)) {
        BottomIntakeMotor.set(TalonFXControlMode.PercentOutput,0.5);
        MiddleIntakeMotor.set(TalonFXControlMode.PercentOutput,0.5);
        }
        else {
        BottomIntakeMotor.set(TalonFXControlMode.PercentOutput,0);
        MiddleIntakeMotor.set(TalonFXControlMode.PercentOutput,0);
        }
}

}
                        