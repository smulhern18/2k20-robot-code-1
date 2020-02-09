package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class AutoAimTurretCommand extends CommandBase {
  private final TurretSubsystem turretSubsystem;
  private final VisionSubsystem visionSubsystem;
  private final DrivetrainSubsystem drivetrainSubsystem;//need for odometry

  public AutoAimTurretCommand(TurretSubsystem turretSubsystem, VisionSubsystem visionSubsystem, DrivetrainSubsystem drivetrainSubsystem) {
    this.turretSubsystem = turretSubsystem;
    this.visionSubsystem = visionSubsystem;
    //drivetrain not required because it's only being used to read from NavX
    this.drivetrainSubsystem = drivetrainSubsystem;
    addRequirements(turretSubsystem, visionSubsystem);
  }

  @Override
  public void initialize() {
    visionSubsystem.setLightRing(true);
  }

  @Override
  public void execute() {
    if (visionSubsystem.getTargetFound()) {
      turretSubsystem.setTargetPosition(visionSubsystem.getAngleToTarget());
    } else {//currently unsure with where to point shooter with no vision
      turretSubsystem.setTargetPosition(-1.0 * drivetrainSubsystem.getYawDegrees());//check logic later with real turret
    }
  }

  @Override
  public boolean isFinished() {
    return turretSubsystem.inPosition();
  }

  @Override
  public void end(boolean interrupted) {
    turretSubsystem.setTargetPosition(0);
    visionSubsystem.setLightRing(false);
  }
}
