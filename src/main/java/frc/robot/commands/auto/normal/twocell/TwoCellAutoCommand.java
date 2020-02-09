package frc.robot.commands.auto.normal.twocell;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class TwoCellAutoCommand extends SequentialCommandGroup {
  public TwoCellAutoCommand() {
    addCommands(
        new TrajectoryFollowerCommand(TwoBallTrajectories.TWO_GRAB)
    );
  }
}
