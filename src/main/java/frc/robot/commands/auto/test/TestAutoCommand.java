package frc.robot.commands.auto.test;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * Simple auto that follows a "tight" S-shape
 */
public class TestAutoCommand extends SequentialCommandGroup {
  public TestAutoCommand(DrivetrainSubsystem drivetrainSubsystem) {
    addCommands(
        new TrajectoryFollowerCommand(TestTrajectories.S, drivetrainSubsystem));
  }
}
