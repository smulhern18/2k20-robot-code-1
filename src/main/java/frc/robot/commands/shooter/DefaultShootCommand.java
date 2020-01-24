package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.ShooterSubsystem;

/**
 * Reads velocity from Shuffleboard, runs shooter at that speed
 */
public class DefaultShootCommand extends CommandBase {

  private final ShooterSubsystem shooterSubsystem;
  private final String SETPOINT = "Shooter setpoint RPM";

  /**
   * Puts 0 rpm to shuffleboard
   *
   * @param shooterSubsystem the shooter subsystem
   */
  public DefaultShootCommand(ShooterSubsystem shooterSubsystem) {
    addRequirements(shooterSubsystem);
    this.shooterSubsystem = shooterSubsystem;
    SmartDashboard.putNumber(SETPOINT, shooterSubsystem.targetVelocity.getRPM());
  }

  /**
   * Reads current target, runs shooter to that velocity
   */
  @Override
  public void execute() {
    shooterSubsystem.setTargetRPM(SmartDashboard.getNumber(SETPOINT, 0));
    shooterSubsystem.shoot();
  }

  /**
   * Stops shooter wheel
   *
   * @param interrupted unused
   */
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stop();
  }
}