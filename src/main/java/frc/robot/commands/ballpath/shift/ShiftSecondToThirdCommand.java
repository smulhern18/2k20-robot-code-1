package frc.robot.commands.ballpath.shift;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;

/**
 * Shifts the ball path to create space and index for three balls
 */
public class ShiftSecondToThirdCommand extends CommandBase {
  BallPathSubsystem ballPathSubsystem;
  boolean targetHit = false;

  public ShiftSecondToThirdCommand(RobotContainer robotContainer) {
    this.ballPathSubsystem = robotContainer.ballPathSubsystem;
    addRequirements(ballPathSubsystem);
  }

  /**
   * Runs the indexer
   */
  @Override
  public void execute() {
    ballPathSubsystem.manualBelt();
  }

  /**
   * Stops when any banner sensor is triggered, either the intended one, or any past it
   *
   * @return if the lead ball has reached or passed its intended point
   */
  @Override
  public boolean isFinished() {
    targetHit |=
        ballPathSubsystem.secondCellBannerSensor.beamBroken() || ballPathSubsystem.firstCellBannerSensor.beamBroken();
    return targetHit && !ballPathSubsystem.beltBannerSensor.beamBroken();
  }

  @Override
  public void end(boolean interrupted) {
    ballPathSubsystem.incrementBalls();
    ballPathSubsystem.stopAll();
  }
}
