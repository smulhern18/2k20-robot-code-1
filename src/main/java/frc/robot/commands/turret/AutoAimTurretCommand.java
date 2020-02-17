package frc.robot.commands.turret;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

/**
 * Aims turret approximately where the port should be, then locks on
 */
public class AutoAimTurretCommand extends CommandBase {
  private final TurretSubsystem turretSubsystem;
  private final VisionSubsystem visionSubsystem;
  private final DrivetrainSubsystem drivetrainSubsystem;//need for odometry

  private double targetPosition;

  public AutoAimTurretCommand(RobotContainer robotContainer) {
    this.turretSubsystem = robotContainer.turretSubsystem;
    this.visionSubsystem = robotContainer.visionSubsystem;
    //drivetrain not required because it's only being used to read from NavX
    this.drivetrainSubsystem = robotContainer.drivetrainSubsystem;
    addRequirements(turretSubsystem, visionSubsystem);
  }

  /**
   * Turn on vision
   */
  @Override
  public void initialize() {
    visionSubsystem.setLightRing(true);
  }

  /**
   * Turrets to 0 drivetrain heading until it finds vision target
   */
  @Override
  public void execute() {
    if (visionSubsystem.getTargetFound()) {
      targetPosition = Units.degreesToRadians(Constants.TurretConstants.MAX_ROTATION_DEGREES / 2.0) + visionSubsystem.getAngleToTarget();
      turretSubsystem.rotateToPosition(targetPosition);//adjusts for middle offset
    } else {//currently unsure with where to point shooter with no vision
      targetPosition = drivetrainSubsystem.getYawDegrees();
      turretSubsystem.resetTargetWithDrivetrain(targetPosition);
    }
  }

  /**
   * Finishes when locked onto port
   *
   * @return true if finished
   */
  @Override
  public boolean isFinished() {
    return turretSubsystem.inPosition(targetPosition) && visionSubsystem.getTargetFound();
  }

  /**
   * Turn off vision
   *
   * @param interrupted
   */
  @Override
  public void end(boolean interrupted) {
    turretSubsystem.rotateToPosition(turretSubsystem.getCurrentPositionDegrees());
    visionSubsystem.setLightRing(false);
  }
}
