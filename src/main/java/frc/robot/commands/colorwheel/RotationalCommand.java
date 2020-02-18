/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.colorwheel;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.ColorWheelConstants;
import frc.robot.subsystems.ColorWheelSubsystem;

public class RotationalCommand extends CommandBase {
  String previousColor, currentColor;
  int colorChanges, rotations;
  ColorWheelSubsystem colorWheelSubsystem;
  final int COLOR_CHANGES_PER_ROTATION = 8;
  final int ROTATIONS_PER_STAGE = 4;

  /**
   * Creates a new RotationalCommand.
   */
  public RotationalCommand(RobotContainer robotContainer) {
    colorWheelSubsystem = robotContainer.colorWheelSubsystem;
    addRequirements(colorWheelSubsystem);
  }

  @Override
  public void initialize() {
    previousColor = ColorWheelConstants.UNKNOWN;
    colorChanges = 0;
    rotations = 0;

    SmartDashboard.putString("hello", "nothing");
    SmartDashboard.putNumber("num", 0);
  }

  @Override
  public void execute() {
    currentColor = colorWheelSubsystem.detectColor();
    if (!previousColor.equals(currentColor) && (currentColor != ColorWheelConstants.UNKNOWN)) {
      colorChanges++;
      previousColor = currentColor;
      SmartDashboard.putString("hello", currentColor);
      SmartDashboard.putNumber("num", colorChanges);
    }else{
      SmartDashboard.putString("hello", "nothing");
      SmartDashboard.putNumber("num", colorChanges);
    }
    rotations = colorChanges / COLOR_CHANGES_PER_ROTATION;
  }

  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putString("hello", "nothing");
    //TODO: stop spinning motor
  }

  @Override
  public boolean isFinished() {
    return rotations == ROTATIONS_PER_STAGE; // limit when motor should stop
  }

  public void detectColorChange() {
    if((! previousColor.equals(colorWheelSubsystem.colorString)) && 
                          (colorWheelSubsystem.confidence >= ColorWheelConstants.CONFIDENCE_THRESHOLD)) {
      colorChanges++;
    }
  }
}
