package frc.robot.commands.auto.thief.oppositetwo;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class OppositeTwoTrajectories extends Trajectories {
  private static final Pose2d SHOOT_START = new Pose2d(-5.701, -2.282, new Rotation2d(Units.degreesToRadians(27.80)));
  public static final Trajectory OPPOSITE_TWO_GRAB = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(0, 0, new Rotation2d(Units.degreesToRadians(0.00))),
          new Pose2d(-1.805, -0.996, new Rotation2d(Units.degreesToRadians(19.60))),
          SHOOT_START
      ),
      BACKWARD_CONFIG);

  public static final Trajectory OPPOSITE_TWO_SHOOT = TrajectoryGenerator.generateTrajectory(
      List.of(
          SHOOT_START,
          new Pose2d(-1.694, -2.158, new Rotation2d(Units.degreesToRadians(0.00)))
      ),
      FORWARD_CONFIG);
}
