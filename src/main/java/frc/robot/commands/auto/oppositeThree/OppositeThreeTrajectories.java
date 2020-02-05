package frc.robot.commands.auto.oppositeThree;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class OppositeThreeTrajectories extends Trajectories {
    private static final Pose2d SHOOT_START = new Pose2d(10.188, -4.545, new Rotation2d(Math.toRadians(-68.20)));
    public static final Trajectory OPPOSITE_THREE_GRAB = TrajectoryGenerator.generateTrajectory(
        List.of(
            new Pose2d(4.277, -7.421, new Rotation2d(Math.toRadians(0.00))),
            new Pose2d(6.824, -5.621, new Rotation2d(Math.toRadians(46.11))),
            new Pose2d(9.248, -3.581, new Rotation2d(Math.toRadians(0.00))),
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