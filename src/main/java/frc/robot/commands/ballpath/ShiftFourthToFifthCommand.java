package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;

public class ShiftFourthToFifthCommand extends CommandBase {
  BallPathSubsystem ballPathSubsystem;

  public ShiftFourthToFifthCommand(RobotContainer robotContainer) {
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
    return ballPathSubsystem.fifthCellBannerSensor.beamBroken();
  }

  @Override
  public void end(boolean interrupted) {
    ballPathSubsystem.stopAll();
  }
}
