package frc.robot.commands.auto.normal.crazy;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class CrazyTrajectories extends Trajectories {
  private static final Pose2d FIRST_THREE_START = new Pose2d(-3.284, -0.178, new Rotation2d(Units.degreesToRadians(3.06)));
  public static final Trajectory START = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(0.000, 0.000, new Rotation2d(Units.degreesToRadians(0.00))),
          FIRST_THREE_START
      ),
      BACKWARD_CONFIG);
  private static final Pose2d LAST_TWO_START = new Pose2d(-5.818, -0.357, new Rotation2d(Units.degreesToRadians(0.00)));
  public static final Trajectory FIRST_THREE = TrajectoryGenerator.generateTrajectory(
      List.of(
          FIRST_THREE_START,
          LAST_TWO_START
      ),
      BACKWARD_CONFIG);
  private static final Pose2d GRAB_TWO_START = new Pose2d(-2.249, 1.143, new Rotation2d(Units.degreesToRadians(-71.13)));
  public static final Trajectory LAST_TWO = TrajectoryGenerator.generateTrajectory(
      List.of(
          LAST_TWO_START,
          GRAB_TWO_START
      ),
      FORWARD_CONFIG);

  public static final Trajectory GRAB_TWO = TrajectoryGenerator.generateTrajectory(
      List.of(
          GRAB_TWO_START,
          new Pose2d(-2.475, 1.821, new Rotation2d(Units.degreesToRadians(-67.76)))
      ),
      BACKWARD_CONFIG);


}
