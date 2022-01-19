package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;




public class SwerveModule{
    public TalonFX turn;
    public TalonFX drive;
    public CANCoder turnCoder;
    public CANCoder driveCoder;
    public SwerveModule(int driveio, int turnio, int drivecoderio, int turncoderio){
        turn = new TalonFX(driveio);
        drive = new TalonFX(turnio);
        driveCoder = new CANCoder(drivecoderio);
        turnCoder = new CANCoder(turncoderio);

        turn.configRemoteFeedbackFilter(turnCoder, 0,25);
        turn.configSelectedFeedbackSensor(TalonFXFeedbackDevice.RemoteSensor0,0,25);
        drive.configRemoteFeedbackFilter(driveCoder, 0,25);
        drive.configSelectedFeedbackSensor(TalonFXFeedbackDevice.RemoteSensor0,0,25);

    }

    public double getTurnPosition_Rad() {
        return turnCoder.getAbsolutePosition();
    }
}
                        