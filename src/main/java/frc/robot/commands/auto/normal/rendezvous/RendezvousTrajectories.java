package frc.robot.commands.auto.normal.rendezvous;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public class RendezvousTrajectories extends Trajectories {
  private final static Pose2d RENDEZVOUS_START = new Pose2d(0.00, 0.0, new Rotation2d(0.0));
  private final static Pose2d BEAM_RIDE_GRAB_THREE_END = new Pose2d(-2.311, -1.141, new Rotation2d(Units.degreesToRadians(111)));
  public final static Trajectory BEAM_RIDE_GRAB_THREE = TrajectoryGenerator.generateTrajectory(
      List.of(
          RENDEZVOUS_START,
          new Pose2d(-1.500, 0.830, new Rotation2d(0)),
          new Pose2d(-2.810, 0.130, new Rotation2d(111)),
          BEAM_RIDE_GRAB_THREE_END
      ),
      BACKWARD_CONFIG
  );
  public final static Trajectory BEAM_RIDE_GRAB_TWO = TrajectoryGenerator.generateTrajectory(
      List.of(
          BEAM_RIDE_GRAB_THREE_END,
          new Pose2d(-2.810, -1.820, new Rotation2d(Units.degreesToRadians(114))),
          new Pose2d(-2.230, -3.400, new Rotation2d(Units.degreesToRadians(-71.55)))),
      BACKWARD_CONFIG
  );


  private final static Pose2d ZIGZAG_END_ONE = new Pose2d(-2.66, -0.757, new Rotation2d(Units.degreesToRadians(21.2)));
  public final static Trajectory ZIGZAG_GRAB_TWO_FRONT = TrajectoryGenerator.generateTrajectory(
      List.of(
          RENDEZVOUS_START,
          ZIGZAG_END_ONE
      ),
      BACKWARD_CONFIG
  );
  public final static Trajectory ZIGZAG_BACK_UP = TrajectoryGenerator.generateTrajectory(
      List.of(
          ZIGZAG_END_ONE,
          new Pose2d(-1.246, -0.63, new Rotation2d(Units.degreesToRadians(20)))),
      FORWARD_CONFIG
  );
  private final static Pose2d ZIGZAG_BACK_UP_END = new Pose2d(-1.246, -0.63, new Rotation2d(Units.degreesToRadians(20)));
  private final static Pose2d ZIGZAG_END_TWO = new Pose2d(-2.684, -1.236, new Rotation2d(0.43));
  public final static Trajectory ZIGZAG_GRAB_ONE_FRONT = TrajectoryGenerator.generateTrajectory(
      List.of(
          ZIGZAG_BACK_UP_END,
          ZIGZAG_END_TWO),
      BACKWARD_CONFIG
  );
  private final static Pose2d ZIGZAG_END_THREE = new Pose2d(-2.299, -2.924, new Rotation2d(Units.degreesToRadians(-68.2)));
  public final static Trajectory ZIGZAG_BACK_UP_TWO = TrajectoryGenerator.generateTrajectory(
      List.of(
          ZIGZAG_END_TWO,
          new Pose2d(-1.185, -1.745, new Rotation2d(Units.degreesToRadians(-150))),
          new Pose2d(-1.613, -2.673, new Rotation2d(Units.degreesToRadians(-75))),
          new Pose2d(-2.291, -2.887, new Rotation2d(Units.degreesToRadians(-13.7))),
          ZIGZAG_END_THREE
      ),
      FORWARD_CONFIG
  );
  // start from here when changing coordinates
  public final static Trajectory ZIGZAG_GRAB_TWO_SIDE = TrajectoryGenerator.generateTrajectory(
      List.of(
          ZIGZAG_END_THREE,
          new Pose2d(-2.691, -1.926, new Rotation2d(Units.degreesToRadians(-68.2)))
      ),
      BACKWARD_CONFIG

  );
}
