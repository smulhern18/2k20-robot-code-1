package frc.robot.commands.auto.thief.half;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.VisionAimAndShootCommand;
import frc.robot.commands.trenchable.UntrenchCommand;

/**
 * 10 ball auto
 * Steal two from opponent's trench, empty own rendezvous
 */
public class HalfThiefTrenchAutoCommand extends SequentialCommandGroup {
  public HalfThiefTrenchAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // Collect two balls from enemy trench
        new ParallelCommandGroup(
            new CollectCommand(robotContainer).withTimeout(3),
            new TrajectoryFollowerCommand(robotContainer, HalfThiefTrenchTrajectories.FIRST_TWO)
        ),
        // move to port and untrench
        new ParallelCommandGroup(
            new UntrenchCommand(robotContainer).withTimeout(3),
            new TrajectoryFollowerCommand(robotContainer, HalfThiefTrenchTrajectories.SHOOT_ONE)
        ),
        // shoot 5 balls
        new VisionAimAndShootCommand(robotContainer).withTimeout(5),
        // Collect 5 balls
        new ParallelCommandGroup(
            new CollectCommand(robotContainer).withTimeout(8),
            new TrajectoryFollowerCommand(robotContainer, HalfThiefTrenchTrajectories.UNDER_RENDEZ)
        ),
        // Shoot 5 balls
        new VisionAimAndShootCommand(robotContainer)
    );
  }
}
