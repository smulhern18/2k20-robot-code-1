package frc.robot.commands.auto.thief.trench;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TrenchThiefAutoCommand extends SequentialCommandGroup {
  public TrenchThiefAutoCommand(DrivetrainSubsystem drivetrainSubsystem) {
    addCommands(
        new TrajectoryFollowerCommand(TrenchThiefTrajectories.STEAL_FIVE, drivetrainSubsystem),
        new TrajectoryFollowerCommand(TrenchThiefTrajectories.RETREAT, drivetrainSubsystem)
    );
  }
}
