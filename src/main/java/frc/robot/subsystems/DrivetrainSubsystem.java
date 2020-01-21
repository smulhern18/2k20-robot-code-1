package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.*;

import frc.robot.models.PairedTalonSRX;
import frc.robot.commands.drivetrain.DefaultDriveCommand;
import frc.robot.input.AttackThree;
import frc.robot.Constants;
import frc.robot.Constants.DrivetrainConstants;


/**
 * The motors and sensors that the robot uses to drive.
 */
public class DrivetrainSubsystem extends SubsystemBase { // drivetrain subsystem

  private PairedTalonSRX leftPair, rightPair;

  private DifferentialDriveOdometry odometry;

  private AHRS navx;

  private TrajectoryConfig forwardTrajectoryConfig, backwardTrajectoryConfig;

  private DifferentialDriveVoltageConstraint autoVoltageConstraint;

  /**
   * Construct Drivetrain subsystem
   * Configures sensors too
   *
   * @param leftStick  AttackThree left stick
   * @param rightStick AttackThree right stick
   */
  public DrivetrainSubsystem(AttackThree leftStick, AttackThree rightStick) {

    leftPair = new PairedTalonSRX(DrivetrainConstants.LEFT_LEADER_CHANNEL, DrivetrainConstants.LEFT_FOLLOWER_CHANNEL);
    rightPair = new PairedTalonSRX(DrivetrainConstants.RIGHT_LEADER_CHANNEL, DrivetrainConstants.RIGHT_FOLLOWER_CHANNEL);

    navx = new AHRS(Port.kUSB);

    rightPair.setInverted(true);

    leftPair.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, DrivetrainConstants.PID_X, DrivetrainConstants.TIMEOUT_MS);
    rightPair.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, DrivetrainConstants.PID_X, DrivetrainConstants.TIMEOUT_MS);

    leftPair.setSensorPhase(true);
    rightPair.setSensorPhase(true);

    leftPair.configPIDF(0, Constants.DrivetrainConstants.P_ENCODER_GAIN, 0, 0, 0);
    rightPair.configPIDF(0, Constants.DrivetrainConstants.P_ENCODER_GAIN, 0, 0, 0);

    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getYawDegrees()));

    autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(DrivetrainConstants.S_VOLTS, DrivetrainConstants.V_VOLT_SECONDS_PER_METER, DrivetrainConstants.A_VOLT_SECONDS_SQUARED_PER_METER),
        DrivetrainConstants.DRIVE_KINEMATICS,
        DrivetrainConstants.MAX_VOLTAGE);

    forwardTrajectoryConfig = createTrajectoryConfig(false);
    backwardTrajectoryConfig = createTrajectoryConfig(true);

    resetAll();

    setDefaultCommand(new DefaultDriveCommand(this, leftStick, rightStick));
  }

  /**
   * Creates a trajectory config
   *
   * @param reversed true if it drives backwards
   * @return a new trajectory config
   */
  private TrajectoryConfig createTrajectoryConfig(boolean reversed) {
    TrajectoryConfig config = new TrajectoryConfig(
        DrivetrainConstants.MAX_SPEED_METERS_PER_SECOND,
        DrivetrainConstants.MAX_ACCELERATION_METERS_PER_SECOND_SQUARED);
    config.setKinematics(DrivetrainConstants.DRIVE_KINEMATICS);
    config.addConstraint(autoVoltageConstraint);
    config.setReversed(reversed);
    return config;
  }


  /**
   * Drive with a specified mode
   *
   * @param mode  A {@link ControlMode}
   * @param left  speed of left motor pair [-1, 1]
   * @param right speed of right motor pair [-1, 1]
   */
  public void drive(ControlMode mode, double left, double right) {
    leftPair.set(mode, left);
    rightPair.set(mode, right);
  }

  /**
   * Drives the drive train with percent output as the ControlMode.
   *
   * @param left  speed of left motor pair [-1, 1]
   * @param right speed of right motor pair [-1, 1]
   */
  public void drive(double left, double right) {
    drive(ControlMode.PercentOutput, left, right);
  }

  /**
   * Drives based on a voltage
   *
   * @param left  left voltage
   * @param right right voltage
   */
  public void driveVolts(double left, double right) {
    drive(left / RobotController.getBatteryVoltage(), right / RobotController.getBatteryVoltage());
  }

  /**
   * Set speed controllers to Coast mode
   */
  public void setCoast() {
    leftPair.setNeutralMode(NeutralMode.Coast);
    rightPair.setNeutralMode(NeutralMode.Coast);
  }

  /**
   * Set speed controllers to Brake mode
   */
  public void setBrake() {
    leftPair.setNeutralMode(NeutralMode.Brake);
    rightPair.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    odometry.update(Rotation2d.fromDegrees(getYawDegrees()), getLeftDistance(), getRightDistance());
    SmartDashboard.putString("Odometry", odometry.getPoseMeters().toString());
    SmartDashboard.putString("Velocity m/s", getWheelSpeeds().toString());
  }

  /**
   * Gets the chassis's yaw (orientation of the robot)
   *
   * @return yaw in degrees
   */
  public double getYawDegrees() {
    return -Math.IEEEremainder(navx.getAngle(), 360);
  }

  /**
   * Sets encoders to 0
   */
  public void resetEncoders() {
    leftPair.setSelectedSensorPosition(0);
    rightPair.setSelectedSensorPosition(0);
  }

  /**
   * Sets yaw to 0
   */
  public void   resetNavX() {
    navx.reset();
  }

  /**
   * Converts meters/s to counts/100ms
   *
   * @param mps meters per second
   * @return counts/100ms
   */
  public double metersPerSecondToCountsPerDeciSec(double mps) {
    return mps * (1 / DrivetrainConstants.METERS_PER_COUNT) * (1.0 / 10.0);
  }

  /**
   * Converts counts/100ms to meters/s
   *
   * @param countsPerDecisec counts/100ms
   * @return meters per second
   */
  public double countsPerDeciSecToMetersPerSecond(double countsPerDecisec) {
    return countsPerDecisec * 10 * DrivetrainConstants.METERS_PER_COUNT;
  }

  /**
   * Gets left distance in meters
   *
   * @return the left distance in meters
   */
  public double getLeftDistance() {
    return DrivetrainConstants.METERS_PER_COUNT * leftPair.getSelectedSensorPosition();
  }

  /**
   * Gets right distance in meters
   *
   * @return the left distance in meters
   */
  public double getRightDistance() {
    return DrivetrainConstants.METERS_PER_COUNT * rightPair.getSelectedSensorPosition();
  }

  /**
   * Returns the position of the robot on the field.
   *
   * @return The pose of the robot (x and y are in meters).
   */
  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  /**
   * Resets odometry to specified pose
   *
   * @param pose pose to reset to
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getYawDegrees()));
  }

  /**
   * Resets NavX, Encoders, odometry, sets coast
   */
  public void resetAll() {
    resetNavX();
    resetEncoders();
    resetOdometry(new Pose2d());
    setCoast();
  }

  /**
   * (counts / 100 ms) * (meters / count) * (10 ms / 1 s) == (meters / second)
   *
   * @return Wheel speeds in meters / second
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(
        countsPerDeciSecToMetersPerSecond(leftPair.getSelectedSensorVelocity()),
        countsPerDeciSecToMetersPerSecond(rightPair.getSelectedSensorVelocity()));
  }

  /**
   * Used for trajectories where the robot drives forwards
   *
   * @return forward trajectory config
   */
  public TrajectoryConfig getForwardTrajectoryConfig() {
    return forwardTrajectoryConfig;
  }

  /**
   * Used for trajectories where the robot drives backwards
   *
   * @return backward trajectory config
   */
  public TrajectoryConfig getBackwardTrajectoryConfig() {
    return backwardTrajectoryConfig;
  }
}