package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class AutoAimTurretCommand extends CommandBase {
  private final TurretSubsystem turretSubsystem;
  private final VisionSubsystem visionSubsystem;
  private final DrivetrainSubsystem drivetrainSubsystem;//need for odometry

  public AutoAimTurretCommand() {
    this.turretSubsystem = TurretSubsystem.getInstance();
    this.visionSubsystem = VisionSubsystem.getInstance();
    //drivetrain not required because it's only being used to read from NavX
    this.drivetrainSubsystem = DrivetrainSubsystem.getInstance();
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
      turretSubsystem.resetTargetWithDrivetrain(drivetrainSubsystem.getYawDegrees());
    }
    turretSubsystem.rotateToTarget();
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
