package frc.robot.commands.auto.thief.half;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class HalfThiefTrenchAutoCommand extends SequentialCommandGroup {
  public HalfThiefTrenchAutoCommand() {
    addCommands(
        new TrajectoryFollowerCommand(HalfThiefTrenchTrajectories.FIRST_TWO),
        new TrajectoryFollowerCommand(HalfThiefTrenchTrajectories.SHOOT_ONE),
        new TrajectoryFollowerCommand(HalfThiefTrenchTrajectories.UNDER_RENDEZ));
  }
}
