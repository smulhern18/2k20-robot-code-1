package frc.robot.commands.auto.thief.half;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.PrepShooterCommand;
import frc.robot.commands.shooter.VisionAimAndShootCommand;

/**
 * 10 ball auto
 * Steal two from opponent's trench, empty own rendezvous
 */
public class HalfThiefTrenchAutoCommand extends SequentialCommandGroup {
  public HalfThiefTrenchAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // Collect two balls from enemy trench
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, HalfThiefTrenchTrajectories.FIRST_TWO),
            new CollectCommand(robotContainer)
        ),
        // move to port and untrench
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, HalfThiefTrenchTrajectories.SHOOT_ONE),
            new PrepShooterCommand(robotContainer)
        ),
        // shoot 5 balls
        new VisionAimAndShootCommand(robotContainer).withTimeout(5),
        // Collect 5 balls
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, HalfThiefTrenchTrajectories.UNDER_RENDEZ),
            new CollectCommand(robotContainer),
            new PrepShooterCommand(robotContainer)
        ),
        // Shoot 5 balls
        new VisionAimAndShootCommand(robotContainer)
    );
  }
}
