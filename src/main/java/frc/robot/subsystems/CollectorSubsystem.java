package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  public boolean state = false;

  public CollectorSubsystem() {
    collectorMotor = new WPI_TalonSRX(CollectorConstants.COLLECTOR_MOTOR_CHANNEL);
    collectorDeployPiston = new Solenoid(CollectorConstants.DEPLOY_COLLECTOR_SOLENOID_CHANNEL);
  }

  public double getCollectorSpeed() {
    return collectorMotor.get();
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
    collectorMotor.set(.8);
    state = true;
  }

  /**
   * push balls out
   */
  public void exhaust() {
    collectorMotor.set(.3);
    state = true;
  }

  /**
   * stop the collector
   */
  public void stopIntake() {
    collectorMotor.set(0);
    state = false;
  }

  /**
   *
   * @return whether the collector is deployed or not
   */
  public CollectorState getState() {
    return collectorDeployPiston.get() ? CollectorState.DEPLOYED : CollectorState.UNDEPLOYED;
  }

  /**
   * States of the Collector
   */
  public enum CollectorState {
    DEPLOYED,
    UNDEPLOYED
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("collector", state);
  }
}