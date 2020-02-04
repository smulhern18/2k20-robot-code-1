package frc.robot.commands.auto.twoBall;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class TwoBallTrajectories extends Trajectories {
    private static final Pose2d TWO_SHOOT_START = new Pose2d(6.145, -3.061, new Rotation2d(Math.toRadians(0.00)));
    public static final Trajectory TWO_GRAB = TrajectoryGenerator.generateTrajectory(
        List.of(
            new Pose2d(3.584, -2.418, new Rotation2d(Math.toRadians(0.00))),
            TWO_SHOOT_START
        ),
        FORWARD_CONFIG);

    public static final Trajectory TWO_SHOOT = TrajectoryGenerator.generateTrajectory(
        List.of(
            TWO_SHOOT_START,
            new Pose2d(6.145, -2.418, new Rotation2d(Math.toRadians(0.00)))
        ),
        BACKWARD_CONFIG);   
}
