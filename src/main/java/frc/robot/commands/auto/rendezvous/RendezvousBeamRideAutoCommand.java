package frc.robot.commands.auto.rendezvous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class RendezvousBeamRideAutoCommand extends SequentialCommandGroup {
  public RendezvousBeamRideAutoCommand(DrivetrainSubsystem drivetrainSubsystem) {
    addCommands(
        new TrajectoryFollowerCommand(RendezvousTrajectories.BEAM_RIDE_GRAB_THREE, drivetrainSubsystem),
        new TrajectoryFollowerCommand(RendezvousTrajectories.BEAM_RIDE_GRAB_TWO, drivetrainSubsystem)
    );
  }
}
