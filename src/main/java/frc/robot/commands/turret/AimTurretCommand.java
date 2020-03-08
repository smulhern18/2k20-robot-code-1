package frc.robot.commands.turret;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.BeefPIDCommandBase;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class AimTurretCommand extends BeefPIDCommandBase {
  private TurretSubsystem turretSubsystem;
  private VisionSubsystem visionSubsystem;

  public AimTurretCommand(RobotContainer robotContainer) {
    super(Constants.TurretConstants.P, 0, 0, 0, "vision aim");
    turretSubsystem = robotContainer.turretSubsystem;
    visionSubsystem = robotContainer.visionSubsystem;
    addRequirements(turretSubsystem, visionSubsystem);
  }

  @Override
  public void initialize() {
    super.initialize();
    visionSubsystem.setLightRing(true);
  }

  @Override
  public void usePIDOutput(double output) {
    turretSubsystem.manualRotateTurret(-output);
  }

  @Override
  public double getInput() {
    if (visionSubsystem.getAngleToTarget() == 0) {
      return 0;
    }
    System.out.println(-(visionSubsystem.getAngleToTarget()-320));
    return -(visionSubsystem.getAngleToTarget() - 320);
  }

  @Override
  public double getSetpoint() {
    return 0;
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    visionSubsystem.setLightRing(false);
  }
}
