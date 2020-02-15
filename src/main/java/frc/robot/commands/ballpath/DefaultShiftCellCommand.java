package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.ballpath.shift.*;
import frc.robot.commands.collector.ManualExhaustCommand;
import frc.robot.subsystems.BallPathSubsystem;

import java.util.Map;

public class DefaultShiftCellCommand extends SequentialCommandGroup {
  private BallPathSubsystem ballPathSubsystem;

  public DefaultShiftCellCommand(RobotContainer robotContainer) {
    ballPathSubsystem = robotContainer.ballPathSubsystem;
    addCommands(
        new SelectCommand(
            Map.ofEntries(
                Map.entry(0, new ShiftToFirstCommand(robotContainer).withTimeout(3)),
                Map.entry(1, new ShiftFirstToSecondCommand(robotContainer).withTimeout(3)),
                Map.entry(2, new ShiftSecondToThirdCommand(robotContainer).withTimeout(3)),
                Map.entry(3, new ShiftThirdToFourthCommand(robotContainer).withTimeout(3)),
                Map.entry(4, new ShiftFourthToFifthCommand(robotContainer).withTimeout(3)),
                Map.entry(5, new ManualExhaustCommand(robotContainer).withTimeout(3))
            ),
            this::select
        )
    );
  }

  public int select() {
    if (ballPathSubsystem.fifthCellBannerSensor.beamBroken()) {
      return 5;
    } else if (ballPathSubsystem.fourthCellBannerSensor.beamBroken()) {
      return 4;
    } else if (ballPathSubsystem.thirdCellBannerSensor.beamBroken()) {
      return 3;
    } else if (ballPathSubsystem.secondCellBannerSensor.beamBroken()) {
      return 2;
    } else if (ballPathSubsystem.firstCellBannerSensor.beamBroken()) {
      return 1;
    } else {
      return 0;
    }
  }
}
