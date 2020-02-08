package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants.CollectorConstants;

public class CollectorSubsystem extends BeefSubsystemBase {
  /**
   * Creates a new CollectorSubsystem.
   */
  private WPI_TalonSRX collectorMotor;

  public CollectorSubsystem() {
    collectorMotor = new WPI_TalonSRX(CollectorConstants.COLLECTOR_MOTOR_CHANNEL);
  }

  @Override
  public void periodic(){

  }

  public void collect(){//collects balls
    collectorMotor.set(ControlMode.PercentOutput, 0.48);
  }


}