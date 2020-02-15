package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * Shoots at current target RPM.
 */
public class ManualShootCommand extends CommandBase {
  private ShooterSubsystem shooterSubsystem;
  private double rpm;

  public ManualShootCommand(RobotContainer robotContainer, double rpm) {
    this.shooterSubsystem = robotContainer.shooterSubsystem;
    this.rpm = rpm;
    addRequirements(shooterSubsystem);
  }

  /**
   * Only feed balls if at target RPM
   */
  @Override
  public void execute() {
    shooterSubsystem.shoot(rpm);
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stop();
  }
}
