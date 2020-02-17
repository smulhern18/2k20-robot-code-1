package frc.robot.commands.ballpath.shift;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;

public class ShiftThirdToFourthCommand extends CommandBase {
  BallPathSubsystem ballPathSubsystem;

  public ShiftThirdToFourthCommand(RobotContainer robotContainer) {
    this.ballPathSubsystem = robotContainer.ballPathSubsystem;
    addRequirements(ballPathSubsystem);
  }

  @Override
  public void execute() {
    ballPathSubsystem.runIndexer();
  }

  @Override
  public boolean isFinished() {
    return ballPathSubsystem.fourthCellBannerSensor.beamBroken() ||
        ballPathSubsystem.fifthCellBannerSensor.beamBroken();
  }

  @Override
  public void end(boolean interrupted) {
    ballPathSubsystem.stopAll();
  }
}
