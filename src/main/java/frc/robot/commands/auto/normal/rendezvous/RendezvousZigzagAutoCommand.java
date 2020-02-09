package frc.robot.commands.auto.normal.rendezvous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class RendezvousZigzagAutoCommand extends SequentialCommandGroup {
  public RendezvousZigzagAutoCommand() {
    addCommands(
        new TrajectoryFollowerCommand(RendezvousTrajectories.ZIGZAG_GRAB_TWO_FRONT),
        new TrajectoryFollowerCommand(RendezvousTrajectories.ZIGZAG_GRAB_ONE_FRONT),
        new TrajectoryFollowerCommand(RendezvousTrajectories.ZIGZAG_GRAB_TWO_SIDE)
    );
  }
}
