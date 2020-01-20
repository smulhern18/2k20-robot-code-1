package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * Trajectory following class
 */
public class TrajectoryFollowerCommand extends RamseteCommand {
  /**
   * Creates a Ramsete command that follows the given trajectory
   * @param trajectory trajectory to follow
   * @param drivetrain Drivetrain subsystem
   */
  public TrajectoryFollowerCommand(Trajectory trajectory, DrivetrainSubsystem drivetrain) {
    super(trajectory,
        drivetrain::getPose,
        new RamseteController(DrivetrainConstants.RAMSETE_B, DrivetrainConstants.RAMSETE_ZETA),
        DrivetrainConstants.DRIVE_FEED_FORWARD,
        DrivetrainConstants.DRIVE_KINEMATICS,
        drivetrain::getWheelSpeeds,
        new PIDController(DrivetrainConstants.P_ENCODER_GAIN, 0, 0),
        new PIDController(DrivetrainConstants.P_ENCODER_GAIN, 0, 0),
        drivetrain::driveVolts,
        drivetrain);
  }
}
