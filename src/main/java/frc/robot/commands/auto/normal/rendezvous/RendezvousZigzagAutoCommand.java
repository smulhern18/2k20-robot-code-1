package frc.robot.commands.auto.normal.rendezvous;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.PrepShooterCommand;
import frc.robot.commands.shooter.VisionAimAndShootCommand;

/**
 * Empty own trench, without hopping speed bump
 */
public class RendezvousZigzagAutoCommand extends SequentialCommandGroup {
  public RendezvousZigzagAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // collect two balls
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.ZIGZAG_GRAB_TWO_FRONT),
            new CollectCommand(robotContainer)
        ),
        // back up
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.ZIGZAG_BACK_UP),
            new PrepShooterCommand(robotContainer)
        ),
        // shoot five balls
        new VisionAimAndShootCommand(robotContainer).withTimeout(5),
        // grab one ball
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.ZIGZAG_GRAB_ONE_FRONT),
            new CollectCommand(robotContainer)
        ),
        // zoom around pole
        new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.ZIGZAG_BACK_UP_TWO),
        // collect two balls
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.ZIGZAG_GRAB_TWO_SIDE),
            new CollectCommand(robotContainer),
            new PrepShooterCommand(robotContainer)
        ),
        // shoot two balls
        new VisionAimAndShootCommand(robotContainer)
    );
  }
}
