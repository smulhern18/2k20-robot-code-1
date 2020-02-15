package frc.robot.commands.ballpath.shift;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;

public class ShiftSecondToThirdCommand extends CommandBase {
  BallPathSubsystem ballPathSubsystem;

  public ShiftSecondToThirdCommand(RobotContainer robotContainer) {
    this.ballPathSubsystem = robotContainer.ballPathSubsystem;
    addRequirements(ballPathSubsystem);
  }

  @Override
  public void execute() {
    ballPathSubsystem.indexIn();
    ballPathSubsystem.runBelt();
  }

  @Override
  public boolean isFinished() {
    return ballPathSubsystem.thirdCellBannerSensor.beamBroken() || ballPathSubsystem.fourthCellBannerSensor.beamBroken() ||
        ballPathSubsystem.fifthCellBannerSensor.beamBroken();
  }

  @Override
  public void end(boolean interrupted) {
    ballPathSubsystem.stopAll();
  }
}
