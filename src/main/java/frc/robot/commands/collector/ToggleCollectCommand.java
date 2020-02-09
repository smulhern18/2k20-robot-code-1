package frc.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.CollectorSubsystem;

public class ToggleCollectCommand extends CommandBase {
  CollectorSubsystem collectorSubsystem;
  BallPathSubsystem ballPathSubsystem;

  public ToggleCollectCommand(CollectorSubsystem collectorSubsystem, BallPathSubsystem ballPathSubsystem) {
    this.collectorSubsystem = collectorSubsystem;
    this.ballPathSubsystem = ballPathSubsystem;
  }

  @Override
  public void initialize() {
    if (collectorSubsystem.getState() == CollectorSubsystem.CollectorState.UNDEPLOYED)
      new CollectCommand(collectorSubsystem, ballPathSubsystem).schedule();
    else
      new StopCollectingCommand(collectorSubsystem, ballPathSubsystem).schedule();
  }
}
