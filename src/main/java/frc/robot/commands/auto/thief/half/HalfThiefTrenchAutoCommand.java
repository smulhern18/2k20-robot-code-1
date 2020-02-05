package frc.robot.commands.auto.thief.half;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class HalfThiefTrenchAutoCommand extends SequentialCommandGroup {
  public HalfThiefTrenchAutoCommand(DrivetrainSubsystem drivetrain) {
    addCommands(
        new TrajectoryFollowerCommand(HalfThiefTrenchTrajectories.FIRST_TWO, drivetrain),
        new TrajectoryFollowerCommand(HalfThiefTrenchTrajectories.SHOOT_ONE, drivetrain),
        new TrajectoryFollowerCommand(HalfThiefTrenchTrajectories.UNDER_RENDEZ, drivetrain));
  }
}
