package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.ballpath.shift.*;
import frc.robot.commands.collector.ManualExhaustCommand;

import java.util.Map;

public class DefaultShiftCellCommand extends SelectCommand {

  public DefaultShiftCellCommand(RobotContainer robotContainer) {
    super(
        Map.ofEntries(
            Map.entry(0, new ShiftToFirstCommand(robotContainer).withTimeout(3)),
            Map.entry(1, new ShiftFirstToSecondCommand(robotContainer).withTimeout(3)),
            Map.entry(2, new ShiftSecondToThirdCommand(robotContainer).withTimeout(3)),
            Map.entry(3, new ShiftThirdToFourthCommand(robotContainer).withTimeout(3)),
            Map.entry(4, new ShiftFourthToFifthCommand(robotContainer).withTimeout(3)),
            Map.entry(5, new ManualExhaustCommand(robotContainer).withTimeout(3))
        ),
        robotContainer.ballPathSubsystem::getBallsInRobot
    );
  }
}
