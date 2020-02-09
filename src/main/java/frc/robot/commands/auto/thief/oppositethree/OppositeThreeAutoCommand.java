package frc.robot.commands.auto.thief.oppositethree;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;

public class OppositeThreeAutoCommand extends SequentialCommandGroup {
  public OppositeThreeAutoCommand(DrivetrainSubsystem drivetrain, BallPathSubsystem ballPathSubsystem) {
    addCommands(
        new TrajectoryFollowerCommand(OppositeThreeTrajectories.OPPOSITE_THREE_GRAB, drivetrain),
        new TrajectoryFollowerCommand(OppositeThreeTrajectories.OPPOSITE_THREE_SHOOT, drivetrain)
    );
  }
}
