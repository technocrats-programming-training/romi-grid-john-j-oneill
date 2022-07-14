package frc.robot;

import static lib.Romi.driveDistance;
import static lib.Romi.turnDegrees;
import java.util.*;

public class Position {
    // Odometry variables
    // Initialize to "up" which is 90 degrees
    public double positionInchesX = 0;
    public double positionInchesY = 0;
    public double positionYawDegrees = 90;
    // Always drive in increments
    public final double distanceToDriveInches = 10;

    public void driveAngle(double targetYawDegrees, double driveDistanceInches) throws InterruptedException {
      // Relative angle to move to get to target yaw
      double correctionYawDegrees = targetYawDegrees - positionYawDegrees;
      if(correctionYawDegrees > 180.0)
      {
        // Wrap to -pi to pi; this could be done with a
        // modulo, but I don't want to mess with negatives
        correctionYawDegrees -= 360.0;
      }
      if(correctionYawDegrees < -180.0)
      {
        // Wrap to -pi to pi; this could be done with a
        // modulo, but I don't want to mess with negatives
        correctionYawDegrees += 360.0;
      }
      if(correctionYawDegrees > 90.0)
      {
        // More efficient to back up: 
        // so rotate the opposite way and drive in reverse
        correctionYawDegrees -= 180.0;
        // Also update odom
        targetYawDegrees -= 180.0;
        // Flip sign of driving distance
        driveDistanceInches *= -1.0;
      }
      else if(correctionYawDegrees < -90.0)
      {
        // More efficient to back up: 
        // so rotate the opposite way and drive in reverse
        correctionYawDegrees += 180.0;
        // Also update odom
        targetYawDegrees += 180.0;
        // Flip sign of driving distance
        driveDistanceInches *= -1.0;
      }
      // Turn the robot
      turnDegrees(correctionYawDegrees);
      // Update the odometery
      positionYawDegrees = targetYawDegrees;
      // Drive the robot
      driveDistance(driveDistanceInches);
      // Update the odometery
      final double targetYawRadians = Math.toRadians(targetYawDegrees);
      // Trig!!!
      positionInchesY += Math.sin(targetYawRadians) * driveDistanceInches;
      positionInchesX += Math.cos(targetYawRadians) * driveDistanceInches;
      
    }

    public void driveUp() throws InterruptedException {
      // Drive up is +y
      driveAngle(90,distanceToDriveInches);
    }

    public void driveDown() throws InterruptedException {
      // Drive down is -y
      driveAngle(-90,distanceToDriveInches);
    }

    public void driveRight() throws InterruptedException {
      // Drive right is +x
      driveAngle(0,distanceToDriveInches);
    }

    public void driveLeft() throws InterruptedException {
      // Drive left is -x
      driveAngle(180,distanceToDriveInches);
    }

    public void returnToHome() throws InterruptedException {
      // Figure out the angle towards home
      double angleToHomeRadians = Math.atan2(-positionInchesY,-positionInchesX);
      // Pythagorus is a steely-eyed missle man
      double distanceToHomeInches = Math.sqrt(positionInchesY * positionInchesY + positionInchesX * positionInchesX);
      // Homeward bound!
      driveAngle(Math.toDegrees(angleToHomeRadians),distanceToHomeInches);
    }

    public String toString() {
      // Doesn't include yaw to satisfy the unit tests
      return "(" + Math.round(positionInchesX) + ", " + Math.round(positionInchesY) + ")";
    }
}
