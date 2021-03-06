package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.Constants.TurretConstants;

/**
 * Creates a new TurretSubsystem.
 */
public class TurretSubsystem extends BeefSubsystemBase {

  private WPI_TalonSRX turretMotor;

  /**
   * Constructs a turret
   */
  public TurretSubsystem() {

    turretMotor = new WPI_TalonSRX(TurretConstants.TURRET_MOTOR_CHANNEL);

    turretMotor.configSelectedFeedbackSensor(
        FeedbackDevice.QuadEncoder,
        TurretConstants.PID_LOOPTYPE,
        TurretConstants.TIMEOUT_MS);

    turretMotor.config_kP(TurretConstants.SLOT_ID, TurretConstants.P);
    turretMotor.config_kI(TurretConstants.SLOT_ID, TurretConstants.I);
    turretMotor.config_kD(TurretConstants.SLOT_ID, TurretConstants.D);
    turretMotor.config_kF(TurretConstants.SLOT_ID, TurretConstants.F);

    createDoubleEntry(TurretConstants.POT_ENTRY, 3, 0, 1, 1, this::getCurrentPositionDegrees);
    createDoubleEntry(TurretConstants.POSITION_ENTRY, 3, 1, 1, 1, turretMotor::getSelectedSensorPosition);
  }

  /**
   * Converts pot units to degrees
   *
   * @param heading
   * @return equivalent potentiometer values
   */
  public static double convertDegreesToPot(double heading) {
    // convert radians (negative left, positive right) to percent of 270 turn needed
    //give number between -45 and 225
    // Min on pot corresponds to MIN_ROTATION_DEGREES
    // Max on pot corresponds to MAX_ROTATION_DEGREES
    // default is 0
//    System.out.println("RUNNING");

    double percent = (heading - TurretConstants.MIN_ROTATION_DEGREES) / TurretConstants.TOTAL_ROTATION_DEGREES;
    System.out.println(heading+" "+percent+" "+(percent * (TurretConstants.POT_MAX - TurretConstants.POT_MIN) + TurretConstants.POT_MIN));
    return percent * (TurretConstants.POT_MAX - TurretConstants.POT_MIN) + TurretConstants.POT_MIN;
  }

  /**
   * Converts pot units to degrees
   *
   * @param potValue
   * @return equivalent degrees on the turret
   */
  private static double convertPotToDegrees(double potValue) {
    double percent = (potValue - TurretConstants.POT_MIN) / (TurretConstants.POT_MAX - TurretConstants.POT_MIN);
    return (percent * TurretConstants.TOTAL_ROTATION_DEGREES) + TurretConstants.MIN_ROTATION_DEGREES;//adjust for any offcenter rotation
  }

  /**
   * Causes the Turret to follow the original orientation of the drive train (theoretically points in general position of power port)
   *
   * @param currentDrivetrainHeadingDegrees
   */
  public void resetTargetWithDrivetrain(double currentDrivetrainHeadingDegrees) {
    double tmpTarget = -currentDrivetrainHeadingDegrees;
    if (TurretConstants.MIN_ROTATION_DEGREES < tmpTarget && tmpTarget < TurretConstants.MAX_ROTATION_DEGREES) {
      rotateToPosition(tmpTarget);
    } else {
      rotateToPosition(getCurrentPositionDegrees());
    }
  }

  /**
   * Rotates the turret to the target position
   *
   * @param targetPositionDegrees
   */
  public void rotateToPosition(double targetPositionDegrees) {
//    convertDegreesToPot(targetPositionDegrees);
    turretMotor.set(ControlMode.Position, convertDegreesToPot(targetPositionDegrees));
  }

  /**
   * Checks if the turret is in the correct position
   *
   * @param targetPosition
   * @return if the turret is within threshold of the correct target position
   */
  public boolean inPosition(double targetPosition) {
    double thisError = targetPosition - getCurrentPositionDegrees();
    return Math.abs(thisError) <= TurretConstants.ERROR_TOLERANCE;
  }

  /**
   * Gets the current position of the potentiometer
   *
   * @return the potentiometer value
   */
  public double getCurrentPotPosition() {
    return -turretMotor.getSelectedSensorPosition();
  }

  public String getPosition() {
    return Double.toString(getCurrentPositionDegrees()) +" degrees";
  }

  /**
   *Gets the current position of the potentiometer converted to degrees of the turret
   *
   * @return the position in degrees of the turret
   */
  public double getCurrentPositionDegrees() {
    return convertPotToDegrees(turretMotor.getSelectedSensorPosition());
  }


  public void resetTurretEncoder() {
    turretMotor.setSelectedSensorPosition(0);
//    System.out.println("Running");
  }
  /**
   * Manually rotates the turret given a speed
   *
   * @param speed
   */
  public void manualRotateTurret(double speed) { // for manual control of turret
    if ((speed > 0 && getCurrentPotPosition() > TurretConstants.POT_MIN) || (speed < 0 && getCurrentPotPosition() < TurretConstants.POT_MAX)) {
      turretMotor.set(ControlMode.PercentOutput, speed);
    } else
      turretMotor.set(ControlMode.PercentOutput, 0);

  }

  /**
   * Sets the direction of the turret
   *
   * @param direction
   */
  public void setDirection(TurretDirection direction) {
    if ((direction == TurretDirection.RIGHT && getCurrentPotPosition() > TurretConstants.POT_MIN) || (direction == TurretDirection.LEFT && getCurrentPotPosition() < TurretConstants.POT_MAX)) {
      manualRotateTurret(direction.get());
    } else
      manualRotateTurret(0);
  }

  /**
   * Stops movement of the turret
   */
  public void setOff() {
    turretMotor.set(0);
  }

  public enum TurretDirection {
    //TODO: cut button for turret manuals
    LEFT(-.3),
    RIGHT(.3);
    private double value;

    TurretDirection(double value) {
      this.value = value;
    }

    public double get() {
      return value;
    }
  }
}
