package frc.robot.commands.auto.opposite;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.commands.auto.Trajectories;

import java.util.List;

public final class OppositeTrajectories extends Trajectories {
    public static final Trajectory OPPOSITE = TrajectoryGenerator.generateTrajectory(
        List.of(
            new Pose2d(3.658, -5.600, new Rotation2d(Math.toRadians(0.00))),
            new Pose2d(9.224, -5.485, new Rotation2d(Math.toRadians(27.94))),
            new Pose2d(10.052, -4.805, new Rotation2d(Math.toRadians(72.73))),
            new Pose2d(9.706, -3.370, new Rotation2d(Math.toRadians(112.82))),
            new Pose2d(6.005, -2.418, new Rotation2d(Math.toRadians(180.00)))
        ),
        FORWARD_CONFIG);   
}