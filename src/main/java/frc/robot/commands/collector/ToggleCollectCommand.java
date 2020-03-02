package frc.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CollectorSubsystem;

import java.util.Map;

public class ToggleCollectCommand extends SelectCommand {
  public ToggleCollectCommand(RobotContainer robotContainer) {
    super(
        Map.ofEntries(
            Map.entry(false, new ManualInBallPathCommand(robotContainer)),
            Map.entry(true, new InstantCommand(() -> robotContainer.collectorSubsystem.stopIntake(), robotContainer.collectorSubsystem))
        ),
        () -> robotContainer.collectorSubsystem.state
    );
  }
}
