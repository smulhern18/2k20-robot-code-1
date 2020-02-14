package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.ClimberConstants;
import frc.robot.models.sensors.Tensiometer;

/**
 * The climber subsystem
 * This includes climber prep, slapping, climbing, and traversing.
 * <p>
 * input: three limit switches for determining lift location and speed, and a reed switch for determining slap state
 * <p>
 * output: a motor for climbing, a motor for traversing a winch cylinder, and a slapping cylinder
 */
public class ClimberSubsystem extends BeefSubsystemBase {

  // tentiometer is the limit switch that ensures that unspooling is going slow enough
  //TODO: make tentiometer class
  private DigitalInput topLimitSwitch, bottomLimitSwitch, slapSwitch;
  public Tensiometer tensiometer;
  private WPI_TalonFX climbMotor;
  private Solenoid slapper, ratchet;
  private WPI_TalonSRX traverseMotor;

  /**
   * Construct hardware objects, set initial state to untrenchable.
   */
  public ClimberSubsystem() {
    topLimitSwitch = new DigitalInput(ClimberConstants.TOP_SWITCH_PORT);
    bottomLimitSwitch = new DigitalInput(ClimberConstants.BOTTOM_SWITCH_PORT);
    tensiometer = new Tensiometer(ClimberConstants.TENTIOMETER_SWITCH_PORT);
    slapSwitch = new DigitalInput(ClimberConstants.SLAP_SWITCH_PORT);
    climbMotor = new WPI_TalonFX(ClimberConstants.CLIMB_MOTOR_CHANNEL);
    slapper = new Solenoid(ClimberConstants.SLAPPER_PORT);
    ratchet = new Solenoid(ClimberConstants.RATCHET_PORT);

    traverseMotor = new WPI_TalonSRX(ClimberConstants.TRAVERSE_MOTOR_PORT);
  }

  /**
   * Sets the ratcheting on or off
   *
   * @param value state of ratchet
   */
  public void setRatchet(boolean value) {
    ratchet.set(value);
  }

  /**
   * Unslaps the climber (sets to vertical position)
   */
  public void unslap() {
    slapper.set(ClimberConstants.UNSLAP);
    setRatchet(ClimberConstants.RATCHET_OFF);
  }

  /**
   * Slaps climber
   */
  public void slap() {
    setRatchet(ClimberConstants.RATCHET_ON);
    slapper.set(ClimberConstants.SLAP);
  }

  public boolean isSlapped() {
    return slapSwitch.get();
  }

  /**
   * Turns off retracting/extending falcon
   */
  public void climbOff() {
    climbMotor.set(ClimberConstants.CLIMB_OFF);
  }

  public boolean atTop() {
    return topLimitSwitch.get();
  }

  public boolean atBottom() {
    return bottomLimitSwitch.get();
  }

  public void unspool() {
    climbMotor.set(ClimberConstants.CLIMB_EXTEND);
  }

  public void spool() {
    climbMotor.set(ClimberConstants.CLIMB_RETRACT);
  }


  /**
   * Sets direction of traversal
   *
   * @param direction direction of traversal
   */
  public void setTraverseDirection(TraverseDirection direction) {
    traverseMotor.set(direction.get());
  }


  /**
   * Mapping of directions of traversal. Uses an enum to prevent any other speed from being set.
   */
  public enum TraverseDirection {
    LEFT(.75),
    RIGHT(-.75),
    OFF(0);

    private double value;

    TraverseDirection(double value) {
      this.value = value;
    }

    public double get() {
      return value;
    }
  }
}
