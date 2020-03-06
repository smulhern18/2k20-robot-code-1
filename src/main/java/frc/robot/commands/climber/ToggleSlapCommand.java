package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.ToggleCollectCommand;
import frc.robot.subsystems.ClimberSubsystem;

import java.util.Map;

/**
 * Slaps an unslaps using the same command
 */
public class ToggleSlapCommand extends SelectCommand {
  public ToggleSlapCommand(RobotContainer robotContainer) {
    super(
        Map.ofEntries(
            Map.entry(true, new SlapCommand(robotContainer)),
            Map.entry(false, new InstantCommand(robotContainer.climberSubsystem::unslap, robotContainer.climberSubsystem))
        ),
        () -> robotContainer.climberSubsystem.slapper.get() == DoubleSolenoid.Value.kForward
    );
  }
}
