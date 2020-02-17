package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.trenchable.UntrenchCommand;

/**
 * Prepares shooter
 * <p>
 * Untrench
 * Auto aim and set shooter rpm to default speed
 * when target is found, rpm is set more accurately
 */
public class PrepShooterCommand extends SequentialCommandGroup {
  public PrepShooterCommand(RobotContainer robotContainer) {
    addCommands(
        // Untrench robot so turret and shooter can spin freely
        new UntrenchCommand(robotContainer),
        // spin up to a fast speed
        new ManualShootCommand(robotContainer, Constants.ShooterConstants.DEFAULT_RPM)
    );
  }
}
