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
        double absAng = getangle(yawAngle);
        getOutPutVal(yawAngle);
        getAngleRadians(absAng);
        getTemp();  
          
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
    
    
    public double getangle(double yawAngle) 
    {
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
    
    public double getOutPutVal(double yawAngle) 
    {
        int ang;
        double outputval;
        double inputval;       
        ang = 360;
        
        inputval = yawAngle;
        outputval = inputval % ang;
        
        return outputval;         
    }
        //gets gyro temp
    public double getTemp(){
            double temp=pigeon.getTemp();
            return temp; 
                                }
    
        //Returns yaw angle in radians
    public double getAngleRadians(double absAng){
        double absAngRad = (absAng*3.14)/180;
        return absAngRad;
    } 
}

