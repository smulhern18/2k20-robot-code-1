package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TurretSubsystem;

public class TurretTo0Command extends CommandBase {
  private TurretSubsystem turretSubsystem;
  public TurretTo0Command(RobotContainer robotContainer) {
    turretSubsystem = robotContainer.turretSubsystem;
    addRequirements(turretSubsystem);
  }

  @Override
  public void execute() {
    turretSubsystem.rotateToPosition(0);
  }
}
