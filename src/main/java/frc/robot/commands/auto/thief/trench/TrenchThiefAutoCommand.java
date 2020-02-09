package frc.robot.commands.auto.thief.trench;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class TrenchThiefAutoCommand extends SequentialCommandGroup {
  public TrenchThiefAutoCommand(RobotContainer robotContainer) {
    addCommands(
        new TrajectoryFollowerCommand(robotContainer, TrenchThiefTrajectories.STEAL_FIVE),
        new TrajectoryFollowerCommand(robotContainer, TrenchThiefTrajectories.RETREAT)
    );
  }
}
