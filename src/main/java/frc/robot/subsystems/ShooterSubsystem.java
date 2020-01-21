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
 * The motors and sensors that the robot uses to shoot.
 */
public class ShooterSubsystem extends SubsystemBase { // shooter subsystem
  /**
   * Creates a new ShooterSubsystem.
   */

  private PairedTalonSRX pair;

  public ShooterSubsystem() {

    pair = new PairedTalonSRX(ShooterConstants.LEADER_CHANNEL, ShooterConstants.FOLLOWER_CHANNEL);
    pair.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, ShooterConstants.PID_X, ShooterConstants.TIMEOUT_MS);

    pair.configPIDF(0, ShooterConstants.P, ShooterConstants.I, ShooterConstants.D, ShooterConstants.F);
    pair.setSensorPhase(true);

    SmartDashboard.putNumber("set shooter velocity", 0);

    resetEncoder();

    setDefaultCommand(new ShootCommand(this));
  }

  /**
   * Shoot with a specified mode
   *
   * @param mode  A {@link ControlMode}
   * @param value  speed of the one motor pair [-1, 1]
   */
  private void shoot(ControlMode mode, double value) {
    pair.set(mode, value);
  }

  /**
   * Shoot with a specified mode
   *
   * @param velocity  
   */
  public void shoot(double velocity) {

    velocity = velocity * ShooterConstants.COUNTS_PER_REVOLUTION * 10.0 / 60.0;
    shoot(ControlMode.Velocity, velocity);
  }

  /**
   * Set speed controllers to Coast mode
   */
  public void setCoast() {
    pair.setNeutralMode(NeutralMode.Coast);
  }

  /**
   * Set speed controllers to Brake mode
   */
  public void setBrake() {
    pair.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * Get velocity of shaft
   */
  public double getVelocity() {
    return (pair.getSelectedSensorVelocity() * 60.0) / (ShooterConstants.COUNTS_PER_REVOLUTION * 10.0);
  }

   /**
   * Get velocity of shaft
   */
  public double getSetpointVelocity() {
    return SmartDashboard.getNumber("set shooter velocity", 0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("shooter velocity", getVelocity());
  }

  /**
   * Sets encoder to 0
   */
  public void resetEncoder() {
    pair.setSelectedSensorPosition(0);
  }
  
}