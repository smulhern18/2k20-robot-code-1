package frc.robot.commands.auto.oppositeTwo;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class OppositeTwoAutoCommand extends SequentialCommandGroup {
  public OppositeTwoAutoCommand(DrivetrainSubsystem drivetrain) {
    addCommands(
        new TrajectoryFollowerCommand(OppositeTwoTrajectories.OPPOSITE_TWO_GRAB, drivetrain),
        new TrajectoryFollowerCommand(OppositeTwoTrajectories.OPPOSITE_TWO_SHOOT, drivetrain)
    );
  }
}
