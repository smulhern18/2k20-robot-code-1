package frc.robot.commands.auto.crazy;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class CrazyAutoCommand extends SequentialCommandGroup {
  public CrazyAutoCommand(DrivetrainSubsystem drivetrain) {
    addCommands(
        new TrajectoryFollowerCommand(CrazyTrajectories.START, drivetrain),
        new TrajectoryFollowerCommand(CrazyTrajectories.FIRST_THREE, drivetrain),
        new TrajectoryFollowerCommand(CrazyTrajectories.LAST_TWO, drivetrain),
        new TrajectoryFollowerCommand(CrazyTrajectories.GRAB_TWO, drivetrain));
  }
}
