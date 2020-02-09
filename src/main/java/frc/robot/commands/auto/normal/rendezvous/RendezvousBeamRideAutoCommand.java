package frc.robot.commands.auto.normal.rendezvous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class RendezvousBeamRideAutoCommand extends SequentialCommandGroup {
  public RendezvousBeamRideAutoCommand() {
    addCommands(
        new TrajectoryFollowerCommand(RendezvousTrajectories.BEAM_RIDE_GRAB_THREE),
        new TrajectoryFollowerCommand(RendezvousTrajectories.BEAM_RIDE_GRAB_TWO)
    );
  }
}
