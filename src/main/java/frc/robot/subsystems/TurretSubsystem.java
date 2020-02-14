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

    createDoubleEntry(TurretConstants.POT_ENTRY, 7, 0, 1, 1, this::getCurrentPotPosition);
    createDoubleEntry(TurretConstants.POSITION_ENTRY, 8, 0, 1, 1, this::getCurrentPositionDegrees);
  }

  private double convertTargetToPot(double heading) { //takes input in radians
    return ((Units.radiansToDegrees(heading)  / TurretConstants.MAX_CAPABILITY_DEGREES) * (TurretConstants.END_POINT - TurretConstants.START_POINT) + TurretConstants.START_POINT);
  }

  private double convertPotToTarget(double potValue) { // will have to adjust with real pot values (whole range won't be utiilized)
    return Units.degreesToRadians(((potValue - TurretConstants.START_POINT) / (TurretConstants.END_POINT - TurretConstants.START_POINT)) * TurretConstants.MAX_CAPABILITY_DEGREES);
  }

  @Override
  public void periodic() {
  }

  public void rotateToPosition(double targetPosition) {
    turretMotor.set(ControlMode.Position, convertTargetToPot(targetPosition));
  }

  public boolean inPosition(double targetPosition) {
    double thisError = targetPosition - getCurrentPositionDegrees();
    return Math.abs(thisError) <= TurretConstants.ERROR_TOLERANCE;
  }

  public double getCurrentPotPosition(){
    return turretMotor.getSelectedSensorPosition();
  }

  public double getCurrentPositionDegrees(){
    return convertPotToTarget(turretMotor.getSelectedSensorPosition());
  }

  public void manualRotateTurret(double speed) { // for manual control of turret
    turretMotor.set(ControlMode.PercentOutput, speed);
  }


}
