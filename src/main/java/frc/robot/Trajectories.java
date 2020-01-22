package frc.robot;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;

import java.util.List;

/**
 * Creates a key-value pair of trajectories. Cleans up code in other places.
 */
public final class Trajectories {
  private final static TrajectoryConfig FORWARD_CONFIG = createTrajectoryConfig(false);
  private final static TrajectoryConfig BACKWARD_CONFIG = createTrajectoryConfig(true);

  /**
   * Trajectories used specifically for testing
   */
  public static final class Test {
    // Quarter circle left
    public final static Trajectory FORWARD = TrajectoryGenerator.generateTrajectory(
        List.of(
            new Pose2d(0, 0, new Rotation2d(0)),
            new Pose2d(1, 0, new Rotation2d(Units.degreesToRadians(90)))),
        FORWARD_CONFIG);

    // Quarter circle back right
    public final static Trajectory BACKWARD = TrajectoryGenerator.generateTrajectory(
        List.of(
            new Pose2d(1, 0, new Rotation2d(Units.degreesToRadians(90))),
            new Pose2d(0, 0, new Rotation2d(0))),
        BACKWARD_CONFIG);
  }

  /**
   * Creates a new trajectory config
   *
   * @param reversed backwards or not
   * @return a new trajectory config
   */
  private static TrajectoryConfig createTrajectoryConfig(boolean reversed) {
    TrajectoryConfig config = new TrajectoryConfig(
        Constants.DrivetrainConstants.MAX_SPEED_METERS_PER_SECOND,
        Constants.DrivetrainConstants.MAX_ACCELERATION_METERS_PER_SECOND_SQUARED);
    config.setKinematics(Constants.DrivetrainConstants.DRIVE_KINEMATICS);
    config.addConstraint(Constants.DrivetrainConstants.AUTO_VOLTAGE_CONSTRAINT);
    config.setReversed(reversed);
    return config;
  }
}
