package frc.robot.commands.auto.normal.threecell;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class ThreeCellAutoCommand extends SequentialCommandGroup {
  public ThreeCellAutoCommand(DrivetrainSubsystem drivetrain) {
    addCommands(
        new TrajectoryFollowerCommand(ThreeCellTrajectories.THREE_CELL, drivetrain)
    );
  }
}
