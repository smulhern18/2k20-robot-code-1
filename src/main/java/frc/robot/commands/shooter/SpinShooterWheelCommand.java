package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;

/**
 * Runs shooter wheel at target rpm
 */
public class SpinShooterWheelCommand extends InstantCommand {
  public SpinShooterWheelCommand(RobotContainer robotContainer) {
    super(() -> robotContainer.shooterSubsystem.shoot(), robotContainer.shooterSubsystem);
  }
}
