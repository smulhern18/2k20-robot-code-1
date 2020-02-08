package frc.robot.commands.auto.normal.rendezvous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ballpath.SetIndexerCountCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;

public class RendezvousZigzagAutoCommand extends SequentialCommandGroup {
  public RendezvousZigzagAutoCommand(DrivetrainSubsystem drivetrainSubsystem, BallPathSubsystem ballPathSubsystem) {
    addCommands(
        new SetIndexerCountCommand(ballPathSubsystem, 3),
        new TrajectoryFollowerCommand(RendezvousTrajectories.ZIGZAG_GRAB_TWO_FRONT, drivetrainSubsystem),
        new TrajectoryFollowerCommand(RendezvousTrajectories.ZIGZAG_GRAB_ONE_FRONT, drivetrainSubsystem),
        new TrajectoryFollowerCommand(RendezvousTrajectories.ZIGZAG_GRAB_TWO_SIDE, drivetrainSubsystem)
    );
  }
}
