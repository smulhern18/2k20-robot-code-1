package frc.robot.commands.auto.crazy;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class CrazyTrajectories extends Trajectories {
    private static final Pose2d FIRST_THREE_START = new Pose2d(7.005, -0.894, new Rotation2d(Math.toRadians(3.06)));
    private static final Pose2d LAST_TWO_START = new Pose2d(9.539, -0.715, new Rotation2d(Math.toRadians(0.00)));
    private static final Pose2d GRAB_TWO_START = new Pose2d(5.97, -2.215, new Rotation2d(Math.toRadians(-71.13)));
    public static final Trajectory START = TrajectoryGenerator.generateTrajectory(
        List.of(
            new Pose2d(3.721, -1.072, new Rotation2d(Math.toRadians(3.08))),
            FIRST_THREE_START
        ),
        BACKWARD_CONFIG);

    public static final Trajectory FIRST_THREE = TrajectoryGenerator.generateTrajectory(
        List.of(
            FIRST_THREE_START,
            new Pose2d(8.611, -0.787, new Rotation2d(Math.toRadians(4.20))),
            LAST_TWO_START
        ),
        BACKWARD_CONFIG);
    
    public static final Trajectory LAST_TWO = TrajectoryGenerator.generateTrajectory(
        List.of(
            LAST_TWO_START,
            GRAB_TWO_START
        ),
        FORWARD_CONFIG);
    
    public static final Trajectory GRAB_TWO = TrajectoryGenerator.generateTrajectory(
        List.of(
            GRAB_TWO_START,
            new Pose2d(6.196, -2.893, new Rotation2d(Math.toRadians(-67.76)))
        ),
        BACKWARD_CONFIG);
        
        
}
