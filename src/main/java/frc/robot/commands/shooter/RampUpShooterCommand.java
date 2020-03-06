package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

public class RampUpShooterCommand  extends CommandBase {
  ShooterSubsystem shooterSubsystem;
  double rpm;
  public RampUpShooterCommand(RobotContainer robotContainer, double rpm) {
    shooterSubsystem = robotContainer.shooterSubsystem;
    this.rpm = rpm;
    addRequirements(shooterSubsystem);
  }

  @Override
  public void execute() {
    shooterSubsystem.shoot(rpm);
  }

  @Override
  public boolean isFinished() {
    return shooterSubsystem.atTargetRPM(rpm);
  }
}
