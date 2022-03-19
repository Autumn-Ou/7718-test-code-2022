// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  // This file can be edited in-place on the robot with WinSCP 
  // or, copied over w/ SCP. 
  static final String CONFIG_PATH="/etc/robot.conf";
  static final int TEAM_NUMBER=7718;

  public static Properties RobotConfiguration = new Properties(); 
  public static boolean ConfigLoaded = false; 

  public static frc.robot.Subsystems.Auto Auto;
  public static frc.robot.Subsystems.Test Test;
  public static frc.robot.Subsystems.Display Display;
  public static frc.robot.Subsystems.Drive Drive;
  public static frc.robot.Subsystems.Cargo Cargo;
  public static frc.robot.Subsystems.Lift Lift;

  public static XboxController driverOne = new XboxController(0);
  public static XboxController driverTwo = new XboxController(1);

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();



  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    
    loadConfiguration();
    
    initializeSystems();

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }



  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    Robot.Auto.robotPeriodic();
    Robot.Test.robotPeriodic();
    Robot.Display.robotPeriodic();
    Robot.Drive.robotPeriodic();
    Robot.Cargo.robotPeriodic();
    Robot.Lift.robotPeriodic();
  }



  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    Robot.Auto.autonomousInit();
    Robot.Test.autonomousInit();
    Robot.Display.autonomousInit();
    Robot.Drive.autonomousInit();
    Robot.Cargo.autonomousInit();
    Robot.Lift.autonomousInit();
  }



  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }

    Robot.Auto.autonomousPeriodic();
    Robot.Test.autonomousPeriodic();
    Robot.Display.autonomousPeriodic();
    Robot.Drive.autonomousPeriodic();
    Robot.Cargo.autonomousPeriodic();
    Robot.Lift.autonomousPeriodic();
  }



  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    Robot.Auto.teleopInit();
    Robot.Test.teleopInit();
    Robot.Display.teleopInit();
    Robot.Drive.teleopInit();
    Robot.Cargo.teleopInit();
    Robot.Lift.teleopInit();
  }



  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    Robot.Auto.teleopPeriodic();
    Robot.Test.teleopPeriodic();
    Robot.Display.teleopPeriodic();
    Robot.Drive.teleopPeriodic();
    Robot.Cargo.teleopPeriodic();
    Robot.Lift.teleopPeriodic();
  }



  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}



  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}



  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
    Robot.Test.testInit();
  }


  
  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    Robot.Test.testPeriodic();
  }



  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}



  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}


  public String getRobotName()
  {
    if (RobotConfiguration != null)
    {
        return RobotConfiguration.getProperty("RobotName", "I don't know who I am. Change this by adding a 'RobotName=' line in " + 
        CONFIG_PATH + " on the robot's filesystem :) ");
    }
    else
    {
      return "No configuration loaded from " + CONFIG_PATH;
    }
  }


  void initializeSystems()  {
    Robot.Auto = new frc.robot.Subsystems.Auto();
    Robot.Test = new frc.robot.Subsystems.Test();
    Robot.Display = new frc.robot.Subsystems.Display();
    Robot.Drive = new frc.robot.Subsystems.Drive();
    Robot.Cargo = new frc.robot.Subsystems.Cargo();
    Robot.Lift = new frc.robot.Subsystems.Lift();
  }


  void loadConfiguration() 
  {
    try (InputStream input = new FileInputStream(CONFIG_PATH))
    //when debugging will throw error because the path only exist on the rio 
    {
      RobotConfiguration = new Properties();
      RobotConfiguration.load(input);
      input.close();
      ConfigLoaded=true; 
      System.out.println("Configuration loaded from " + CONFIG_PATH);
      
      if (RobotConfiguration != null) { 
      RobotConfiguration.forEach((k,v) -> System.out.println(k + "=" + v));} 
      //when debugging will throw error because the path only exist on the rio 
    }
    catch (java.io.IOException e) 
    {
      System.out.println("Unable to load robot configuration from " + CONFIG_PATH + ": " + e.getMessage() );
      
      ConfigLoaded = false; 

    }
  }
}