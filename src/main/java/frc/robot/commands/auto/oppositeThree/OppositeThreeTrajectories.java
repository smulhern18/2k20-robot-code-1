package frc.robot.commands.auto.oppositeThree;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class OppositeThreeTrajectories extends Trajectories {
    private static final Pose2d SHOOT_START = new Pose2d(9.706, -3.544, new Rotation2d(Math.toRadians(27.80)));
    public static final Trajectory OPPOSITE_THREE_GRAB = TrajectoryGenerator.generateTrajectory(
        List.of(
            new Pose2d(3.498, -4.978, new Rotation2d(Math.toRadians(0.00))),
            new Pose2d(6.874, -4.731, new Rotation2d(Math.toRadians(32.13))),
            new Pose2d(8.531, -3.296, new Rotation2d(Math.toRadians(21.23))),
            SHOOT_START
        ),
        FORWARD_CONFIG);

    public static final Trajectory OPPOSITE_THREE_SHOOT = TrajectoryGenerator.generateTrajectory(
        List.of(
            SHOOT_START,
            new Pose2d(6.000, -2.416, new Rotation2d(Math.toRadians(0.00)))
        ),
        BACKWARD_CONFIG);  
}