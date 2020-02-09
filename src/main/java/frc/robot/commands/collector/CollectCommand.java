package frc.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.CollectorSubsystem;

public class CollectCommand extends CommandBase {
  private CollectorSubsystem collectorSubsystem;
  private BallPathSubsystem ballPathSubsystem;

  public CollectCommand(CollectorSubsystem collectorSubsystem, BallPathSubsystem ballPathSubsystem) {
    this.collectorSubsystem = collectorSubsystem;
    addRequirements(collectorSubsystem, ballPathSubsystem);
  }
  /**
   * Alternates between the two states of trenchability
   */
  @Override
  public void initialize() {
    switch (collectorSubsystem.getState()) {
      case UNDEPLOYED:
        collectorSubsystem.deploy();
        collectorSubsystem.intake();
        collectorSubsystem.setState(CollectorSubsystem.CollectorState.DEPLOYED);
        break;
      case DEPLOYED:
        collectorSubsystem.undeploy();
        collectorSubsystem.stopIntake();
        collectorSubsystem.setState(CollectorSubsystem.CollectorState.UNDEPLOYED);
        break;
      default:
        System.out.println("UNEXPECTED COLLECTOR STATE!");
        break;
    }
  }

  /** 
   * Confirms that the next trenchability state has been reached
   * @return boolean: true if the next state has been reached, else false 
  */
  @Override
  public boolean isFinished() {
    switch (collectorSubsystem.getState()) {
      case DEPLOYED:
        return ballPathSubsystem.isLoaded();
      case UNDEPLOYED:
        return true;
      default:
        System.out.println("UNEXPECTED COLLECTOR STATE isFinished");
        break;
    }
    return true;
  }

  /**
   * Terminates collector state-change process
   * 
   * @param interrupted true if state change was interrupted
   */
  @Override
  public void end(boolean interrupted) {
    switch (collectorSubsystem.getState()) {
      case DEPLOYED:
        if (!interrupted) {
          collectorSubsystem.stopIntake();
          collectorSubsystem.setState(CollectorSubsystem.CollectorState.UNDEPLOYED);
        }
        break;
      case UNDEPLOYED:
        // nothing
        break;
      default:
        System.out.println("UNEXPECTED COLLECTOR STATE isFinished");
        break;
    }
  }
}
