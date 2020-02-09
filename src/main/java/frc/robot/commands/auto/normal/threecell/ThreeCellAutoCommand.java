package frc.robot.commands.auto.normal.threecell;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class ThreeCellAutoCommand extends SequentialCommandGroup {
  public ThreeCellAutoCommand() {
    addCommands(
        new TrajectoryFollowerCommand(ThreeCellTrajectories.THREE_CELL)
    );
  }
}
