package frc.robot.commands.turret;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
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
      turretSubsystem.rotateToPosition(Units.degreesToRadians(Constants.TurretConstants.MAX_CAPABILITY_DEGREES / 2.0)+ visionSubsystem.getAngleToTarget());//adjusts for middle offset
    } else {//currently unsure with where to point shooter with no vision
      turretSubsystem.setTargetPosition(-1.0 * drivetrainSubsystem.getYawDegrees());//TODO check with odomoetry for -180 to 180 or 0 to 360
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
