package frc.robot.commands.auto.normal.trench;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class TrenchAutoCommand extends SequentialCommandGroup {
  public TrenchAutoCommand(RobotContainer robotContainer) {
    addCommands(
        new TrajectoryFollowerCommand(robotContainer, TrenchTrajectories.GRAB),
        new TrajectoryFollowerCommand(robotContainer, TrenchTrajectories.RETURN));
  }
}
