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
  private double potValue;
  private double targetPosition;
  private double actualPosition;

  public TurretSubsystem() {

    turretMotor = new WPI_TalonSRX(TurretConstants.TURRET_MOTOR_CHANNEL);

    turretMotor.configSelectedFeedbackSensor(
        FeedbackDevice.Analog,
        TurretConstants.PID_LOOPTYPE,
        TurretConstants.TIMEOUT_MS);

    turretMotor.config_kP(TurretConstants.SLOT_ID, TurretConstants.P);
    turretMotor.config_kI(TurretConstants.SLOT_ID, TurretConstants.I);
    turretMotor.config_kD(TurretConstants.SLOT_ID, TurretConstants.D);
    turretMotor.config_kF(TurretConstants.SLOT_ID, TurretConstants.F);

    createDoubleEntry(TurretConstants.POT_ENTRY, 7, 0, 1, 1, () -> potValue);
    createDoubleEntry(TurretConstants.POSITION_ENTRY, 8, 0, 1, 1, () -> actualPosition);
  }

  /**
   * Set target position for turret. Does validity check in this function.
   *
   * @param targetPosition radians from vision. This is a change in radians, not an absolute position
   */
  public void setTargetPosition(double targetPosition) {
    double tmpTarget = actualPosition + (Units.radiansToDegrees(targetPosition) + TurretConstants.MAX_ROTATION_DEGREES / 2.0);
    if (0 < tmpTarget && tmpTarget < TurretConstants.MAX_ROTATION_DEGREES)
      this.targetPosition = tmpTarget;
    // cannot turn turret that far otherwise
  }

  public void resetTargetWithDrivetrain(double currentDrivetrainHeadingDegrees) {
    double tmpTarget = -currentDrivetrainHeadingDegrees;
    if (0 < tmpTarget && tmpTarget < TurretConstants.MAX_ROTATION_DEGREES)
      this.targetPosition = tmpTarget;
  }

  private double convertTargetToPot(double heading) {
    // convert radians (negative left, positive right) to percent of 270 turn needed
    // 270 / 2 is center
    double percent = (Units.radiansToDegrees(heading) + (TurretConstants.MAX_ROTATION_DEGREES / 2.0)) / TurretConstants.MAX_ROTATION_DEGREES;
    return percent * (TurretConstants.POT_MAX - TurretConstants.POT_MIN) + TurretConstants.POT_MIN;
  }

  private double convertPotToDegrees(double potValue) {
    double percent = (potValue - TurretConstants.POT_MIN) / (TurretConstants.POT_MAX - TurretConstants.POT_MIN);
    return percent * TurretConstants.MAX_ROTATION_DEGREES;
  }

  @Override
  public void periodic() {
    potValue = turretMotor.getSelectedSensorPosition();
    actualPosition = convertPotToDegrees(potValue);
  }

  public void rotateToTarget() {
    rotateToPosition(targetPosition);
  }

  public void rotateToPosition(double targetPosition) {
    turretMotor.set(ControlMode.Position, convertTargetToPot(targetPosition));
  }

  public boolean inPosition() {
    double thisError = targetPosition - actualPosition;
    return Math.abs(thisError) <= TurretConstants.ERROR_TOLERANCE;
  }

  public void setDirection(TurretDirection direction) {
    turretMotor.set(direction.get());
  }

  public void setOff() {
    turretMotor.set(0);
  }

  public enum TurretDirection {
    LEFT(1),
    RIGHT(1);
    private double value;

    TurretDirection(int value) {
      this.value = value;
    }

    public double get() {
      return value;
    }
  }
}
