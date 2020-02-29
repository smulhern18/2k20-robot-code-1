package frc.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CollectorSubsystem;

import java.util.Map;

public class ToggleCollectorPistonCommand extends SelectCommand {

  public ToggleCollectorPistonCommand(RobotContainer robotContainer) {
    super(
        Map.ofEntries(
            Map.entry(CollectorSubsystem.CollectorState.UNDEPLOYED, new InstantCommand(robotContainer.collectorSubsystem::deploy, robotContainer.collectorSubsystem)),
            Map.entry(CollectorSubsystem.CollectorState.DEPLOYED, new InstantCommand(robotContainer.collectorSubsystem::undeploy, robotContainer.collectorSubsystem))
        ),
        robotContainer.collectorSubsystem::getState
    );
  }
}
