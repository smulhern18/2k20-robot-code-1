package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.trenchable.UntrenchCommand;

public class ExtendClimbCommand extends SequentialCommandGroup {
  public ExtendClimbCommand(RobotContainer robotContainer) {
    addCommands(
        // Untrenches an unslaps
        new UntrenchCommand(robotContainer).withTimeout(2),
        // extends climber
        new InstantCommand(robotContainer.climberSubsystem::triggerClimb, robotContainer.climberSubsystem)
    );
  }
}
