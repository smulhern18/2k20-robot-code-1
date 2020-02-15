package frc.robot.models.speedcontrollers;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Group of Talons
 */
public class PairedTalonSRX extends WPI_TalonSRX {

  private final WPI_TalonSRX follower;

  /**
   * Creates two Talons, one following the other
   *
   * @param leaderDeviceNumber   CAN id of lead device
   * @param followerDeviceNumber CAN id of follower device
   */
  public PairedTalonSRX(int leaderDeviceNumber, int followerDeviceNumber) {
    super(leaderDeviceNumber);
    configFactoryDefault();

    follower = new WPI_TalonSRX(followerDeviceNumber);
    follower.configFactoryDefault();
    follower.follow(this);
    follower.setInverted(InvertType.FollowMaster);
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
  public void configPIDF(int SLOT_ID, double P, double I, double D, double F) {
    selectProfileSlot(0, 0);
    config_kP(SLOT_ID, P);
    config_kI(SLOT_ID, I);
    config_kD(SLOT_ID, D);
    config_kF(SLOT_ID, F);
  }
}
