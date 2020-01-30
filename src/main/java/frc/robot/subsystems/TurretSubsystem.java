package frc.robot.subsystems;

import frc.robot.Constants.TurretConstants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class TurretSubsystem extends GompeiSubsystemBase {
  /**
   * Creates a new TurretSubsystem.
   */
  private WPI_TalonSRX turretMotor;

  public TurretSubsystem(WPI_TalonSRX turretMotor) {
    turretMotor = new WPI_TalonSRX(TurretConstants.TURRET_MOTOR_CHANNEL);
  }
}