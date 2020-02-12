package frc.robot.commands.auto.test;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

/**
 * Simple auto that follows a "tight" S-shape
 */
public class TestAutoCommand extends SequentialCommandGroup {
  public TestAutoCommand(RobotContainer robotContainer) {
    addCommands(
        new TrajectoryFollowerCommand(robotContainer, TestTrajectories.FORWARD));
  }
}
