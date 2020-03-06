package frc.robot.commands.trenchable;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.DeployCollectorCommand;
import frc.robot.commands.collector.LiftCollectorCommand;
import frc.robot.subsystems.TrenchableSubsystem;

/**
 * Toggles trenchability in one command
 */
public class ToggleTrenchabilityCommand extends CommandBase {
  RobotContainer robotContainer;

  /**
   * Requires trenchable subsystem
   */
  public ToggleTrenchabilityCommand(RobotContainer robotContainer) {
    this.robotContainer = robotContainer;
  }

  /**
   * Swaps state of trenchability
   */
  @Override
  public void initialize() {
    if (robotContainer.trenchableSubsystem.trenchablifier.get()) {
      new SequentialCommandGroup(
          new UntrenchCommand(robotContainer).withTimeout(2),
          new InstantCommand(() -> robotContainer.climberSubsystem.unslap(), robotContainer.climberSubsystem)
      ).schedule();
    } else {
      new SequentialCommandGroup(
          new DeployCollectorCommand(robotContainer).withTimeout(1),
          new TrenchCommand(robotContainer)
      ).schedule();
    }
  }

  /**
   * Instant command
   *
   * @return true
   */
  @Override
  public boolean isFinished() {
    return true;
  }
}
