package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TurretSubsystem;

public class JogTurretCommand extends CommandBase {
  private final TurretSubsystem turretSubsystem;
  TurretSubsystem.TurretDirection direction;

  /**
   * Creates a new JogTurretCommand
   * @param robotContainer
   * @param direction
   */
  public JogTurretCommand(RobotContainer robotContainer, TurretSubsystem.TurretDirection direction) {
    turretSubsystem = robotContainer.turretSubsystem;
    this.direction = direction;
    addRequirements(turretSubsystem);
  }

  /**
   * upon initialization sets the intended direction
   */
  @Override
  public void initialize() {
    turretSubsystem.setDirection(direction);
  }

  /**
   * At the end, the turret stops
   * @param interrupted
   */
  @Override
  public void end(boolean interrupted) {
    turretSubsystem.setOff();
  }
}
