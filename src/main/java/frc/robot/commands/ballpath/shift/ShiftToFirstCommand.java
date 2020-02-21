package frc.robot.commands.ballpath.shift;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;

public class ShiftToFirstCommand extends CommandBase {
  BallPathSubsystem ballPathSubsystem;

  public ShiftToFirstCommand(RobotContainer robotContainer) {
    this.ballPathSubsystem = robotContainer.ballPathSubsystem;
    addRequirements(ballPathSubsystem);
  }

  /**
   * Runs the indexer
   */
  @Override
  public void execute() {
    ballPathSubsystem.runIndexer();
  }

  /**
   * Stops when any banner sensor is triggered, either the intended one, or any past it
   *
   * @return if the lead ball has reached or passed its intended point
   */
  @Override
  public boolean isFinished() {
    return ballPathSubsystem.firstCellBannerSensor.beamBroken() || ballPathSubsystem.secondCellBannerSensor.beamBroken() ||
        ballPathSubsystem.thirdCellBannerSensor.beamBroken() || ballPathSubsystem.fourthCellBannerSensor.beamBroken() ||
        ballPathSubsystem.fifthCellBannerSensor.beamBroken();
  }

  @Override
  public void end(boolean interrupted) {
    ballPathSubsystem.stopAll();
  }
}
