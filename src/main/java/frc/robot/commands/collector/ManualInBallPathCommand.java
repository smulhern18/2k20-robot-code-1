package frc.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.CollectorSubsystem;

public class ManualInBallPathCommand extends CommandBase {
  CollectorSubsystem collectorSubsystem;
  BallPathSubsystem ballPathSubsystem;
  public ManualInBallPathCommand(RobotContainer robotContainer) {
    collectorSubsystem = robotContainer.collectorSubsystem;
    ballPathSubsystem = robotContainer.ballPathSubsystem;
    addRequirements(collectorSubsystem, ballPathSubsystem);
  }

  @Override
  public void initialize() {
//    ballPathSubsystem.manualBelt();
    collectorSubsystem.intake();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
