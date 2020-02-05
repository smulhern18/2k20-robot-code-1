package frc.robot.commands.auto.thief.half;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class HalfThiefTrenchTrajectories extends Trajectories {
  private static final Pose2d SHOOT_ONE_START = new Pose2d(6.491, -7.501, new Rotation2d(Math.toRadians(0.00)));
  public static final Trajectory FIRST_TWO = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(3.856, -7.501, new Rotation2d(Math.toRadians(0.00))),
          SHOOT_ONE_START
      ),
      BACKWARD_CONFIG);
  private static final Pose2d UNDER_RENDEZ_START = new Pose2d(3.980, -4.137, new Rotation2d(Math.toRadians(-23.63)));
  public static final Trajectory SHOOT_ONE = TrajectoryGenerator.generateTrajectory(
      List.of(
          SHOOT_ONE_START,
          UNDER_RENDEZ_START
      ),
      FORWARD_CONFIG);

  public static final Trajectory UNDER_RENDEZ = TrajectoryGenerator.generateTrajectory(
      List.of(
          UNDER_RENDEZ_START,
          new Pose2d(6.169, -4.756, new Rotation2d(Math.toRadians(113.43))),
          new Pose2d(5.882, -3.247, new Rotation2d(Units.degreesToRadians(65))),
          new Pose2d(6.664, -2.744, new Rotation2d(Math.toRadians(Units.degreesToRadians(20))))
      ),
      BACKWARD_CONFIG);

}