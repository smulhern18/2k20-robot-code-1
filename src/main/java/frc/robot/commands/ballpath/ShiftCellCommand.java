package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.ballpath.shift.*;
import frc.robot.commands.collector.ManualExhaustCommand;
import frc.robot.subsystems.BallPathSubsystem;

public class ShiftCellCommand extends CommandBase {
  private BallPathSubsystem ballPathSubsystem;
  private RobotContainer robotContainer;

  public ShiftCellCommand(RobotContainer robotContainer) {
    this.ballPathSubsystem = robotContainer.ballPathSubsystem;
    this.robotContainer = robotContainer;
  }

  @Override
  public void initialize() {
    if (ballPathSubsystem.fifthCellBannerSensor.beamBroken()) {
      new ManualExhaustCommand(robotContainer).withTimeout(3).schedule();
    } else if (ballPathSubsystem.fourthCellBannerSensor.beamBroken()) {
      new ShiftFourthToFifthCommand(robotContainer).withTimeout(3).schedule();
    } else if (ballPathSubsystem.thirdCellBannerSensor.beamBroken()) {
      new ShiftThirdToFourthCommand(robotContainer).withTimeout(3).schedule();
    } else if (ballPathSubsystem.secondCellBannerSensor.beamBroken()) {
      new ShiftSecondToThirdCommand(robotContainer).withTimeout(3).schedule();
    } else if (ballPathSubsystem.firstCellBannerSensor.beamBroken()) {
      new ShiftFirstToSecondCommand(robotContainer).withTimeout(3).schedule();
    } else {
      new ShiftToFirstCommand(robotContainer).withTimeout(3).schedule();
    }
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
