package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.CollectorConstants;

/**
 * The collector subsystem
 * <p>
 * input: none
 * <p>
 * output: a motor and a cylinder for deploying
 */
public class CollectorSubsystem extends BeefSubsystemBase {

  private WPI_TalonSRX collectorMotor;
  private Solenoid collectorDeployPiston;
  private CollectorState state;

  public CollectorSubsystem() {
    collectorMotor = new WPI_TalonSRX(CollectorConstants.COLLECTOR_MOTOR_CHANNEL);
    collectorDeployPiston = new Solenoid(CollectorConstants.DEPLOY_COLLECTOR_SOLENOID_CHANNEL);
    state = CollectorState.UNDEPLOYED;
  }

  @Override
  public void periodic() {

  }

  /**
   * Extend outside frame perimeter
   */
  public void deploy() {
    collectorDeployPiston.set(true);
    state = CollectorState.DEPLOYED;
  }

  /**
   * Get back inside frame perimeter
   */
  public void undeploy() {
    collectorDeployPiston.set(false);
    state = CollectorState.UNDEPLOYED;
  }

  /**
   * bring balls in
   */
  public void intake() {
    collectorMotor.set(1);
  }

  public void exhaust() {
    collectorMotor.set(-1);
  }

  public void stopIntake() {
    collectorMotor.set(0);
  }

  public CollectorState getState() {
    return state;
  }

  public void setState(CollectorState state) {
    this.state = state;
  }

  public enum CollectorState {
    DEPLOYED,
    UNDEPLOYED
  }
}