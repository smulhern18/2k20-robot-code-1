package frc.robot.commands.auto.thief.oppositefive;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;

public class OppositeFiveAutoCommand extends SequentialCommandGroup {
  public OppositeFiveAutoCommand() {
    addCommands(
        new TrajectoryFollowerCommand(OppositeFiveTrajectories.OPPOSITE)
    );
  }
}
