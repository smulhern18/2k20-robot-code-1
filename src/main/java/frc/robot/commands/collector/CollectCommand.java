package frc.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.abrahamblinkin.ChangeHatCommand;
import frc.robot.subsystems.AbrahamBlinkinSubsystem;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.CollectorSubsystem;

/**
 * Press once, collector deploys, intake wheels go
 * once 5 balls are in robot, intake wheels stop, undeploys
 */
public class CollectCommand extends CommandBase {
  private CollectorSubsystem collectorSubsystem;
  private BallPathSubsystem ballPathSubsystem;
  private RobotContainer robotContainer;

  public CollectCommand(RobotContainer robotContainer) {
    this.collectorSubsystem = robotContainer.collectorSubsystem;
    this.ballPathSubsystem = robotContainer.ballPathSubsystem;
    // does not require ball path, as it is only used for sensors.
    addRequirements(collectorSubsystem);
  }

  /**
   * Deploys intake and starts motors
   */
  @Override
  public void initialize() {
    collectorSubsystem.deploy();
    collectorSubsystem.intake();
  }

  /**
   * Confirms that five balls are in robot
   *
   * @return boolean: true if five balls are in robot
   */
  @Override
  public boolean isFinished() {
    return ballPathSubsystem.getBallsInRobot() == 5;
  }

  /**
   * Terminates collector state-change process
   *
   * @param interrupted true if state change was interrupted
   */
  @Override
  public void end(boolean interrupted) {
    System.out.println("Collect end");
    if (!interrupted) {
      collectorSubsystem.stopIntake();
      collectorSubsystem.undeploy();
    }
    collectorSubsystem.stopIntake();
  }
}
