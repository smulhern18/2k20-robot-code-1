package frc.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CollectorSubsystem;

/**
 * Press once, collector deploys, intake wheels go
 * once 5 balls are in robot, intake wheels stop, undeploys
 */
public class ManualIntakeCommand extends CommandBase {
  private CollectorSubsystem collectorSubsystem;
  private RobotContainer robotContainer;

  public ManualIntakeCommand(RobotContainer robotContainer) {
    this.collectorSubsystem = robotContainer.collectorSubsystem;
    addRequirements(collectorSubsystem);
  }

  /**
   * Deploys collector and exhausts
   */
  @Override
  public void initialize() {
    collectorSubsystem.deploy();
    collectorSubsystem.intake();
  }

  /**
   * Terminates collector state-change process
   *
   * @param interrupted true if state change was interrupted
   */
  @Override
  public void end(boolean interrupted) {
    collectorSubsystem.stopIntake();
  }
}
