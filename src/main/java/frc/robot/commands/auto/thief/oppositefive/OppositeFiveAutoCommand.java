package frc.robot.commands.auto.thief.oppositefive;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class OppositeFiveAutoCommand extends SequentialCommandGroup {
  public OppositeFiveAutoCommand(DrivetrainSubsystem drivetrain) {
    addCommands(
        new TrajectoryFollowerCommand(OppositeFiveTrajectories.OPPOSITE, drivetrain)
    );
  }
}
