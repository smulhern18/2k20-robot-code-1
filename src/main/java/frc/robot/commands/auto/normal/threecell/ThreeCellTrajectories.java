package frc.robot.commands.auto.normal.threecell;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class ThreeCellTrajectories extends Trajectories {
  public static final Trajectory THREE_CELL = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(0, 0, new Rotation2d(Units.degreesToRadians(0.00))),
          new Pose2d(-2.362, 0.234, new Rotation2d(Units.degreesToRadians(-67.73))),
          new Pose2d(-2.647, -0.977, new Rotation2d(Units.degreesToRadians(-67.73)))
      ),
      BACKWARD_CONFIG);
}
