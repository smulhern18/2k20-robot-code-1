package frc.robot.commands.auto.normal.trench;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TrenchAutoCommand extends SequentialCommandGroup {
  public TrenchAutoCommand(DrivetrainSubsystem drivetrain) {
    addCommands(
        new TrajectoryFollowerCommand(TrenchTrajectories.GRAB, drivetrain),
        new TrajectoryFollowerCommand(TrenchTrajectories.RETURN, drivetrain));
  }
}
