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

    // Predefine constants
    final int joystickID = 0;
    final int joystickUp = 1;
    final int joystickDown = 2;
    final int joystickLeft = 3;
    final int joystickRight = 4;
    final int joystickHome = 5;
    
    Joystick joystick = new Joystick(joystickID);
    Position position = new Position();
    Scanner sc = new Scanner(System.in);
    while (true) {
      if(joystick.getRawButton(joystickUp)) {
        // Up
        position.driveUp();
      } else if (joystick.getRawButton(joystickDown)) {
        // Down
        position.driveDown();
      } else if (joystick.getRawButton(joystickLeft)) {
        // Left
        position.driveLeft();
      } else if (joystick.getRawButton(joystickRight)) {
        // Right
        position.driveRight();
      } else if (joystick.getRawButton(joystickHome)) {
        // Home
        break;
      } else {
        System.out.println("No Command Given");
      }
    }
    System.out.println("Returning Home Now...");
    position.returnToHome();
    System.out.println("There's no place like 127.0.0.1!");

  }
}
