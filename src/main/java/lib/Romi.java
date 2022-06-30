package lib;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.DriveTime;
import frc.robot.commands.TurnDegrees;
import frc.robot.commands.TurnTime;
import frc.robot.subsystems.Drivetrain;

import static lib.ProceduralRobot.callCommand;
import static lib.ProceduralRobot.sleep;

public class Romi {

    private static Drivetrain drive;
    private static boolean isTestMode;

    public static void initRomiSubsystems() {
        drive = new Drivetrain();
    }

    public static void enableTestMode(boolean isTestMode) {
        Romi.isTestMode = isTestMode;
    }

    public static void driveTime(double speed, double time) throws InterruptedException {
        if (!isTestMode) {
            Command driveTime = new DriveTime(speed, time, drive);
            callCommand(driveTime);
        }
    }

    public static void turnTime(double speed, double time) throws InterruptedException {
        if (!isTestMode) {
            Command turnTime = new TurnTime(speed, time, drive);
            callCommand(turnTime);
        }
    }

    public static void driveDistance(double inches) throws InterruptedException {
        if (!isTestMode) {
            Command driveDistance = new DriveDistance(0.5, inches, drive);
            callCommand(driveDistance);
        }
    }

    public static void turnDegrees(double degrees) throws InterruptedException {
        if (!isTestMode) {
            Command turnDegrees = new TurnDegrees(0.5, degrees, drive);
            callCommand(turnDegrees);
        }
    }


}
