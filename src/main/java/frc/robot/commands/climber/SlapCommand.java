package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.turret.TurretCommand;

public class SlapCommand extends SequentialCommandGroup {
  public SlapCommand(RobotContainer robotContainer) {
    addCommands(
//        new TurretCommand(robotContainer, 0).withTimeout(2),
        new InstantCommand(robotContainer.climberSubsystem::slap, robotContainer.climberSubsystem),
        new PrintCommand("slapping")
    );
  }
}
