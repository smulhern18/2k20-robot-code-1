package frc.robot.models;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Constants.DrivetrainConstants;

/**
 * Group of Talons
 */
public class PairedTalonFX extends WPI_TalonFX {

  private final WPI_TalonFX follower;

  /**
   * Creates two Talons, one following the other
   *
   * @param leaderDeviceNumber   CAN id of lead device
   * @param followerDeviceNumber CAN id of follower device
   */
  public PairedTalonFX(int leaderDeviceNumber, int followerDeviceNumber) {
    super(leaderDeviceNumber);
    configFactoryDefault();

    follower = new WPI_TalonFX(followerDeviceNumber);
    follower.configFactoryDefault();
    follower.follow(this);
    follower.setInverted(InvertType.FollowMaster);

    configSelectedFeedbackSensor(
        FeedbackDevice.IntegratedSensor,
        DrivetrainConstants.PID_LOOPTYPE,
        DrivetrainConstants.TIMEOUT_MS);
    configPIDF(
        DrivetrainConstants.P,
        DrivetrainConstants.I,
        DrivetrainConstants.D,
        DrivetrainConstants.F
    );
  }

  /**
   * Sets mode of Talons
   *
   * @param mode mode to set the talons (break or coast)
   */
  @Override
  public void setNeutralMode(NeutralMode mode) {
    super.setNeutralMode(mode);
    follower.setNeutralMode(mode);
  }

  /**
   * Configures PIDF, not used by Trajectories
   *
   * @param P proportional value
   * @param I integral value
   * @param D derivative value
   * @param F feed forward value
   */
  public void configPIDF(double P, double I, double D, double F) {
    config_kP(DrivetrainConstants.SLOT_ID, P);
    config_kI(DrivetrainConstants.SLOT_ID, I);
    config_kD(DrivetrainConstants.SLOT_ID, D);
    config_kF(DrivetrainConstants.SLOT_ID, F);
  }

  /**
   * Gets distance in meters
   *
   * @return the distance in meters
   */
  public double getDistanceMeters() {
    return getSelectedSensorPosition() * DrivetrainConstants.METERS_PER_COUNT;
  }

  /**
   * Gets velocity in meters per second
   *
   * @return velocity
   */
  public double getVelocityMetersPerSecond() {
    return getSelectedSensorVelocity() * DrivetrainConstants.METERS_PER_COUNT * 10;
  }


}