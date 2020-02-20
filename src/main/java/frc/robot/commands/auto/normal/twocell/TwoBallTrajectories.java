package frc.robot.commands.auto.normal.twocell;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class TwoBallTrajectories extends Trajectories {
  public static final Trajectory TWO_GRAB = TrajectoryGenerator.generateTrajectory(
      List.of(
          new Pose2d(0, 0, new Rotation2d(Units.degreesToRadians(0.00))),
          new Pose2d(-2.259, 0.476, new Rotation2d(Units.degreesToRadians(-68)))
      ),
      BACKWARD_CONFIG);
}
