package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.models.PairedTalonSRX;


/**
 * The motors and sensors that the robot uses to drive.
 */
public class DrivetrainSubsystem extends SubsystemBase {

  private PairedTalonSRX leftPair, rightPair;

  private DifferentialDriveOdometry odometry;

  private AHRS navx;

  private double leftAcceleration = 0, rightAcceleration = 0;

  /**
   * Construct Drivetrain subsystem
   * Configures sensors too
   */
  public DrivetrainSubsystem() {

    leftPair = new PairedTalonSRX(
        DrivetrainConstants.LEFT_LEADER_CHANNEL,
        DrivetrainConstants.LEFT_FOLLOWER_CHANNEL);
    rightPair = new PairedTalonSRX(
        DrivetrainConstants.RIGHT_LEADER_CHANNEL,
        DrivetrainConstants.RIGHT_FOLLOWER_CHANNEL);

    navx = new AHRS(Port.kUSB);

    rightPair.setInverted(true);

    leftPair.configSelectedFeedbackSensor(
        FeedbackDevice.QuadEncoder,
        DrivetrainConstants.PID_LOOPTYPE,
        DrivetrainConstants.TIMEOUT_MS);
    rightPair.configSelectedFeedbackSensor(
        FeedbackDevice.QuadEncoder,
        DrivetrainConstants.PID_LOOPTYPE,
        DrivetrainConstants.TIMEOUT_MS);

    leftPair.setSensorPhase(true);
    rightPair.setSensorPhase(true);

    leftPair.configPIDF(
        DrivetrainConstants.P,
        DrivetrainConstants.I,
        DrivetrainConstants.D,
        DrivetrainConstants.F
    );
    rightPair.configPIDF(
        DrivetrainConstants.P,
        DrivetrainConstants.I,
        DrivetrainConstants.D,
        DrivetrainConstants.F
    );

    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getYawDegrees()));

    resetAll();
  }

  /**
   * Drive with a specified mode
   *
   * @param mode  A {@link ControlMode}
   * @param left  speed of left motor pair [-1, 1]
   * @param right speed of right motor pair [-1, 1]
   */
  private void drive(ControlMode mode, double left, double right) {
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
   * Controls the left and right side of the drive using Talon SRX closed-loop
   * velocity.
   *
   * @param leftVelocity  left velocity
   * @param rightVelocity right velocity
   */
  public void tankDriveVelocity(double leftVelocity, double rightVelocity) {
    //TODO: investigate acceleration units
    leftAcceleration = (leftVelocity - getWheelSpeeds().leftMetersPerSecond) / Constants.LOOP_TIME_MS;
    rightAcceleration = (rightVelocity - getWheelSpeeds().rightMetersPerSecond) / Constants.LOOP_TIME_MS;

    double leftFeedForwardVolts = DrivetrainConstants.DRIVE_FEED_FORWARD.calculate(leftVelocity, leftAcceleration);
    double rightFeedForwardVolts = DrivetrainConstants.DRIVE_FEED_FORWARD.calculate(rightVelocity, rightAcceleration);

    leftPair.set(
        ControlMode.Velocity,
        metersPerSecondToCountsPerDeciSec(leftVelocity),
        DemandType.ArbitraryFeedForward,
        leftFeedForwardVolts / Constants.MAX_BATTERY_VOLTAGE);
    rightPair.set(
        ControlMode.Velocity,
        metersPerSecondToCountsPerDeciSec(rightVelocity),
        DemandType.ArbitraryFeedForward,
        rightFeedForwardVolts / Constants.MAX_BATTERY_VOLTAGE);
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
    SmartDashboard.putString("Acceleration (unknown units)", "Left: " + leftAcceleration + ", Right: " + rightAcceleration);
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
  public void resetNavX() {
    navx.reset();
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
    resetOdometry(new Pose2d(0, 0, Rotation2d.fromDegrees(getYawDegrees())));
    setCoast();
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
   * Gets the chassis's yaw (orientation of the robot)
   *
   * @return yaw in degrees
   */
  public double getYawDegrees() {
    return -Math.IEEEremainder(navx.getAngle(), 360);
  }

  /**
   * Returns the position of the robot on the field.
   *
   * @return The pose of the robot (x and y are in meters).
   */
  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }
}