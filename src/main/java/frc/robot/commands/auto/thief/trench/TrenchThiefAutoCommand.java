package frc.robot.commands.auto.thief.trench;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.PrepShooterCommand;
import frc.robot.commands.shooter.VisionAimAndShootCommand;
import frc.robot.commands.trenchable.UntrenchCommand;

/**
 * 5 ball auto
 * empty opponent's trench
 */
public class TrenchThiefAutoCommand extends SequentialCommandGroup {
  public TrenchThiefAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // steal five balls
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, TrenchThiefTrajectories.STEAL_FIVE),
            new CollectCommand(robotContainer)
        ),
        // run away
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, TrenchThiefTrajectories.RETREAT),
            new PrepShooterCommand(robotContainer)
        ),
        // shoot five
        new VisionAimAndShootCommand(robotContainer)
    );
  }
}
