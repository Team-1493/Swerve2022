package frc.robot.subsystems;
/**
 * @author Saugat
 */

import java.lang.reflect.Method;

//imports pigeon
import com.ctre.phoenix.sensors.PigeonIMU;
//imports Rotation2d
import edu.wpi.first.math.geometry.Rotation2d;
//creates instance of pigeon
public class gyro
{
   private PigeonIMU pigeon = new PigeonIMU(20);
    {
        //resets to default values
        PigeonIMU pigeon = new PigeonIMU(20);
        
        pigeon.configFactoryDefault();
        pigeon.setYaw(0);
        
        //gets the yaw angle
        double[] ypr_deg = new double[3];
        
        pigeon.getYawPitchRoll(ypr_deg);
        double yawAngle = ypr_deg[0];
          
    }
    //returns 2d object
    public Rotation2d getRotation2d(){
    return new Rotation2d(getAngleRadians()); 
    }
    //resets yaw angle to zero
    resetGyro(pigeon);
    private void resetGyro(PigeonIMU pigeon) {
        pigeon.configFactoryDefault();
        pigeon.setYaw(0);
    }
    
    
    public double getangle() 
    {
        double[] ypr_deg = new double[3];
        pigeon.getYawPitchRoll(ypr_deg);
        double yawAngle = ypr_deg[0]; 
        int ang;
        double outputval;
        double inputval;
        double absAng;
        ang = 360;
        
        inputval = yawAngle;
        outputval = inputval % ang;
        if (outputval <= 0) {
            absAng= outputval + 180; 
        }
        else {
            absAng = outputval - 180; 
        }
        return absAng;
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

