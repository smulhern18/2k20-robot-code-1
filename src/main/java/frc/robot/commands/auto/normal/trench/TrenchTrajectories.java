package frc.robot.commands.auto.normal.trench;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class TrenchTrajectories extends Trajectories {
  private static final Pose2d TRENCH_END_ONE = new Pose2d(-6.25, 0, new Rotation2d(0));
  public static final Trajectory GRAB = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(0, 0, new Rotation2d(0)),
          TRENCH_END_ONE),
      BACKWARD_CONFIG);

  public static final Trajectory RETURN = TrajectoryGenerator.generateTrajectory(
      List.of(
          TRENCH_END_ONE,
          new Pose2d(0, 0, new Rotation2d(0))),
      FORWARD_CONFIG);
}
