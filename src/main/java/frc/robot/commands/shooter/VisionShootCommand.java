package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

/**
 * Shoots based on the vision retrieved distance
 */
public class VisionShootCommand extends CommandBase {
  private VisionSubsystem visionSubsystem;
  private ShooterSubsystem shooterSubsystem;
  private BallPathSubsystem ballPathSubsystem;
  private double targetRPM;

  /**
   * Creates a new VisionShootCommand
   * @param robotContainer
   */
  public VisionShootCommand(RobotContainer robotContainer) {
    visionSubsystem = robotContainer.visionSubsystem;
    shooterSubsystem = robotContainer.shooterSubsystem;
    ballPathSubsystem = robotContainer.ballPathSubsystem;
    addRequirements(shooterSubsystem, ballPathSubsystem);
  }

  @Override
  public void initialize(){
    targetRPM = shooterSubsystem.inchesToRPM(visionSubsystem.getDistanceToTarget());
  }

  /**
   * Gets the distance to the target
   * Revs the shooter to the correct rpm
   * Shoots the ball when Ready
   */
  @Override
  public void execute() {
    shooterSubsystem.shoot(targetRPM);
    if (shooterSubsystem.atTargetRPM(targetRPM)) {
      ballPathSubsystem.runAll();
    } else {
      ballPathSubsystem.stopAll();
    }
  }

  /**
   * When the ball is out of balls, stop
   * @return whether the balls in the robot is equal to 0
   */
  @Override
  public boolean isFinished() {
    return ballPathSubsystem.getBallsInRobot() == 0;
  }

  /**
   * At the the end, stop the shooter
   * @param interrupted
   */
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stop();
  }
}
