package frc.robot.commands.auto.normal.rendezvous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class RendezvousBeamRideAutoCommand extends SequentialCommandGroup {
  public RendezvousBeamRideAutoCommand(RobotContainer robotContainer) {
    addCommands(
        new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.BEAM_RIDE_GRAB_THREE),
        new TrajectoryFollowerCommand(robotContainer, RendezvousTrajectories.BEAM_RIDE_GRAB_TWO)
    );
  }
}
