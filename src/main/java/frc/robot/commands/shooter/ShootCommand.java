package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * Shoots at current target RPM.
 */
public class ShootCommand extends CommandBase {
  ShooterSubsystem shooterSubsystem;
  BallPathSubsystem ballPathSubsystem;

  public ShootCommand(ShooterSubsystem shooterSubsystem, BallPathSubsystem ballPathSubsystem) {
    this.shooterSubsystem = shooterSubsystem;
    this.ballPathSubsystem = ballPathSubsystem;
    addRequirements(shooterSubsystem, ballPathSubsystem);
  }

  @Override
  public void initialize() {
    // target RPM is currently 0 unless set prior
    shooterSubsystem.shoot();
  }

  @Override
  public void execute() {
    if (shooterSubsystem.atTargetRPM()) {
      ballPathSubsystem.shoot();
    } else {
      ballPathSubsystem.stopAll();
    }
    shooterSubsystem.shoot();
  }

  @Override
  public boolean isFinished() {
    return !ballPathSubsystem.getAnyBannerSensor();
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stop();
    ballPathSubsystem.stopAll();
  }
}
