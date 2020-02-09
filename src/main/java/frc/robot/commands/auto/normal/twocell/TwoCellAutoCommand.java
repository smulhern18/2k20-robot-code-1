package frc.robot.commands.auto.normal.twocell;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class TwoCellAutoCommand extends SequentialCommandGroup {
  public TwoCellAutoCommand(RobotContainer robotContainer) {
    addCommands(
        new TrajectoryFollowerCommand(robotContainer, TwoBallTrajectories.TWO_GRAB)
    );
  }
}
