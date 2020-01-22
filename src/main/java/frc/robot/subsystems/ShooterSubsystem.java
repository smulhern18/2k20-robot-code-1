package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.models.PairedTalonSRX;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.shooter.ShootCommand;;

/**
 * The shooter
 */
public class ShooterSubsystem extends SubsystemBase {


  private PairedTalonSRX pair;
  private String setpointEntry = "Set Shooter Velocity";

  /**
   * Creates a new ShooterSubsystem.
   */
  public ShooterSubsystem() {

    pair = new PairedTalonSRX(ShooterConstants.LEADER_CHANNEL, ShooterConstants.FOLLOWER_CHANNEL);
    pair.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, ShooterConstants.PID_LOOPTYPE, ShooterConstants.TIMEOUT_MS);

    pair.configPIDF(0, ShooterConstants.P, ShooterConstants.I, ShooterConstants.D, ShooterConstants.F);
    pair.setSensorPhase(true);

    SmartDashboard.putNumber(setpointEntry, 0);

    resetEncoder();
    setCoast();

    setDefaultCommand(new ShootCommand(this));
  }

  /**
   * Shoot with a specified mode
   *
   * @param mode  A {@link ControlMode}
   * @param value speed of the one motor pair [-1, 1]
   */
  private void shoot(ControlMode mode, double value) {
    pair.set(mode, value);
  }

  /**
   * Set flywheel to specified velocity
   *
   * @param velocity RPM velocity
   */
  public void shoot(double velocity) {
    velocity = velocity * ShooterConstants.COUNTS_PER_REVOLUTION * 10.0 * (1.0 / 60.0);
    shoot(ControlMode.Velocity, velocity);
  }

  /**
   * Set speed controllers to Coast mode
   */
  private void setCoast() {
    pair.setNeutralMode(NeutralMode.Coast);
  }

  /**
   * Get velocity of shooting wheel
   */
  public double getVelocity() {
    return pair.getSelectedSensorVelocity() * 60.0 * (1 / ShooterConstants.COUNTS_PER_REVOLUTION) * (1.0 / 10.0);
  }

  /**
   * Get input setpoint velocity from Shuffleboard
   */
  public double getSetpointVelocity() {
    return SmartDashboard.getNumber(setpointEntry, 0);
  }

  /**
   * Puts current velocity of shooter wheel
   */
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Shooter Velocity", getVelocity());
  }

  /**
   * Sets encoder to 0
   */
  public void resetEncoder() {
    pair.setSelectedSensorPosition(0);
  }

}