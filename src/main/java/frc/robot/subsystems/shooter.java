package frc.robot.subsystems;
 
    import com.ctre.phoenix.motorcontrol.can.TalonFX;
 
public class shooter{
    
    public shooter(int shooterPort){
        TalonFX ShooterMotor = new TalonFX(shooterPort);
    }


   
}  
