package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

/**
 * Spins up shooter wheel to default RPM
 */
public class PrepShooterDefaultRPMCommand extends SequentialCommandGroup {
  public PrepShooterDefaultRPMCommand(RobotContainer robotContainer) {
    addCommands(
        new SetDefaultShooterRPMCommand(robotContainer),
        new SpinShooterWheelCommand(robotContainer)
    );
  }
}
