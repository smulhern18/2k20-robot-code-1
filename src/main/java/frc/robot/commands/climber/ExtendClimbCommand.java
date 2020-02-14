package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.trenchable.UntrenchCommand;

public class ExtendClimbCommand extends SequentialCommandGroup {
  public ExtendClimbCommand(RobotContainer robotContainer) {
    addCommands(
        // Untrenches an unslaps
        new UntrenchCommand(robotContainer),
        // extends climber
        new UnspoolClimberCommand(robotContainer)
    );
  }
}
