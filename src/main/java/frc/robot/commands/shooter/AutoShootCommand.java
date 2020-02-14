package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class AutoShootCommand extends CommandBase {
  private VisionSubsystem visionSubsystem;
  private ShooterSubsystem shooterSubsystem;
  private BallPathSubsystem ballPathSubsystem;
  public AutoShootCommand(RobotContainer robotContainer) {
    visionSubsystem = robotContainer.visionSubsystem;
    shooterSubsystem = robotContainer.shooterSubsystem;
    ballPathSubsystem = robotContainer.ballPathSubsystem;
    addRequirements(shooterSubsystem);
  }

  @Override
  public void execute() {
    double targetRPM = shooterSubsystem.inchesToRPM(visionSubsystem.getDistanceToTarget());
    shooterSubsystem.shoot(targetRPM);
  }

  @Override
  public boolean isFinished() {
    return ballPathSubsystem.getAnyBannerSensor();
  }
}
