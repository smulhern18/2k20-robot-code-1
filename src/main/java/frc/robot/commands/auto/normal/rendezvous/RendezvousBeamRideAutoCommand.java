package frc.robot.commands.auto.normal.rendezvous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.AutoAimAndShootCommand;
import frc.robot.commands.trenchable.UntrenchCommand;

/**
 * 8 ball auto
 * empty own rendezvous by hopping speed bump
 */
public class RendezvousBeamRideAutoCommand extends SequentialCommandGroup {
  public RendezvousBeamRideAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // grab first two balls, to make five in robot
        new ParallelCommandGroup(
            new UntrenchCommand(robotContainer),
            new CollectCommand(robotContainer).withTimeout(5),
            new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.BEAM_RIDE_GRAB_THREE)
        ),
        // shoot 5 balls
        new AutoAimAndShootCommand(robotContainer),
        // pick up three balls
        new ParallelCommandGroup(
            new UntrenchCommand(robotContainer),
            new CollectCommand(robotContainer).withTimeout(5),
            new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.BEAM_RIDE_GRAB_TWO)
        ),
        // shoot last three
        new AutoAimAndShootCommand(robotContainer)
    );
  }
}
