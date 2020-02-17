package frc.robot.commands.shooter;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * Shoots at current target RPM.
 */
public class ManualShootCommand extends CommandBase {
  private ShooterSubsystem shooterSubsystem;
  private double rpm;
  NetworkTableEntry rpmEntry;

  public ManualShootCommand(RobotContainer robotContainer, double rpm) {
    this.shooterSubsystem = robotContainer.shooterSubsystem;
    this.rpm = rpm;
    addRequirements(shooterSubsystem);
    rpmEntry = Constants.SubsystemConstants.DEBUG_TAB.add("rpm", 0).getEntry();
  }

  /**
   * Only feed balls if at target RPM
   */
  @Override
  public void execute() {
//    shooterSubsystem.shoot(rpm);
    shooterSubsystem.shoot(rpmEntry.getDouble(0));
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stop();
    System.out.println("stop");
  }
}
