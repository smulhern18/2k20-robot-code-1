package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends BeefSubsystemBase {
  /**
   * Creates a new TurretSubsystem.
   */
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

  public void setTargetPosition(double targetPosition) {
    this.targetPosition = targetPosition;
  }

  private double convertTargetToPot(double heading) { //takes input in radians
    return ((Units.radiansToDegrees(heading)  / TurretConstants.MAX_CAPABILITY_DEGREES) * (TurretConstants.END_POINT - TurretConstants.START_POINT) + TurretConstants.START_POINT);
  }

  private double convertPotToTarget(double potValue) { // will have to adjust with real pot values (whole range won't be utiilized)
    return Units.degreesToRadians(((potValue - TurretConstants.START_POINT) / (TurretConstants.END_POINT - TurretConstants.START_POINT)) * TurretConstants.MAX_CAPABILITY_DEGREES);
  }

  @Override
  public void periodic() {
    potValue = turretMotor.getSelectedSensorPosition();
    actualPosition = convertPotToTarget(potValue);
  }

  public void rotateToPosition(double targetPosition) {
    turretMotor.set(ControlMode.Position, convertTargetToPot(targetPosition));
  }

  public boolean inPosition() {
    double thisError = targetPosition - actualPosition;
    return Math.abs(thisError) <= TurretConstants.ERROR_TOLERANCE;
  }

  public void manualRotateTurret(double speed) { // for manual control of turret
    turretMotor.set(ControlMode.PercentOutput, speed);
  }


}
