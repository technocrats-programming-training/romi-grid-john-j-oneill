// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILi.b BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import lib.ProceduralRobot;

import java.util.Scanner;

import static lib.Romi.*;


public class Robot extends ProceduralRobot {
  @Override
  public void autonomousProcedure() throws InterruptedException {
    Joystick joystick = new Joystick(0);
    Position position = new Position();
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i==-1; i++) {
      if(joystick.getRawButton(1)) {
        // UP
        position.driveUp();
      } else if (joystick.getRawButton(2)) {
        // Down
        position.driveDown();
      } else if (joystick.getRawButton(3)) {
        // Left
        position.driveLeft();
      } else if (joystick.getRawButton(4)) {
        // Right
        position.driveRight();
      } else if (joystick.getRawButton(5)) { //H
        // Home
        break;
      } else {
        System.out.println("No Command Given");
      }
    }
    System.out.println("Returning Home Now...");
    position.returnToHome();

  }
}
