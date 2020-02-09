package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.CollectorConstants;

/**
 * Creates a new CollectorSubsystem.
 */
public class CollectorSubsystem extends BeefSubsystemBase {
  private static CollectorSubsystem collectorSubsystem = null;

  private WPI_TalonSRX collectorMotor;
  private Solenoid collectorDeployPiston;
  private CollectorState state;

  private CollectorSubsystem() {
    collectorMotor = new WPI_TalonSRX(CollectorConstants.COLLECTOR_MOTOR_CHANNEL);
    collectorDeployPiston = new Solenoid(CollectorConstants.DEPLOY_COLLECTOR_SOLENOID_CHANNEL);
    state = CollectorState.UNDEPLOYED;
  }

  public static CollectorSubsystem getInstance() {
    if (collectorSubsystem == null)
      collectorSubsystem = new CollectorSubsystem();
    return collectorSubsystem;
  }

  @Override
  public void periodic() {

  }

  public void deploy() {
    collectorDeployPiston.set(true);
    state = CollectorState.DEPLOYED;
  }

  public void undeploy() {
    collectorDeployPiston.set(false);
    state = CollectorState.UNDEPLOYED;
  }

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