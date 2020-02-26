package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


/**
 * Stop the shooter
 */
public class StopShootingCommand extends CommandBase {
  private ShooterSubsystem shooterSubsystem;
  private BallPathSubsystem ballPathSubsystem;
  private Timer timer;

  public StopShootingCommand(RobotContainer robotContainer) {
    shooterSubsystem = robotContainer.shooterSubsystem;
    ballPathSubsystem = robotContainer.ballPathSubsystem;
    addRequirements(shooterSubsystem, ballPathSubsystem);
  }

  /**
   * Sets the ballpath and shooter to stop
   */
  @Override
  public void initialize() {
    shooterSubsystem.stop();
    ballPathSubsystem.stopAll();
  }

  /**
   * finishes instantly
   * @return true
   */
  @Override
  public boolean isFinished() {
    return true;
  }
}
