package frc.robot.Subsystems;

import frc.robot.RobotSubsystems;

import edu.wpi.first.cameraserver.CameraServer;

public class Display extends RobotSubsystems {
    
    // Constructors
    public Display() {
        robotInit();
    }
    int loopCount = 0;

    public void robotInit() {CameraServer.startAutomaticCapture();}

    public void robotPeriodic() {
        int skips = 50;

        if(loopCount % skips == 0)  {
            //place all outgoing data to Smart Dashboard/Drivers Station here
            //This is just to reduce the number of times it reads and sends data
            // to keep the code quick and not slow the rio too much

        }
        loopCount++;
    }
}
