package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class AutoSetRPMCommand extends CommandBase {
  ShooterSubsystem shooterSubsystem;
  VisionSubsystem visionSubsystem;

  public AutoSetRPMCommand(RobotContainer robotContainer) {
    shooterSubsystem = robotContainer.shooterSubsystem;
    visionSubsystem = robotContainer.visionSubsystem;
    addRequirements(robotContainer.shooterSubsystem);
  }

  @Override
  public void initialize() {
    shooterSubsystem.setTargetRPM(shooterSubsystem.inchesToRPM(visionSubsystem.getDistanceToTarget()));
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
