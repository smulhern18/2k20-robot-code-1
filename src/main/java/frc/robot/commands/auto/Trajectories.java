package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import frc.robot.Constants.DrivetrainConstants;

/**
 * Superclass for auto trajectory lists.
 */
public abstract class Trajectories {
  public final static TrajectoryConfig FORWARD_CONFIG = createTrajectoryConfig(false);
  public final static TrajectoryConfig BACKWARD_CONFIG = createTrajectoryConfig(true);

  /**
   * Creates a new trajectory config
   *
   * @param reversed backwards or not
   * @return a new trajectory config
   */
  private static TrajectoryConfig createTrajectoryConfig(boolean reversed) {
    final TrajectoryConfig config = new TrajectoryConfig(
        DrivetrainConstants.MAX_SPEED_METERS_PER_SECOND,
        DrivetrainConstants.MAX_ACCELERATION_METERS_PER_SECOND_SQUARED);
    config.setKinematics(DrivetrainConstants.DRIVE_KINEMATICS);
    config.addConstraint(DrivetrainConstants.AUTO_VOLTAGE_CONSTRAINT);
    config.setReversed(reversed);
    return config;
  }
}
