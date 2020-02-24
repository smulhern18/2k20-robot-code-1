package frc.robot.commands.auto.thief.trench;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public class TrenchThiefTrajectories extends Trajectories {
  private static final Pose2d STEAL_END = new Pose2d(-5.671, -0.071, new Rotation2d(0));
  public static final Trajectory STEAL_FIVE = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(0, 0, new Rotation2d(0)),
          STEAL_END
      ),
      BACKWARD_CONFIG
  );
  public static final Trajectory RETREAT = TrajectoryGenerator.generateTrajectory(
      List.of(
          STEAL_END,
          new Pose2d(-3.032, -1.641, new Rotation2d(Units.degreesToRadians(-35.0)))
      ),
      FORWARD_CONFIG
  );
}
