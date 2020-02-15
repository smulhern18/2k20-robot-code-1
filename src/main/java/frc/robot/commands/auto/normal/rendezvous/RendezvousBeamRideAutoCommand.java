package frc.robot.commands.auto.normal.rendezvous;

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
 * 8 ball auto
 * empty own rendezvous by hopping speed bump
 */
public class RendezvousBeamRideAutoCommand extends SequentialCommandGroup {
  public RendezvousBeamRideAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // grab first two balls, to make five in robot
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.BEAM_RIDE_GRAB_THREE),
            new PrepShooterCommand(robotContainer),
            new CollectCommand(robotContainer)
        ),
        // shoot 5 balls
        new VisionAimAndShootCommand(robotContainer).withTimeout(5),
        // pick up three balls
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.BEAM_RIDE_GRAB_TWO),
            new PrepShooterCommand(robotContainer),
            new CollectCommand(robotContainer)
        ),
        // shoot last three
        new VisionAimAndShootCommand(robotContainer)
    );
  }
}
