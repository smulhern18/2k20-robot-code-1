package frc.robot.commands.auto.thief.oppositefive;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.PrepShooterCommand;
import frc.robot.commands.shooter.VisionAimAndShootCommand;

/**
 * 5 ball auto
 * empty opponent rendezvous
 */
public class OppositeFiveAutoCommand extends SequentialCommandGroup {
  public OppositeFiveAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // Collect five balls from the enemy rendezvous
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, OppositeFiveTrajectories.OPPOSITE),
            new CollectCommand(robotContainer)
        ),
        // spin in place
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, OppositeFiveTrajectories.SPIN),
            new PrepShooterCommand(robotContainer)
        ),
        // shoot five
        new VisionAimAndShootCommand(robotContainer)
    );
  }
}
