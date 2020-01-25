package frc.robot.commands.auto.trench;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class TrenchTrajectories extends Trajectories {
    public static final Trajectory GRAB = TrajectoryGenerator.generateTrajectory(
            List.of(new Pose2d(3.5,-0.75, new Rotation2d(0)),
                    new Pose2d(9.75,-0.75, new Rotation2d(0))),
            BACKWARD_CONFIG);

    public static final Trajectory RETURN = TrajectoryGenerator.generateTrajectory(
            List.of(new Pose2d(9.75,-0.75, new Rotation2d(0)),
                    new Pose2d(3.5,-0.75, new Rotation2d(0))),
            FORWARD_CONFIG);
}
