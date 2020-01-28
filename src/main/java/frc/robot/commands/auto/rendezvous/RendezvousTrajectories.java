package frc.robot.commands.auto.rendezvous;

public class RendezvousTrajectories extends Trajectories {
    public final Rotation2D FORWARD = new Rotation2D(0.0);
    public final Pose2D Point001 = new Pose2D(3.50, -4.96, FORWARD);
    public final Pose2D Point002 = new Pose2D(5.00, -5.79, new Rotation2D(-0.03);
    public final Pose2D Point003 = new Pose2D(6.31, -5.09, new Rotation2D(1.95);
    public final Pose2D Point004 = new Pose2D(5.60, -3.50, new Rotation2D(1.95));
    public final Pose2D Point005 = new Pose2D(5.70, -3.72, new Rotation2D(1.98));
    public final Pose2D Point006 = new Pose2D(6.11, -3.47, new Rotation2D(0.81));
    public final Pose2D Point007 = new Pose2D(6.31, -3.14, new Rotation2D(1.99));
    public final Pose2D Point008 = new Pose2D(5.38, -2.18, new Rotation2D(3.02));
    public final Pose2D Point009 = new Pose2D(4.49, -4.20, new Rotation2D(-2.33));
    public final Pose2D Point010 = new Pose2D(6.39, -4.13, new Rotation2D(0.37));
    public final Pose2D Point011 = new Pose2D(6.18, -3.72, new Rotation2D(0.43));
    public final Pose2D Point012 = new Pose2D(4.75, -4.33, new Rotation2D(0.35));
    public final Pose2D Point014 = new Pose2D(6.21, -3.75, new Rotation2D(0.43));
    public final Pose2D Point015 = new Pose2D(4.82, -3.87, new Rotation2D(2.63));
    public final Pose2D Point016 = new Pose2D(4.54, -2.87, new Rotation2D(1.31));
    public final Pose2D Point017 = new Pose2D(5.25, -2.13, new Rotation2D(0.24));
    public final Pose2D Point018 = new Pose2D(6.41, -3.34, new Rotation2D(-1.19));
    public final Pose2D Point019 = new Pose2D(3.50, -3.93, FORWARD);

    public final static Trajectory BRGrab1 = TrajectoryGenerator.generateTrajectory(
            List.of(Point001, Point002, Point003, Point004)
    );

    public final static Trajectory BRGrab2 = TrajectoryGenerator.generateTrajectory(
            List.of(Point005, Point006, Point007, Point008, Point009, Point001)
    );

    // Zigzag grab

    public final static Trajectory ZZGrab1 = TrajectoryGenerator.generateTrajectory(
            List.of(Point001, Point010)
    );

    public final static Trajectory ZZGrab2 = TrajectoryGenerator.generateTrajectory(
            List.of(Point011, Point012, Point010)
    );

    public final static Trajectory ZZGrab3 = TrajectoryGenerator.generateTrajectory(
            List.of(Point014, Point015, Point016, Point017, Point018)
    );

    public final static Trajectory ZZReturn = TrajectoryGenerator.generateTrajectory(
            List.of(Point019, Point016, Point017, Point018)
            //List.of(Point018, Point017, Point016, Point019)
    );
}
