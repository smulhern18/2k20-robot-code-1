package frc.robot.commands.auto.thief;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class ThiefAutoCommand extends SequentialCommandGroup {
  public ThiefAutoCommand(DrivetrainSubsystem drivetrainSubsystem) {
    addCommands(
        new TrajectoryFollowerCommand(ThiefTrajectories.STEAL_FIVE, drivetrainSubsystem),
        new TrajectoryFollowerCommand(ThiefTrajectories.RETREAT, drivetrainSubsystem)
    );
  }
}
