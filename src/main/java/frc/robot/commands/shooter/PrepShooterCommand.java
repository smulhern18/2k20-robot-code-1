package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.trenchable.UntrenchCommand;
import frc.robot.commands.turret.AutoAimTurretCommand;

/**
 * Prepares shooter
 *
 * Untrench
 * Auto aim and set shooter rpm to default speed
 * when target is found, rpm is set more accurately
 */
public class PrepShooterCommand extends SequentialCommandGroup {
  public PrepShooterCommand(RobotContainer robotContainer) {
    addCommands(
        new UntrenchCommand(robotContainer),
        new ParallelDeadlineGroup(
            new AutoAimTurretCommand(robotContainer),
            new SetDefaultRPMCommand(robotContainer)
        ),
        new AutoSetRPMCommand(robotContainer)
    );
  }
}
