package frc.robot.commands.auto.normal.rendezvous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.AutoAimAndShootCommand;

/**
 * Empty own trench, without hopping speed bump
 */
public class RendezvousZigzagAutoCommand extends SequentialCommandGroup {
  public RendezvousZigzagAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // collect two balls
        new ParallelCommandGroup(
            new CollectCommand(robotContainer).withTimeout(5),
            new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.ZIGZAG_GRAB_TWO_FRONT)
        ),
        // back up
        new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.ZIGZAG_BACK_UP),
        // shoot five balls
        new AutoAimAndShootCommand(robotContainer).withTimeout(5),
        // grab one ball
        new ParallelCommandGroup(
            new CollectCommand(robotContainer).withTimeout(5),
            new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.ZIGZAG_GRAB_ONE_FRONT)
        ),
        // zoom around pole
        new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.ZIGZAG_BACK_UP_TWO),
        // collect two balls
        new ParallelCommandGroup(
            new CollectCommand(robotContainer).withTimeout(5),
            new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.ZIGZAG_GRAB_TWO_SIDE)
        ),
        // shoot two balls
        new AutoAimAndShootCommand(robotContainer)
    );
  }
}
