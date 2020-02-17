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
  String prevColor;
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
    prevColor = "null";
    colorChanges = 0;
    rotations = 0;

    SmartDashboard.putBoolean("Rotation", false);
  }

  @Override
  public void execute() {
    if(colorChanges == COLOR_CHANGES_PER_ROTATION) {
      colorChanges = 0;
      rotations++;
    }
    detectColorChange();
  }

  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("Rotation", true);
    // stop spinning motor
  }

  @Override
  public boolean isFinished() {
    return rotations == ROTATIONS_PER_STAGE; // limit when motor should stop
  }

  public void detectColorChange() {
    
    if((! prevColor.equals(colorWheelSubsystem.colorString)) && 
                          (colorWheelSubsystem.confidence >= ColorWheelConstants.CONFIDENCE_LIMIT)) {
      colorChanges++;
    }
  }
}
