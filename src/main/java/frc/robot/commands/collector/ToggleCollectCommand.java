package frc.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CollectorSubsystem;

public class ToggleCollectCommand extends CommandBase {
  private RobotContainer robotContainer;

  public ToggleCollectCommand(RobotContainer robotContainer) {
    this.robotContainer = robotContainer;
  }

  /**
   * On initialize the robot will toggle its collection state
   */
  @Override
  public void initialize() {
    if (robotContainer.collectorSubsystem.getState() == CollectorSubsystem.CollectorState.UNDEPLOYED)
      new CollectCommand(robotContainer).schedule();
    else
      new StopCollectingCommand(robotContainer).schedule();
  }
}
