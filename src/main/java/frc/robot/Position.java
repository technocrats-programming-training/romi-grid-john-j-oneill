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
      double correctionYawDegrees = targetYawDegrees - positionYawDegrees;
      if(correctionYawDegrees > 180.0)
      {
        correctionYawDegrees -= 360.0;
      }
      if(correctionYawDegrees < -180.0)
      {
        correctionYawDegrees += 360.0;
      }
      if(correctionYawDegrees > 90.0)
      {
        correctionYawDegrees -= 180.0;
        targetYawDegrees -= 180.0;
        driveDistanceInches *= -1.0;
      }
      else if(correctionYawDegrees < -90.0)
      {
        correctionYawDegrees += 180.0;
        targetYawDegrees += 180.0;
        driveDistanceInches *= -1.0;
      }
      turnDegrees(correctionYawDegrees);
      positionYawDegrees = targetYawDegrees;
      driveDistance(driveDistanceInches);
      final double targetYawRadians = Math.toRadians(targetYawDegrees);
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

    public void returnToHome() throws InterruptedException {
      double angleToHomeRadians = Math.atan2(-positionInchesY,-positionInchesX);
      double distanceToHomeInches = Math.sqrt(positionInchesY * positionInchesY + positionInchesX * positionInchesX);
      driveAngle(Math.toDegrees(angleToHomeRadians),distanceToHomeInches);
    }

    public String toString() {
        return "(" + Math.round(positionInchesX) + ", " + Math.round(positionInchesY) + ")";
    }
}
