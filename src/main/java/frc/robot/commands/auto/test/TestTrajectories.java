package frc.robot.commands.auto.test;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
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
          new Pose2d(1, 0, new Rotation2d(Units.degreesToRadians(90)))),
      FORWARD_CONFIG);

  // Quarter circle back right
  public final static Trajectory BACKWARD = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(1, 0, new Rotation2d(Units.degreesToRadians(90))),
          new Pose2d(0, 0, new Rotation2d(0))),
      BACKWARD_CONFIG);
}