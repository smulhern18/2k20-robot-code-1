package frc.robot.commands.auto.thief.oppositetwo;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class OppositeTwoAutoCommand extends SequentialCommandGroup {
  public OppositeTwoAutoCommand(RobotContainer robotContainer) {
    addCommands(
        new TrajectoryFollowerCommand(robotContainer, OppositeTwoTrajectories.OPPOSITE_TWO_GRAB),
        new TrajectoryFollowerCommand(robotContainer, OppositeTwoTrajectories.OPPOSITE_TWO_SHOOT)
    );
  }
}
