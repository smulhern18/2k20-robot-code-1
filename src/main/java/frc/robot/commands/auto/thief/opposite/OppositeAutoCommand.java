package frc.robot.commands.auto.thief.opposite;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class OppositeAutoCommand extends SequentialCommandGroup {
  public OppositeAutoCommand(DrivetrainSubsystem drivetrain) {
    addCommands(
        new TrajectoryFollowerCommand(OppositeTrajectories.OPPOSITE, drivetrain)
    );
  }
}
