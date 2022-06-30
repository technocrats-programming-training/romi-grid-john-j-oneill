package frc.robot;

import static lib.Romi.driveDistance;
import static lib.Romi.turnDegrees;
import java.util.*;

public class Position {
    public double positionInchesX = 0;
    public double positionInchesY = 0;
    public double positionYawDegrees = 90;
    public double distanceToDriveInches = 10;

    public void driveAngle(double targetYawDegrees, double driveDistanceInches) throws InterruptedException {
      double targetYawRadians = Math.toRadians(targetYawDegrees);
      double correctionYawDegrees = targetYawDegrees - positionYawDegrees;
      turnDegrees(correctionYawDegrees);
      positionYawDegrees = targetYawDegrees;
      driveDistance(driveDistanceInches);
      positionInchesY += Math.sin(targetYawRadians) * driveDistanceInches;
      positionInchesX += Math.cos(targetYawRadians) * driveDistanceInches;
      
    }

    public void driveUp() throws InterruptedException {
      driveAngle(90,distanceToDriveInches);
    }

    public void driveDown() throws InterruptedException {
      driveAngle(-90,distanceToDriveInches);
    }

    public void driveRight() throws InterruptedException {
      driveAngle(0,distanceToDriveInches);
    }

    public void driveLeft() throws InterruptedException {
      driveAngle(180,distanceToDriveInches);
    }

    public void returnToHome() {
      //double angleToHome = Math.atan2(-y,-x);
      //driveAngle(Math.toDegrees(angleToHome));
    }

    public String toString() {
        return positionInchesX + "," + positionInchesY;
    }
}
