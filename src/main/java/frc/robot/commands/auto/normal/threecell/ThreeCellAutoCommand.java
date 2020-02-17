package frc.robot.commands.auto.normal.threecell;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.PrepShooterCommand;
import frc.robot.commands.shooter.VisionAimAndShootCommand;

/**
 * 6 ball auto
 * grab three from own rendezvous
 */
public class ThreeCellAutoCommand extends SequentialCommandGroup {
  public ThreeCellAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // shoot three balls
        new VisionAimAndShootCommand(robotContainer).withTimeout(5),
        // collect three balls
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, ThreeCellTrajectories.THREE_CELL),
            new CollectCommand(robotContainer),
            new PrepShooterCommand(robotContainer)
        ),
        // shoot three balls
        new VisionAimAndShootCommand(robotContainer)
    );
  }
}
