package frc.robot.commands.colorwheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ColorWheelConstants;
import frc.robot.RobotContainer;
import frc.robot.models.Color;
import frc.robot.subsystems.ColorWheelSubsystem;

public class PositionalCommand extends CommandBase {
  private String currentColor;
  private ColorWheelSubsystem colorWheelSubsystem;

  public PositionalCommand(RobotContainer robotContainer) {
    this.colorWheelSubsystem = robotContainer.colorWheelSubsystem;
    addRequirements(colorWheelSubsystem);
  }

  @Override
  public void initialize() {
    currentColor = ColorWheelConstants.UNKNOWN;
  }

  @Override
  public void execute() {
    currentColor = colorWheelSubsystem.detectColor();
    colorWheelSubsystem.rotateWheel();
  }

  @Override
  public boolean isFinished() {
    Color goalColor = RobotContainer.readColor();
    return goalColor.value.equals("red") && currentColor.equals("blue") || goalColor.value.equals("blue") && currentColor.equals("red") ||
        goalColor.value.equals("yellow") && currentColor.equals("green") || goalColor.value.equals("green") && currentColor.equals("yellow"); // based on compliment
  }

  @Override
  public void end(boolean interrupted) {
    colorWheelSubsystem.stopWheel();
  }
}
