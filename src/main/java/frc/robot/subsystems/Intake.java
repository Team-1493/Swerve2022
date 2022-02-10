package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;


import frc.robot.Devices.Stick;

public class Intake{
    TalonFX LowerPositionMotor = new TalonFX(10);
    TalonFX UpperPositionMotor = new TalonFX(11);
    TalonFX IntakeMotor = new TalonFX(6);
    Joystick mystick = new Joystick(0);
    DigitalInput LowerPositionInput = new DigitalInput(0);
    DigitalInput UpperPositionInput = new DigitalInput(1);

    public Intake(){
    
        // Factory default
        LowerPositionMotor.configFactoryDefault();
        UpperPositionMotor.configFactoryDefault();
        IntakeMotor.configFactoryDefault();
        
        // Inverted - False
        LowerPositionMotor.setInverted(true);
        UpperPositionMotor.setInverted(true);
        IntakeMotor.setInverted(true);
    
        // Brake mode
        LowerPositionMotor.setNeutralMode(NeutralMode.Brake);
        UpperPositionMotor.setNeutralMode(NeutralMode.Brake);
        IntakeMotor.setNeutralMode(NeutralMode.Brake);
    
        // Peak output both forward and reverse in percentages
        // Forward
        LowerPositionMotor.configPeakOutputForward(1);
        UpperPositionMotor.configPeakOutputForward(1);
        IntakeMotor.configPeakOutputForward(1);
        // Reverse
        LowerPositionMotor.configPeakOutputReverse(-1);
        UpperPositionMotor.configPeakOutputReverse(-1);
        IntakeMotor.configPeakOutputReverse(-1);
    
        // Voltage Saturation
        LowerPositionMotor.configVoltageCompSaturation(10);
        UpperPositionMotor.configVoltageCompSaturation(10);
        IntakeMotor.configVoltageCompSaturation(10);
    
    
    }

    
    public void IntakeSystem() {

        if (mystick.getRawButton(1)) {
            IntakeMotor.set(ControlMode.PercentOutput, 0.5);
            LowerPositionMotor.set(ControlMode.PercentOutput, 0.5);
        }
        else{
            IntakeMotor.set(ControlMode.PercentOutput, 0);
            LowerPositionMotor.set(ControlMode.PercentOutput, 0.5);
        }

        if (mystick.getRawButton(2)) {
            UpperPositionMotor.set(ControlMode.PercentOutput, 0.5);
        }
        else {
            UpperPositionMotor.set(ControlMode.PercentOutput, 0.5);
        }


        //Check if there is a ball in the lower position
        if (LowerPositionInput.get()) {
            //check if there is one in the upper position

            //if there is also a ball in the upper position lower position motor will stop
            if (UpperPositionInput.get()) {
            LowerPositionMotor.set(ControlMode.PercentOutput, 0);
            }
            
            //if there is not a ball in the upper position run the motors until one makles it up there
            else {
                while (UpperPositionInput.get() == false)
                    LowerPositionMotor.set(ControlMode.PercentOutput, 0.5);
                    UpperPositionMotor.set(ControlMode.PercentOutput, 0.5);
            }
                
        }
        

}

}
                        