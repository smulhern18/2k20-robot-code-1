package frc.robot.commands.auto.test;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

/**
 * Trajectories specifically used for testing.
 */
public final class TestTrajectories extends Trajectories {
  // Quarter circle left
  public final static Trajectory FORWARD = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(0, 0, new Rotation2d(0)),
          new Pose2d(2, 0, new Rotation2d(Units.degreesToRadians(0)))),
      FORWARD_CONFIG);

  // Quarter circle back right
  public final static Trajectory BACKWARD = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(0, 0, new Rotation2d(Units.degreesToRadians(0))),
          new Pose2d(-1, 0, new Rotation2d(0))),
      BACKWARD_CONFIG);

  public final static Trajectory S = TrajectoryGenerator.generateTrajectory(
      new Pose2d(0, 0, new Rotation2d(0)),
      List.of(
          new Translation2d(1, 1),
          new Translation2d(1.5, 0.0),
          new Translation2d(2, 1),
          new Translation2d(2.5, 0.0),
          new Translation2d(3, 1)
      ),
      new Pose2d(3.5, 0, new Rotation2d(0)),
      FORWARD_CONFIG
  );
}