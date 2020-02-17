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

  public CollectorSubsystem() {
    collectorMotor = new WPI_TalonSRX(CollectorConstants.COLLECTOR_MOTOR_CHANNEL);
    collectorDeployPiston = new Solenoid(CollectorConstants.DEPLOY_COLLECTOR_SOLENOID_CHANNEL);
  }

  /**
   * Extend outside frame perimeter
   */
  public void deploy() {
    collectorDeployPiston.set(true);
  }

  /**
   * Get back inside frame perimeter
   */
  public void undeploy() {
    collectorDeployPiston.set(false);
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
    return collectorDeployPiston.get() ? CollectorState.DEPLOYED : CollectorState.UNDEPLOYED;
  }

  public enum CollectorState {
    DEPLOYED,
    UNDEPLOYED
  }
}