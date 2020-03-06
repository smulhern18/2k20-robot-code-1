package frc.robot.commands.auto.normal.trench;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.ManualShootCommand;
import frc.robot.commands.shooter.ShootCommand;
import frc.robot.commands.shooter.VisionAimAndShootCommand;
import frc.robot.commands.trenchable.TrenchCommand;
import frc.robot.commands.trenchable.UntrenchCommand;

/**
 * 8 ball auto
 * Empty own trench
 */
public class TrenchAutoCommand extends SequentialCommandGroup {
  public TrenchAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // shoot 3 preload balls
        new VisionAimAndShootCommand(robotContainer).withTimeout(7),
//        new ShootCommand(robotContainer, 5500).withTimeout(5),
        // trenchable
        new TrenchCommand(robotContainer).withTimeout(3),
        // Collect five balls from enemy trench
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, TrenchTrajectories.GRAB),
            new CollectCommand(robotContainer)
        ),
        new TrajectoryFollowerCommand(robotContainer, TrenchTrajectories.GRAB),
        // run away
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, TrenchTrajectories.RETURN),
            new SequentialCommandGroup(
                new WaitCommand(.5),
                new UntrenchCommand(robotContainer)
            )
        ),
        // shoot 5
        new VisionAimAndShootCommand(robotContainer).withTimeout(7)
    );
  }
}
