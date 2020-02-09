package frc.robot.commands.auto.normal.trench;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TrenchAutoCommand extends SequentialCommandGroup {
  public TrenchAutoCommand() {
    addCommands(
        new TrajectoryFollowerCommand(TrenchTrajectories.GRAB),
        new TrajectoryFollowerCommand(TrenchTrajectories.RETURN));
  }
}
