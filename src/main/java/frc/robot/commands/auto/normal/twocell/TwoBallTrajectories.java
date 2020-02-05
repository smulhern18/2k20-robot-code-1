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
          new Pose2d(3.584, -2.418, new Rotation2d(Math.toRadians(0.00))),
          new Pose2d(6.164, -2.896, new Rotation2d(Units.degreesToRadians(-68)))
      ),
      BACKWARD_CONFIG);
}
