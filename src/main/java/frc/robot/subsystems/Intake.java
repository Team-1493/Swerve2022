package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class Intake{

    TalonFX intake_motor1 = new TalonFX();
    TalonFX intake_motor2 = new TalonFX();
    
public void teleopPeriodic() {
    intake_motor1.set(TalonFXControlMode.PercentOutput)
    intake_motor2.set(TalonFXControlMode.PercentOutput)
}

}
                        