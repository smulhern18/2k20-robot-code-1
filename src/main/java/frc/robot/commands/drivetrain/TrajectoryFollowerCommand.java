package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * Trajectory following class
 */
public class TrajectoryFollowerCommand extends RamseteCommand {
  private DrivetrainSubsystem drivetrainSubsystem;

  /**
   * Creates a Ramsete command that follows the given trajectory
   *
   * @param trajectory trajectory to follow
   */
  public TrajectoryFollowerCommand(Trajectory trajectory) {
    super(trajectory,
        DrivetrainSubsystem.getInstance()::getPose,
        new RamseteController(DrivetrainConstants.RAMSETE_B, DrivetrainConstants.RAMSETE_ZETA),
        DrivetrainConstants.DRIVE_KINEMATICS,
        DrivetrainSubsystem.getInstance()::tankDriveVelocity,
        DrivetrainSubsystem.getInstance());
    this.drivetrainSubsystem = DrivetrainSubsystem.getInstance();
    addRequirements(drivetrainSubsystem);
  }

  /**
   * Starts preprogrammed trajectory code
   */
  @Override
  public void initialize() {
    super.initialize();
    drivetrainSubsystem.setBrake();
  }

  /**
   * Terminates preprogrammed trajectory code
   */
  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    drivetrainSubsystem.drive(0, 0);
    drivetrainSubsystem.setCoast();
  }
}
