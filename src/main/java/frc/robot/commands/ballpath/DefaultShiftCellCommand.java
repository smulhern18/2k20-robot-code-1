package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.abrahamblinkin.ChangeHatCommand;
import frc.robot.commands.ballpath.shift.*;
import frc.robot.commands.collector.ManualExhaustCommand;
import frc.robot.subsystems.AbrahamBlinkinSubsystem;

import java.util.Map;

public class DefaultShiftCellCommand extends SelectCommand {

  public DefaultShiftCellCommand(RobotContainer robotContainer) {
    super(
        Map.ofEntries(
            Map.entry(0, new ShiftToFirstCommand(robotContainer).withTimeout(5)),
            Map.entry(1, new ShiftFirstToSecondCommand(robotContainer).withTimeout(4)),
            Map.entry(2, new ShiftSecondToThirdCommand(robotContainer).withTimeout(5)),
            Map.entry(3, new ShiftThirdToFourthCommand(robotContainer).withTimeout(5)),
            Map.entry(4, new ShiftFourthToFifthCommand(robotContainer).withTimeout(1)),
            Map.entry(5, new ChangeHatCommand(robotContainer, AbrahamBlinkinSubsystem.Hat.RainbowParty).withTimeout(3))
        ),
        robotContainer.ballPathSubsystem::getBallsInRobot
    );
  }

  @Override
  public boolean isFinished() {
    return super.isFinished();
  }
}
