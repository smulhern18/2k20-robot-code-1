package frc.robot.commands.auto.thief.oppositefive;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class OppositeFiveAutoCommand extends SequentialCommandGroup {
  public OppositeFiveAutoCommand(RobotContainer robotContainer) {
    addCommands(
        new TrajectoryFollowerCommand(robotContainer, OppositeFiveTrajectories.OPPOSITE)
    );
  }
}
