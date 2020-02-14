package frc.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.CollectorSubsystem;

/**
 * Press once, collector deploys, intake wheels go
 * once 5 balls are in robot, intake wheels stop, undeploys
 */
public class StopCollectingCommand extends CommandBase {
  private CollectorSubsystem collectorSubsystem;
  private BallPathSubsystem ballPathSubsystem;

  public StopCollectingCommand(RobotContainer robotContainer) {
    this.collectorSubsystem = robotContainer.collectorSubsystem;
    this.ballPathSubsystem = robotContainer.ballPathSubsystem;
    addRequirements(collectorSubsystem, ballPathSubsystem);
  }

  /**
   * Alternates between the two states of trenchability
   */
  @Override
  public void initialize() {
    collectorSubsystem.undeploy();
    collectorSubsystem.stopIntake();
    collectorSubsystem.setState(CollectorSubsystem.CollectorState.UNDEPLOYED);
    ballPathSubsystem.stopAll();
  }

  /**
   * Confirms that the next trenchability state has been reached
   *
   * @return boolean: true if the next state has been reached, else false
   */
  @Override
  public boolean isFinished() {
    return true;
  }
}
