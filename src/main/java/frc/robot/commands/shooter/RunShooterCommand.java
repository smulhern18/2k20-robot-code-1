package frc.robot.commands.shooter;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * Shoots at current target RPM.
 */
public class RunShooterCommand extends CommandBase {
  NetworkTableEntry rpmEntry;
  private ShooterSubsystem shooterSubsystem;
  private double rpm;

  /**
   * Creates a new ManualShootCommand
   * @param robotContainer
   * @param rpm
   */
  public RunShooterCommand(RobotContainer robotContainer, double rpm) {
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
//    shooterSubsystem.shoot(rpmEntry.getDouble(0));
  }

  /**
   * At the end stops the shooter
   * @param interrupted
   */
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stop();
    System.out.println("stop");
  }
}
