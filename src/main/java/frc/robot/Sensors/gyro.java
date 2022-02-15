package frc.robot.Sensors;
/**
 * @author Saugat
 */

import java.lang.reflect.Method;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.math.geometry.Rotation2d;

public class Gyro
{
   private PigeonIMU pigeon = new PigeonIMU(20);
    {
        //resets to default values
        PigeonIMU pigeon = new PigeonIMU(20);
        pigeon.configFactoryDefault();
        pigeon.setYaw(0);
    }
   
    //returns 2d object
    public Rotation2d getRotation2d(){
    return new Rotation2d(getAngleRadians()); 
    }
   
    //resets yaw angle to zero
    private void resetGyro() {
        pigeon.configFactoryDefault();
        pigeon.setYaw(0);
    }
    
    public double getangle() 
    {
        double[] ypr_deg = new double[3];
        pigeon.getYawPitchRoll(ypr_deg);
        double yawAngle = ypr_deg[0]; 
        if (yawAngle > 180) yawAngle=yawAngle-360;
        else if (yawAngle <-180) yawAngle = yawAngle + 360;
        return yawAngle;
    }

//gets gyro temp
    public double getTemp(){
        double temp=pigeon.getTemp();
        return temp; 
    }
    
 //Returns yaw angle in radians
    public double getAngleRadians(){
        double AngRad = (getangle()*Math.PI)/180;
    return AngRad;
    } 
}

