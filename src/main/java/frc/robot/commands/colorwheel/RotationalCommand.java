/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.colorwheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ColorWheelConstants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ColorWheelSubsystem;

public class RotationalCommand extends CommandBase {
  private String previousColor = ColorWheelConstants.UNKNOWN;
  private int colorChanges = 0, rotations = 0;
  private ColorWheelSubsystem colorWheelSubsystem;

  /**
   * Creates a new RotationalCommand.
   */
  public RotationalCommand(RobotContainer robotContainer) {
    colorWheelSubsystem = robotContainer.colorWheelSubsystem;
    addRequirements(colorWheelSubsystem);
  }

  /**
   * checks whether a color is detected, and if it changes, increments the amount of times rotated
   */
  @Override
  public void execute() {
    String currentColor = colorWheelSubsystem.detectColor();
    if (!previousColor.equals(currentColor) && (!currentColor.equals(ColorWheelConstants.UNKNOWN))) {
      colorChanges++;
      previousColor = currentColor;
    }
    rotations = colorChanges / ColorWheelConstants.COLOR_CHANGES_PER_ROTATION;
    colorWheelSubsystem.rotateWheel();
  }

  /**
   * Finishes when completed all rotations
   * @return rotations = needed rotations for stage
   */
  @Override
  public boolean isFinished() {
    return rotations == ColorWheelConstants.ROTATIONS_PER_STAGE;
  }

  /**
   * Stops the wheel at the end
   * @param interrupted
   */
  @Override
  public void end(boolean interrupted) {
    colorWheelSubsystem.stopWheel();
  }
}
