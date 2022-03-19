package frc.robot.Subsystems;

import frc.robot.RobotSubsystems;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Lift extends RobotSubsystems {
    /* 
    The components relating to ONLY the Drive subsystem 
    should be defined as class variables here 
    */
    public static VictorSPX liftMotor;

    // Constructors
    public Lift() {
        robotInit();
    }

    public void robotInit() {
        //.getProperty("name in config file", "backup value if config file not found or variable not found")
        int liftMotorCanID = Integer.parseInt(
            frc.robot.Robot.RobotConfiguration.getProperty("lift_motor_canid", "6"));

        liftMotor = new VictorSPX(liftMotorCanID);
    }

    @Override
    public void teleopPeriodic() {
        if(frc.robot.Robot.driverTwo.getXButton())  {
            liftMotor.set(VictorSPXControlMode.PercentOutput, 0.7);
        }   else if(frc.robot.Robot.driverTwo.getYButton()) {
            liftMotor.set(VictorSPXControlMode.PercentOutput, -0.7);
        }   else    {
            liftMotor.set(VictorSPXControlMode.PercentOutput, 0);
        }
    }
}