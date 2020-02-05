package frc.robot.commands.auto.normal.rendezvous;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public class RendezvousTrajectories extends Trajectories {
  private static final Pose2d RENDEZVOUS_START = new Pose2d(3.50, -4.96, new Rotation2d(0.0));
  private static final Pose2d BEAM_RIDE_GRAB_THREE_END = new Pose2d(5.81, -3.818, new Rotation2d(Units.degreesToRadians(111)));
  public final static Trajectory BEAM_RIDE_GRAB_THREE = TrajectoryGenerator.generateTrajectory(
      List.of(
          RENDEZVOUS_START,
          new Pose2d(5.00, -5.79, new Rotation2d(0)),
          new Pose2d(6.31, -5.09, new Rotation2d(111)),
          BEAM_RIDE_GRAB_THREE_END
      ),
      BACKWARD_CONFIG
  );
  public final static Trajectory BEAM_RIDE_GRAB_TWO = TrajectoryGenerator.generateTrajectory(
      List.of(
          BEAM_RIDE_GRAB_THREE_END,
          new Pose2d(6.31, -3.14, new Rotation2d(Units.degreesToRadians(114))),
          new Pose2d(5.73, -1.56, new Rotation2d(Math.toRadians(-71.55)))),
      BACKWARD_CONFIG
  );
  private static final Pose2d ZIGZAG_END_ONE = new Pose2d(6.39, -4.13, new Rotation2d(Units.degreesToRadians(21.2)));
  public final static Trajectory ZIGZAG_GRAB_TWO_FRONT = TrajectoryGenerator.generateTrajectory(
      List.of(
          RENDEZVOUS_START,
          ZIGZAG_END_ONE
      ),
      BACKWARD_CONFIG
  );
  private static final Pose2d ZIGZAG_END_TWO = new Pose2d(6.18, -3.72, new Rotation2d(0.43));
  public final static Trajectory ZIGZAG_GRAB_ONE_FRONT = TrajectoryGenerator.generateTrajectory(
      List.of(
          ZIGZAG_END_ONE,
          new Pose2d(4.75, -4.33, new Rotation2d(Units.degreesToRadians(20))),
          ZIGZAG_END_TWO),
      BACKWARD_CONFIG
  );

  public final static Trajectory ZIGZAG_GRAB_TWO_SIDE = TrajectoryGenerator.generateTrajectory(
      List.of(
          ZIGZAG_END_TWO,
          new Pose2d(4.82, -3.87, new Rotation2d(Units.degreesToRadians(-150))),
          new Pose2d(4.54, -2.87, new Rotation2d(Units.degreesToRadians(-75))),
          new Pose2d(5.25, -2.13, new Rotation2d(Units.degreesToRadians(-13.7))),
          new Pose2d(6.41, -3.34, new Rotation2d(Units.degreesToRadians(-68.2)))),
      BACKWARD_CONFIG
  );
}
