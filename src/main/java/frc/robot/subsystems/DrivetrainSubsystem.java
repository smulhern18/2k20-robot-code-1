package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.models.PairedTalonSRX;
import frc.robot.commands.drivetrain.DefaultDriveCommand;
import frc.robot.input.AttackThree;
import frc.robot.Constants.DrivetrainConstants;


/**
 * The motors and sensors that the robot uses to drive.
 */
public class DrivetrainSubsystem extends SubsystemBase { // drivetrain subsystem

  /**
   * Creates a new DrivetrainSubsystem.
   */


  private PairedTalonSRX leftPair;
  private PairedTalonSRX rightPair;

  private DifferentialDriveOdometry odometry;

  private AHRS navx;

  private TrajectoryConfig trajectoryConfig;
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

    navx = new AHRS(SPI.Port.kMXP);

    rightPair.setInverted(true);

    leftPair.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, DrivetrainConstants.PID_X, DrivetrainConstants.TIMEOUT_MS);
    rightPair.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, DrivetrainConstants.PID_X, DrivetrainConstants.TIMEOUT_MS);

    leftPair.setSensorPhase(true);
    rightPair.setSensorPhase(true);

    resetEncoders();

    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getYawDegrees()));

    autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(DrivetrainConstants.S_VOLTS, DrivetrainConstants.V_VOLT_SECONDS_PER_METER, DrivetrainConstants.A_VOLT_SECONDS_SQUARED_PER_METER),
        DrivetrainConstants.DRIVE_KINEMATICS,
        DrivetrainConstants.MAX_VOLTAGE);
    trajectoryConfig = new TrajectoryConfig(
        DrivetrainConstants.MAX_SPEED_METERS_PER_SECOND,
        DrivetrainConstants.MAX_ACCELERATION_METERS_PER_SECOND_SQUARED);
    trajectoryConfig.setKinematics(DrivetrainConstants.DRIVE_KINEMATICS);
    trajectoryConfig.addConstraint(autoVoltageConstraint);


    this.setDefaultCommand(new DefaultDriveCommand(this, leftStick, rightStick));
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
  }

  @Override
  public String getName() { //returns the name of the subsytem
    return "Drivetrain";
  }

  @Override
  public String getSubsystem() { //returns the name of the subsytem for Sendable
    return "Drivetrain";
  }

  /**
   * Gets the chassis's yaw (orientation of the robot)
   *
   * @return yaw in degrees
   */
  public double getYawDegrees() {
    return navx.getYaw();
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
   * Gets left distance in meters
   *
   * @return the left distance in meters
   */
  public double getLeftDistance() {
    return DrivetrainConstants.METERS_PER_COUNT * leftPair.getSelectedSensorPosition();
  }

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
   * @param pose pose to reset to
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getYawDegrees()));
  }

  public double getTurnRate() {
    return navx.getRate();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(leftPair.getSelectedSensorVelocity(), rightPair.getSelectedSensorVelocity());
  }

  public TrajectoryConfig getTrajectoryConfig() {
    return trajectoryConfig;
  }

}