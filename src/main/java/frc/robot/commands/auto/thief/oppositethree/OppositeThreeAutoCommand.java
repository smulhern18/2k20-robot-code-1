package frc.robot.commands.auto.thief.oppositethree;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class OppositeThreeAutoCommand extends SequentialCommandGroup {
  public OppositeThreeAutoCommand(RobotContainer robotContainer) {
    addCommands(
        new TrajectoryFollowerCommand(robotContainer, OppositeThreeTrajectories.OPPOSITE_THREE_GRAB),
        new TrajectoryFollowerCommand(robotContainer, OppositeThreeTrajectories.OPPOSITE_THREE_SHOOT)
    );
  }
}
