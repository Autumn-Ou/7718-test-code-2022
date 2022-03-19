package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.RobotSubsystems;

public class Drive extends RobotSubsystems {
    /* 
    The components relating to ONLY the Drive subsystem 
    should be defined as class variables here 
    */
    public static VictorSPX rightFront;
    public static VictorSPX leftFront;
    public static VictorSPX rightBack;
    public static VictorSPX leftBack;

    // Constructors
    public Drive() {
        robotInit();
    }

    public void robotInit() {
        //.getProperty("name in config file", "backup value if config file not found or variable not found")
        int rightFrontCanID = Integer.parseInt(
            frc.robot.Robot.RobotConfiguration.getProperty("drive_right_front_canid", "0"));
        int leftFrontCanID = Integer.parseInt(
            frc.robot.Robot.RobotConfiguration.getProperty("drive_left_front_canid", "1"));
        int rightBackCanID = Integer.parseInt(
            frc.robot.Robot.RobotConfiguration.getProperty("drive_right_back_canid", "2"));
        int leftBackCanID = Integer.parseInt(
            frc.robot.Robot.RobotConfiguration.getProperty("drive_left_back_canid", "3"));

        rightFront = new VictorSPX(rightFrontCanID);
        leftFront = new VictorSPX(leftFrontCanID);
        rightBack = new VictorSPX(rightBackCanID);
        leftBack = new VictorSPX(leftBackCanID);

        rightBack.follow(rightFront);
        leftBack.follow(leftFront);
    }

    @Override
    public void teleopPeriodic() {
        exponentialDrive(deadband(frc.robot.Robot.driverOne.getLeftY(), 0.1), deadband(frc.robot.Robot.driverOne.getRightY(), 0.1));
    }

    public double deadband(double input, double deadband) {
    double output;
    if(Math.abs(input) < deadband) {
      output = 0;
    } else {
      output = input;
    }
    return output;
  }
  
  public void exponentialDrive(double left, double right) {
    leftFront.set(VictorSPXControlMode.PercentOutput, -(0.2*Math.tan(1.4*left)));
    rightFront.set(VictorSPXControlMode.PercentOutput, -(0.2*Math.tan(1.4*right)));
  }
}