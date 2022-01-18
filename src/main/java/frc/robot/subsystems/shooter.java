package frc.robot.subsystems;
 
    import com.ctre.phoenix.motorcontrol.can.TalonFX;
 
public class shooter{
    
    TalonFX shooterMotor = new
TalonFX(10);
    public shooter(){

        shooterMotor.configFactoryDefault();
        shooterMotor.setInverted(false);
        shooterMotor.setNeutralMode(NeutralMode.Brake);
        shooterMotor.configPeakOutputForward(1);
        shooterMotor.configPeakOutputReverse(-1);
        shooterMotor.configVoltageCompensation(10);
        shooterMotor.enableVoltageCompensation(true);

        
    }
   
}  
