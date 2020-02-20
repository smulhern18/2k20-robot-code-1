package frc.robot.commands.colorwheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ColorWheelConstants;
import frc.robot.RobotContainer;
import frc.robot.models.Color;
import frc.robot.subsystems.ColorWheelSubsystem;

public class PositionalCommand extends CommandBase {
  private String currentColor;
  private ColorWheelSubsystem colorWheelSubsystem;

  /**
   * Creates a new PositionalCommand
   * @param robotContainer
   */
  public PositionalCommand(RobotContainer robotContainer) {
    this.colorWheelSubsystem = robotContainer.colorWheelSubsystem;
    addRequirements(colorWheelSubsystem);
  }

  /**
   * Starts the current color at unknown
   */
  @Override
  public void initialize() {
    currentColor = ColorWheelConstants.UNKNOWN;
  }

  /**
   * Updates the current Color and rotates the wheel
   */
  @Override
  public void execute() {
    currentColor = colorWheelSubsystem.detectColor();
    colorWheelSubsystem.rotateWheel();
  }

  /**
   * Stops rotating when the complement of the current color is the goal color
   * @return complement of the current color is equal to the goal color
   */
  @Override
  public boolean isFinished() {
    Color goalColor = RobotContainer.readColor();
    return goalColor.value.equals("red") && currentColor.equals("blue") || goalColor.value.equals("blue") && currentColor.equals("red") ||
        goalColor.value.equals("yellow") && currentColor.equals("green") || goalColor.value.equals("green") && currentColor.equals("yellow"); // based on compliment
  }

  /**
   * At the end the colorWheelSubsystem stops
   * @param interrupted
   */
  @Override
  public void end(boolean interrupted) {
    colorWheelSubsystem.stopWheel();
  }
}
