package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import frc.robot.Constants.TurretConstants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;



public class TurretSubsystem extends GompeiSubsystemBase {
  /**
   * Creates a new TurretSubsystem.
   */
  private WPI_TalonSRX turretMotor;
  private double currentPosition; //degrees
  private double potValue;
  private double targetPosition;
  private double actualPosition;

  public TurretSubsystem(WPI_TalonSRX turretMotor) {

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

  private void setTargetPosition(double targetPosition){
    this.targetPosition = targetPosition;
  }

  private double convertTargetToPot(double heading){
    return ((((heading / 360.0) * TurretConstants.TURRET_SPROCKET_CIRCUMFERENCE) / TurretConstants.POT_SPROCKET_CIRCUMFERENCE)
            / TurretConstants.MAX_POT_ROTATIONS) * TurretConstants.END_POINT;
  }

  private double convertPotToTarget(double potValue){
    return (((potValue / TurretConstants.END_POINT) * TurretConstants.MAX_POT_ROTATIONS) * TurretConstants.POT_SPROCKET_CIRCUMFERENCE /
            TurretConstants.TURRET_SPROCKET_CIRCUMFERENCE) * 360.0;
  }

  @Override
  public void periodic(){
    potValue = turretMotor.getSelectedSensorPosition();
    actualPosition = convertPotToTarget(potValue);
    rotateToPosition(targetPosition);
  }

  public void rotateToPosition(double targetPosition){
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