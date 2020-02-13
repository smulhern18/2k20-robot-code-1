package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TurretSubsystem;

public class ResetTurretCommand extends CommandBase {
  TurretSubsystem turretSubsystem;

  public ResetTurretCommand(RobotContainer robotContainer) {
    turretSubsystem = robotContainer.turretSubsystem;
    addRequirements(turretSubsystem);

  }

  @Override
  public void initialize() {
    turretSubsystem.setTargetPosition(0);
  }

  @Override
  public void execute() {
    turretSubsystem.rotateToTarget();
  }

  @Override
  public boolean isFinished() {
    return turretSubsystem.inPosition();
  }
}
