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
        2,
        "turret error"
    );
    turretSubsystem = robotContainer.turretSubsystem;
    this.setpoint = setpoint;
    addRequirements(turretSubsystem);
  }

  @Override
  public void usePIDOutput(double output) {
    turretSubsystem.manualRotateTurret(-output);
//    System.out.println(setpoint);
//    System.out.println(turretSubsystem.getCurrentPositionDegrees());
  }

  @Override
  public double getInput() {
    return turretSubsystem.getCurrentPositionDegrees();
  }

  @Override
  public double getSetpoint() {
    return setpoint;
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("PID aiming done");
  }
}
