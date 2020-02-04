package frc.robot.commands.auto.thief;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public class ThiefTrajectories extends Trajectories {
  private static final Pose2d STEAL_END = new Pose2d(10, -7.5, new Rotation2d(0));
  public static final Trajectory STEAL_FIVE = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(4.277, -7.5, new Rotation2d(0)),
          STEAL_END
      ),
      BACKWARD_CONFIG
  );
  public static final Trajectory RETREAT = TrajectoryGenerator.generateTrajectory(
      List.of(
          STEAL_END,
          new Pose2d(7.3, -5.75, new Rotation2d(Units.degreesToRadians(-35.0)))
      ),
      FORWARD_CONFIG
  );
}
