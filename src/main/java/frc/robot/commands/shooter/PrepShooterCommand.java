package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.trenchable.UntrenchCommand;
import frc.robot.commands.turret.AutoAimTurretCommand;

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
        // lock onto port and pre-prep shooter
        new ParallelDeadlineGroup(
            new AutoAimTurretCommand(robotContainer),
            new ManualShootCommand(robotContainer, Constants.ShooterConstants.DEFAULT_RPM)
        ),
        // start up shooter wheel
        new AutoShootCommand(robotContainer)
    );
  }
}
