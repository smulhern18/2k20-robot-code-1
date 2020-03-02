package frc.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CollectorSubsystem;

public class DeployCollectorCommand extends CommandBase {
  private boolean done = false;
  private CollectorSubsystem collectorSubsystem;
  public DeployCollectorCommand(RobotContainer robotContainer) {
    collectorSubsystem = robotContainer.collectorSubsystem;
    addRequirements(collectorSubsystem);
  }

  @Override
  public void initialize() {
    done = collectorSubsystem.getState() == CollectorSubsystem.CollectorState.DEPLOYED;
    collectorSubsystem.deploy();
  }

  @Override
  public boolean isFinished() {
    return done;
  }
}
