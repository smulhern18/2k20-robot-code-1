package frc.robot.commands.auto.normal.threecell;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;

public class ThreeCellAutoCommand extends SequentialCommandGroup {
  public ThreeCellAutoCommand() {
    addCommands(
        new TrajectoryFollowerCommand(ThreeCellTrajectories.THREE_CELL)
    );
  }
}
