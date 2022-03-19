package frc.robot.Subsystems;

import frc.robot.RobotSubsystems;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Cargo extends RobotSubsystems {
    /* 
    The components relating to ONLY the Drive subsystem 
    should be defined as class variables here 
    */
    public static VictorSPX cargoArm;
    public static VictorSPX cargoIntake;

    // Constructors
    public Cargo() {
        robotInit();
    }

    public void robotInit() {
        //.getProperty("name in config file", "backup value if config file not found or variable not found")
        int cargoArmCanID = Integer.parseInt(
            frc.robot.Robot.RobotConfiguration.getProperty("cargo_arm_canid", "4"));
        int cargoIntakeCanID = Integer.parseInt(
            frc.robot.Robot.RobotConfiguration.getProperty("cargo_intake_canid", "5"));

        cargoArm = new VictorSPX(cargoArmCanID);
        cargoIntake = new VictorSPX(cargoIntakeCanID);
    }

    public void teleopPeriodic()    {
        if(frc.robot.Robot.driverTwo.getAButton())  {
            cargoArm.set(VictorSPXControlMode.PercentOutput, 0.3);
        }   else if(frc.robot.Robot.driverTwo.getBButton())   {
            cargoArm.set(VictorSPXControlMode.PercentOutput, -0.3);
        }   else  {
            cargoArm.set(VictorSPXControlMode.PercentOutput, 0);
        }

        if(frc.robot.Robot.driverTwo.getLeftBumper())  {
            cargoIntake.set(VictorSPXControlMode.PercentOutput, 0.75);
        }   else if(frc.robot.Robot.driverTwo.getRightBumper()) {
            cargoIntake.set(VictorSPXControlMode.PercentOutput, -0.75);
        }   else    {
            cargoIntake.set(VictorSPXControlMode.PercentOutput, 0);
        }
    }
}