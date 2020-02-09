package frc.robot.commands.auto.normal.threecell;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class ThreeCellAutoCommand extends SequentialCommandGroup {
  public ThreeCellAutoCommand(RobotContainer robotContainer) {
    addCommands(
        new TrajectoryFollowerCommand(robotContainer, ThreeCellTrajectories.THREE_CELL)
    );
  }
}
