package frc.robot.commands.auto.thief.oppositefive;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class OppositeFiveTrajectories extends Trajectories {
  private static Pose2d STEAL_END = new Pose2d(5.600, -2.418, new Rotation2d(Units.degreesToRadians(180.00)));
  public static final Trajectory OPPOSITE = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(4.277, -7.421, new Rotation2d(Units.degreesToRadians(0.00))),
          new Pose2d(6.256, -6.351, new Rotation2d(Units.degreesToRadians(16.14))),
          new Pose2d(10.052, -4.805, new Rotation2d(Units.degreesToRadians(72.73))),
          new Pose2d(9.706, -3.370, new Rotation2d(Units.degreesToRadians(112.82))),
          STEAL_END
      ),
      BACKWARD_CONFIG);
  public static final Trajectory SPIN = TrajectoryGenerator.generateTrajectory(
      List.of(
          STEAL_END,
          new Pose2d(4.847, -3.033, new Rotation2d(0))
      ),
      BACKWARD_CONFIG
  );
}
