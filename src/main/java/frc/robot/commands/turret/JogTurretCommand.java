package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class JogTurretCommand extends CommandBase {
  private final TurretSubsystem turretSubsystem;
  TurretSubsystem.TurretDirection direction;

  public JogTurretCommand(TurretSubsystem.TurretDirection direction) {
    turretSubsystem = TurretSubsystem.getInstance();
    this.direction = direction;
    addRequirements(turretSubsystem);
  }

  @Override
  public void initialize() {
    turretSubsystem.setDirection(direction);
  }

  @Override
  public void end(boolean interrupted) {
    turretSubsystem.setOff();
  }
}
