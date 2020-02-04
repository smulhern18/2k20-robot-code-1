package frc.robot.commands.auto.twoBall;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TwoBallAutoCommand extends SequentialCommandGroup {
  public TwoBallAutoCommand(DrivetrainSubsystem drivetrain) {
    addCommands(
        new TrajectoryFollowerCommand(TwoBallTrajectories.TWO_GRAB, drivetrain),
        new TrajectoryFollowerCommand(TwoBallTrajectories.TWO_SHOOT, drivetrain));
  }
}
