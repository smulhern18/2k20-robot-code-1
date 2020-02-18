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
          new Pose2d(3.411, -3.581, new Rotation2d(Units.degreesToRadians(0.00))),
          new Pose2d(5.773, -3.816, new Rotation2d(Units.degreesToRadians(-67.73))),
          new Pose2d(6.058, -4.558, new Rotation2d(Units.degreesToRadians(-67.73)))
      ),
      BACKWARD_CONFIG);
}
