package frc.robot.commands.auto.rendezvous;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public class RendezvousTrajectories extends Trajectories {
  private static final Pose2d RENDEZVOUS_START = new Pose2d(3.50, -4.96, new Rotation2d(0.0));
  private static final Pose2d BEAM_RIDE_GRAB_THREE_END = new Pose2d(5.60, -3.50, new Rotation2d(1.95));
  private static final Pose2d ZIGZAG_END_ONE = new Pose2d(6.39, -4.13, new Rotation2d(0.37));
  private static final Pose2d ZIGZAG_END_TWO = new Pose2d(6.18, -3.72, new Rotation2d(0.43));

  public final static Trajectory BEAM_RIDE_GRAB_THREE = TrajectoryGenerator.generateTrajectory(
      List.of(
          RENDEZVOUS_START,
          new Pose2d(5.00, -5.79, new Rotation2d(-0.03)),
          new Pose2d(6.31, -5.09, new Rotation2d(1.95)),
          BEAM_RIDE_GRAB_THREE_END
      ),
      BACKWARD_CONFIG
  );

  public final static Trajectory BEAM_RIDE_GRAB_TWO = TrajectoryGenerator.generateTrajectory(
      List.of(
          BEAM_RIDE_GRAB_THREE_END,
          new Pose2d(6.11, -3.47, new Rotation2d(0.81)),
          new Pose2d(6.31, -3.14, new Rotation2d(1.99)),
          new Pose2d(5.73, -1.56, new Rotation2d(Math.toRadians(-71.55)))),
      BACKWARD_CONFIG
  );

  public final static Trajectory ZIGZAG_GRAB_TWO_FRONT = TrajectoryGenerator.generateTrajectory(
      List.of(
          RENDEZVOUS_START,
          ZIGZAG_END_ONE
      ),
      BACKWARD_CONFIG
  );

  public final static Trajectory ZIGZAG_GRAB_ONE_FRONT = TrajectoryGenerator.generateTrajectory(
      List.of(
          ZIGZAG_END_ONE,
          new Pose2d(4.75, -4.33, new Rotation2d(0.35)),
          ZIGZAG_END_TWO),
      BACKWARD_CONFIG
  );

  public final static Trajectory ZIGZAG_GRAB_TWO_SIDE = TrajectoryGenerator.generateTrajectory(
      List.of(
          ZIGZAG_END_TWO,
          new Pose2d(4.82, -3.87, new Rotation2d(-2.63)),
          new Pose2d(4.54, -2.87, new Rotation2d(-1.31)),
          new Pose2d(5.25, -2.13, new Rotation2d(-0.24)),
          new Pose2d(6.41, -3.34, new Rotation2d(-1.19))),
      BACKWARD_CONFIG
  );
}
