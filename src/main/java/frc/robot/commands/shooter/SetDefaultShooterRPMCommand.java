package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;

/**
 * Sets shooter to default RPM
 */
public class SetDefaultShooterRPMCommand extends InstantCommand {
  public SetDefaultShooterRPMCommand(RobotContainer robotContainer) {
    super(() -> robotContainer.shooterSubsystem.setTargetRPM(Constants.ShooterConstants.DEFAULT_RPM), robotContainer.shooterSubsystem);
  }
}
