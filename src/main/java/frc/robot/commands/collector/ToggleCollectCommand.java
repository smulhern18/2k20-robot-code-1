package frc.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.CollectorSubsystem;

public class ToggleCollectCommand extends CommandBase {
  CollectorSubsystem collectorSubsystem;
  BallPathSubsystem ballPathSubsystem;

  public ToggleCollectCommand() {
    this.collectorSubsystem = CollectorSubsystem.getInstance();
    this.ballPathSubsystem = BallPathSubsystem.getInstance();
  }

  @Override
  public void initialize() {
    if (collectorSubsystem.getState() == CollectorSubsystem.CollectorState.UNDEPLOYED)
      new CollectCommand().schedule();
    else
      new StopCollectingCommand().schedule();
  }
}
