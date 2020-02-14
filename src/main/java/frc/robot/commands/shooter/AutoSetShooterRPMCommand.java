package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

/**
 * Sets target RPM of shooter to be relative to calculated distance to port
 * <p>
 * DOES NOT turn on shooter wheel
 */
public class AutoSetShooterRPMCommand extends CommandBase {
  ShooterSubsystem shooterSubsystem;
  VisionSubsystem visionSubsystem;

  public AutoSetShooterRPMCommand(RobotContainer robotContainer) {
    shooterSubsystem = robotContainer.shooterSubsystem;
    visionSubsystem = robotContainer.visionSubsystem;
    // does not write to vision subsystem
    addRequirements(robotContainer.shooterSubsystem);
  }

  /**
   * Sets target RPM
   */
  @Override
  public void initialize() {
    shooterSubsystem.setTargetRPM(shooterSubsystem.inchesToRPM(visionSubsystem.getDistanceToTarget()));
  }

  /**
   * Instant command with a name
   *
   * @return
   */
  @Override
  public boolean isFinished() {
    return true;
  }
}
