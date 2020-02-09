package frc.robot.commands.trenchable;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;
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
    if (robotContainer.trenchableSubsystem.getState() == TrenchableSubsystem.TrenchableState.TRENCHABLE) {
      new UntrenchCommand(robotContainer).schedule();
    } else {
      new TrenchCommand(robotContainer).schedule();
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
