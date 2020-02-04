package frc.robot.commands.auto.threeBall;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class ThreeBallTrajectories extends Trajectories {
    public static final Trajectory THREE_BALL = TrajectoryGenerator.generateTrajectory(
        List.of(
            new Pose2d(3.411, -3.581, new Rotation2d(Math.toRadians(0.00))),
            new Pose2d(5.773, -3.816, new Rotation2d(Math.toRadians(-67.73))),
            new Pose2d(6.058, -4.558, new Rotation2d(Math.toRadians(-67.73)))
        ),
        FORWARD_CONFIG);   
}
