package frc.robot.commands.auto.threeBall;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class ThreeBallAutoCommand extends SequentialCommandGroup {
  public ThreeBallAutoCommand(DrivetrainSubsystem drivetrain) {
    addCommands(
        new TrajectoryFollowerCommand(ThreeBallTrajectories.THREE_BALL, drivetrain)
    );
  }
}
