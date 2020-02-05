package frc.robot.commands.auto.normal.twocell;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TwoCellAutoCommand extends SequentialCommandGroup {
  public TwoCellAutoCommand(DrivetrainSubsystem drivetrain) {
    addCommands(
        new TrajectoryFollowerCommand(TwoBallTrajectories.TWO_GRAB, drivetrain)
    );
  }
}
