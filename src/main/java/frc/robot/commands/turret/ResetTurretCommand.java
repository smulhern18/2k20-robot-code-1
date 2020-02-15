package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TurretSubsystem;

/**
 * Points turret in-line with chassis
 */
public class ResetTurretCommand extends CommandBase {
  TurretSubsystem turretSubsystem;

  public ResetTurretCommand(RobotContainer robotContainer) {
    turretSubsystem = robotContainer.turretSubsystem;
    addRequirements(turretSubsystem);
  }

  /**
   * Sets target position to 0
   */
  @Override
  public void initialize() {
    turretSubsystem.rotateToPosition(0);
  }

  /**
   * Runs PID position control
   */
  @Override
  public void execute() {
    turretSubsystem.rotateToPosition(0);
  }

  /**
   * Sees if target is in-line
   *
   * @return true if zeroed
   */
  @Override
  public boolean isFinished() {
    return turretSubsystem.inPosition(0);
  }
}
