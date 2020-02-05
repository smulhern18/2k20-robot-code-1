package frc.robot.commands.auto.thief.oppositetwo;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class OppositeTwoTrajectories extends Trajectories {
  private static final Pose2d SHOOT_START = new Pose2d(9.978, -5.139, new Rotation2d(Math.toRadians(27.80)));
  public static final Trajectory OPPOSITE_TWO_GRAB = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(4.277, -7.421, new Rotation2d(Math.toRadians(0.00))),
          new Pose2d(6.082, -6.425, new Rotation2d(Math.toRadians(19.60))),
          SHOOT_START
      ),
      FORWARD_CONFIG);

  public static final Trajectory OPPOSITE_TWO_SHOOT = TrajectoryGenerator.generateTrajectory(
      List.of(
          SHOOT_START,
          new Pose2d(5.971, -5.263, new Rotation2d(Math.toRadians(0.00)))
      ),
      BACKWARD_CONFIG);
}
