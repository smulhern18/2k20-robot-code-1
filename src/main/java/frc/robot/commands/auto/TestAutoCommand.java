package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Trajectories;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * Simple auto that makes a quarter circle, then goes back
 */
public class TestAutoCommand extends SequentialCommandGroup {
  public TestAutoCommand(DrivetrainSubsystem drivetrainSubsystem) {
    addCommands(
        new TrajectoryFollowerCommand(
            Trajectories.Test.FORWARD,
            drivetrainSubsystem),
        new TrajectoryFollowerCommand(
            Trajectories.Test.BACKWARD,
            drivetrainSubsystem));
  }
}
