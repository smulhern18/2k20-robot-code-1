package frc.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CollectorSubsystem;

public class IntakeCommand extends CommandBase {
  CollectorSubsystem collectorSubsystem;
  public IntakeCommand(RobotContainer robotContainer) {
    collectorSubsystem = robotContainer.collectorSubsystem;
    addRequirements(collectorSubsystem);
  }

  @Override
  public void initialize() {
    collectorSubsystem.deploy();
  }

  @Override
  public void execute() {
    collectorSubsystem.intake();
  }
}
