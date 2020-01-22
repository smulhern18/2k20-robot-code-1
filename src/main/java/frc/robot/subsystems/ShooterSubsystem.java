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
  public Velocity targetVelocity = new Velocity(),
      currentVelocity = new Velocity();


  /**
   * Creates a new ShooterSubsystem.
   */
  public ShooterSubsystem() {
    pair = new PairedTalonSRX(ShooterConstants.LEADER_CHANNEL, ShooterConstants.FOLLOWER_CHANNEL);
    pair.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, ShooterConstants.PID_LOOPTYPE, ShooterConstants.TIMEOUT_MS);
    pair.configPIDF(0, ShooterConstants.P, ShooterConstants.I, ShooterConstants.D, ShooterConstants.F);

    setCoast();

    setDefaultCommand(new ShootCommand(this));
  }

  /**
   * Changes the target rotational velocity of the shooter
   *
   * @param rpm
   */
  public void setTargetRPM(double rpm) {
    targetVelocity.setRPM(rpm);
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
  private void shoot(Velocity velocity) {
    shoot(ControlMode.Velocity, velocity.getCPD());
  }

  /**
   * Runs the shooter to spin at target velocity
   */
  public void shoot() {
    shoot(targetVelocity);
  }

  /**
   * Stops shooter wheel from spinning, sets target to 0
   */
  public void stop() {
    targetVelocity.setRPM(0);
    shoot(ControlMode.PercentOutput, 0);
  }

  /**
   * Set speed controllers to Coast mode
   */
  private void setCoast() {
    pair.setNeutralMode(NeutralMode.Coast);
  }


  /**
   * Puts current velocity of shooter wheel
   */
  @Override
  public void periodic() {
    SmartDashboard.putString("Shooter Velocity", currentVelocity.toString());
    currentVelocity.setCPD(pair.getSelectedSensorVelocity());
  }

  /**
   * Class represents a velocity. Stores in both Rotations Per Minute and Counts Per 100 Milliseconds
   */
  public static class Velocity {
    private double rpm, cpd;

    /**
     * Inits values to 0 for safety
     */
    public Velocity() {
      rpm = 0;
      cpd = 0;
    }

    /**
     * Gets rotations per minute
     *
     * @return velocity in rotations per minute
     */
    public double getRPM() {
      return rpm;
    }

    /**
     * Sets velocity in rotations per minute
     *
     * @param value rotations per minute
     */
    public void setRPM(double value) {
      this.rpm = value;
      cpd = value * ShooterConstants.COUNTS_PER_REVOLUTION * (1.0 / 10.0) * (1.0 / 60.0);
    }

    /**
     * Gets Counts per decisec (100ms)
     *
     * @return
     */
    public double getCPD() {
      return cpd;
    }

    /**
     * Sets velocity in Counts per decisec (100ms)
     *
     * @param value
     */
    public void setCPD(double value) {
      this.cpd = value;
      rpm = value * 60.0 * (1 / ShooterConstants.COUNTS_PER_REVOLUTION) * 10.0;
    }

    /**
     * Returns velocity in both units
     *
     * @return Shooter velocity: (xxxx rpm), (xxxx cpd)
     */
    @Override
    public String toString() {
      return String.format("Shooter velocity: (%f rpm), (%f cpd)", rpm, cpd);
    }
  }

}