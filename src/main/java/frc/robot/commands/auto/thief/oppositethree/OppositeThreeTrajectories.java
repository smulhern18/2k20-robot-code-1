package frc.robot.commands.auto.thief.oppositethree;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class OppositeThreeTrajectories extends Trajectories {
  private static final Pose2d SHOOT_START = new Pose2d(-5.911, -2.876, new Rotation2d(Units.degreesToRadians(-68.20)));
  public static final Trajectory OPPOSITE_THREE_GRAB = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(0, 0, new Rotation2d(Units.degreesToRadians(0.00))),
          new Pose2d(-2.547, -1.8, new Rotation2d(Units.degreesToRadians(46.11))),
          new Pose2d(-4.971, -3.84, new Rotation2d(Units.degreesToRadians(0.00))),
          SHOOT_START
      ),
      BACKWARD_CONFIG);

  public static final Trajectory OPPOSITE_THREE_SHOOT = TrajectoryGenerator.generateTrajectory(
      List.of(
          SHOOT_START,
          new Pose2d(-1.323, -5.005, new Rotation2d(Units.degreesToRadians(0.00)))
      ),
      FORWARD_CONFIG);
}