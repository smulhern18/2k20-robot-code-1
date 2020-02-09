package frc.robot.commands.auto.thief.half;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class HalfThiefTrenchAutoCommand extends SequentialCommandGroup {
  public HalfThiefTrenchAutoCommand(RobotContainer robotContainer) {
    addCommands(
        new TrajectoryFollowerCommand(robotContainer, HalfThiefTrenchTrajectories.FIRST_TWO),
        new TrajectoryFollowerCommand(robotContainer, HalfThiefTrenchTrajectories.SHOOT_ONE),
        new TrajectoryFollowerCommand(robotContainer, HalfThiefTrenchTrajectories.UNDER_RENDEZ));
  }
}
