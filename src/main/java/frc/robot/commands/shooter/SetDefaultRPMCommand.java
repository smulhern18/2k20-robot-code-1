package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

public class SetDefaultRPMCommand extends CommandBase {
  ShooterSubsystem shooterSubsystem;
  public SetDefaultRPMCommand(RobotContainer robotContainer) {
    shooterSubsystem = robotContainer.shooterSubsystem;
    addRequirements(shooterSubsystem);
  }

  @Override
  public void initialize() {
    shooterSubsystem.setTargetRPM(Constants.ShooterConstants.DEFAULT_RPM);
    shooterSubsystem.shoot();
  }
}
