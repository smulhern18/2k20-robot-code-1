package frc.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CollectorSubsystem;

public class LiftCollectorCommand extends CommandBase {
  private boolean done = false;
  private CollectorSubsystem collectorSubsystem;
  public LiftCollectorCommand(RobotContainer robotContainer) {
    collectorSubsystem = robotContainer.collectorSubsystem;
    addRequirements(collectorSubsystem);
  }

  @Override
  public void initialize() {
    done = collectorSubsystem.getState() == CollectorSubsystem.CollectorState.UNDEPLOYED;
    collectorSubsystem.undeploy();
    collectorSubsystem.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return done;
  }
}
