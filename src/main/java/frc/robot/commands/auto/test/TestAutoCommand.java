package frc.robot.commands.auto.test;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

/**
 * Simple auto that follows a "tight" S-shape
 */
public class TestAutoCommand extends SequentialCommandGroup {
  public TestAutoCommand() {
    addCommands(
        new TrajectoryFollowerCommand(TestTrajectories.S));
  }
}
