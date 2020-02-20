package frc.robot.commands.auto.thief.half;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class HalfThiefTrenchTrajectories extends Trajectories {
  private static final Pose2d SHOOT_ONE_START = new Pose2d(-2.214, 0.08, new Rotation2d(Units.degreesToRadians(0.00)));
  public static final Trajectory FIRST_TWO = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(0, 0, new Rotation2d(Units.degreesToRadians(0.00))),
          SHOOT_ONE_START
      ),
      BACKWARD_CONFIG);
  private static final Pose2d UNDER_RENDEZ_START = new Pose2d(0.297, -3.284, new Rotation2d(Units.degreesToRadians(-23.63)));
  public static final Trajectory SHOOT_ONE = TrajectoryGenerator.generateTrajectory(
      List.of(
          SHOOT_ONE_START,
          UNDER_RENDEZ_START
      ),
      FORWARD_CONFIG);

  public static final Trajectory UNDER_RENDEZ = TrajectoryGenerator.generateTrajectory(
      List.of(
          UNDER_RENDEZ_START,
          new Pose2d(-1.892, -2.665, new Rotation2d(Units.degreesToRadians(113.43))),
          new Pose2d(-1.605, -4.174, new Rotation2d(Units.degreesToRadians(65))),
          new Pose2d(-2.354, -4.673, new Rotation2d(Units.degreesToRadians(20)))
      ),
      BACKWARD_CONFIG);

}