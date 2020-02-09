package frc.robot.commands.auto.thief.oppositetwo;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class OppositeTwoAutoCommand extends SequentialCommandGroup {
  public OppositeTwoAutoCommand() {
    addCommands(
        new TrajectoryFollowerCommand(OppositeTwoTrajectories.OPPOSITE_TWO_GRAB),
        new TrajectoryFollowerCommand(OppositeTwoTrajectories.OPPOSITE_TWO_SHOOT)
    );
  }
}
