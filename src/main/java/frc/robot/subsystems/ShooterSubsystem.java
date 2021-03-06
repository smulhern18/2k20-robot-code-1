package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import frc.robot.Constants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.models.speedcontrollers.PairedTalonSRX;

import java.util.Map;

/**
 * The shooter
 */
public class ShooterSubsystem extends BeefSubsystemBase {

  private PairedTalonSRX pair;
  private NetworkTableEntry bonusShooterRPMEntry;

  /**
   * Creates a new ShooterSubsystem.
   */
  public ShooterSubsystem() {
    pair = new PairedTalonSRX(
        ShooterConstants.LEADER_CHANNEL,
        ShooterConstants.FOLLOWER_CHANNEL);
    pair.configSelectedFeedbackSensor(
        FeedbackDevice.QuadEncoder,
        ShooterConstants.PID_LOOPTYPE,
        ShooterConstants.TIMEOUT_MS);
    pair.configPIDF(
        ShooterConstants.SLOT_ID,
        ShooterConstants.P,
        ShooterConstants.I,
        ShooterConstants.D,
        ShooterConstants.F);
    pair.setInverted(true);


    setCoast();
    createStringEntry(ShooterConstants.VELOCITY_ENTRY, 4, 0, 4, 1, this::veloctityToString);
    bonusShooterRPMEntry = Constants.SubsystemConstants.DRIVER_TAB.add("Shooter bonus RPM", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", -500.0, "max", 500.0))
        .withPosition(2, 2)
        .withSize(3, 1)
        .getEntry();
  }

  /**
   * @param rpm
   * @return rpm converted to cpd
   */
  public static double convertRPMToCPD(double rpm) {
    return rpm * ShooterConstants.COUNTS_PER_REVOLUTION * (1.0 / 10.0) * (1.0 / 60.0) * (1.0 / ShooterConstants.MOTOR_TO_WHEEL);
  }

  /**
   * @param cpd
   * @return cpd converted to rpm
   */
  public static double convertCPDToRPM(double cpd) {
    return cpd * 60.0 * (1 / ShooterConstants.COUNTS_PER_REVOLUTION) * 10.0 * ShooterConstants.MOTOR_TO_WHEEL;
  }

  /**
   * Sets the speed of the pair of motors
   * @param v
   */
  public void set(double v) {
    pair.set(ControlMode.PercentOutput, v);
  }

  /**
   * Configures PIDF of the paired talons
   * @param P
   * @param I
   * @param D
   * @param F
   */
  public void configPIDF(double P, double I, double D, double F) {
    pair.configPIDF(ShooterConstants.SLOT_ID, P, I, D, F);
  }

  /**
   * Shoot with a specified mode
   *
   * @param mode  A {@link ControlMode}
   * @param value value
   */
  public void shoot(ControlMode mode, double value) {
    pair.set(mode, value);
  }

  /**
   * Set flywheel to specified velocity
   *
   * @param velocityRPM RPM velocity
   */
  public void shoot(double velocityRPM) {
    double bonusRPM = bonusShooterRPMEntry.getDouble(0);

    if (atTargetRPM(velocityRPM)) {
      shoot(ControlMode.Velocity, convertRPMToCPD(bonusRPM + velocityRPM));
    } else {
      shoot(ControlMode.PercentOutput, .75);
    }
  }

  /**
   * Stops shooter wheel from spinning, sets target to 0
   */
  public void stop() {
    shoot(ControlMode.PercentOutput, 0);
  }

  /**
   * Does conversion of inches to RPM
   *
   * @param inches
   * @return RPM of the shooter necessary to reach the distance
   */
  public static double inchesToRPM(double inches) {
    return metersToRPM(inches * 0.0254);
  }

  /**
   * Does conversion of meters to RPM
   *
   * @param meters
   * @return RPM of the shooter necessary to reach the distance
   */
  public static double metersToRPM(double meters) {
    return meters; //TODO: line of best fit
  }

  /**
   * Sees if motors are up to speed
   *
   * @return Whether error is within RPM_THRESHOLD
   */
  public boolean atTargetRPM(double targetRPM) {
    return Math.abs(targetRPM - convertCPDToRPM(pair.getSelectedSensorVelocity())) < ShooterConstants.RPM_THRESHOLD;
  }

  /**
   * Set speed controllers to Coast mode
   */
  private void setCoast() {
    pair.setNeutralMode(NeutralMode.Coast);
  }

  /**
   * Returns velocity in both units
   *
   * @return Shooter velocity: (xxxx rpm), (xxxx cpd)
   */
  public String veloctityToString() {
    double cpd = pair.getSelectedSensorVelocity();
    return String.format("Shooter velocity: (%f rpm), (%f cpd)", convertCPDToRPM(cpd), cpd);
  }
}