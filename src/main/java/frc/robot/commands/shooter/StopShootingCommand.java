package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class StopShootingCommand extends CommandBase {
  private ShooterSubsystem shooterSubsystem;
  private BallPathSubsystem ballPathSubsystem;

  public StopShootingCommand(RobotContainer robotContainer) {
    shooterSubsystem = robotContainer.shooterSubsystem;
    ballPathSubsystem = robotContainer.ballPathSubsystem;
    addRequirements(shooterSubsystem, ballPathSubsystem);
  }

  @Override
  public void initialize() {
    shooterSubsystem.stop();
    ballPathSubsystem.stopAll();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
