package frc.robot.commands.auto.normal.trench;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class TrenchAutoCommand extends SequentialCommandGroup {
  public TrenchAutoCommand() {
    addCommands(
        new TrajectoryFollowerCommand(TrenchTrajectories.GRAB),
        new TrajectoryFollowerCommand(TrenchTrajectories.RETURN));
  }
}
