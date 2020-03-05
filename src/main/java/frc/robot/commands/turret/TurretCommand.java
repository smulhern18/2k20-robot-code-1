package frc.robot.commands.turret;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.BeefPIDCommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class TurretCommand extends BeefPIDCommandBase {
  private TurretSubsystem turretSubsystem;
  private double setpoint;

  public TurretCommand(RobotContainer robotContainer, double setpoint) {
    super(
        Constants.TurretConstants.P,
        Constants.TurretConstants.I,
        Constants.TurretConstants.D,
        0,
        "turret error"
    );
    turretSubsystem = robotContainer.turretSubsystem;
    this.setpoint = setpoint;
  }

  @Override
  public void usePIDOutput(double output) {
    turretSubsystem.manualRotateTurret(output);
  }

  @Override
  public double getInput() {
    return turretSubsystem.getCurrentPositionDegrees();
  }

  @Override
  public double getSetpoint() {
    return setpoint;
  }
}
