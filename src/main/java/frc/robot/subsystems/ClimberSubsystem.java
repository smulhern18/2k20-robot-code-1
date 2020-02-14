package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.ClimberConstants;

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
  private DigitalInput topLimitSwitch, bottomLimitSwitch, tentiometerLimitSwitch, slapSwitch;
  private WPI_TalonFX climbMotor;
  private Solenoid slapper, ratchet;
  private ClimbState state;
  private WPI_TalonSRX traverseMotor;

  /**
   * Construct hardware objects, set initial state to untrenchable.
   */
  public ClimberSubsystem() {
    topLimitSwitch = new DigitalInput(ClimberConstants.TOP_SWITCH_PORT);
    bottomLimitSwitch = new DigitalInput(ClimberConstants.BOTTOM_SWITCH_PORT);
    tentiometerLimitSwitch = new DigitalInput(ClimberConstants.TENTIOMETER_SWITCH_PORT);
    slapSwitch = new DigitalInput(ClimberConstants.SLAP_SWITCH_PORT);
    climbMotor = new WPI_TalonFX(ClimberConstants.CLIMB_MOTOR_CHANNEL);
    slapper = new Solenoid(ClimberConstants.SLAPPER_PORT);
    ratchet = new Solenoid(ClimberConstants.RATCHET_PORT);
    this.state = ClimbState.UNTRENCHABLE;

    traverseMotor = new WPI_TalonSRX(ClimberConstants.TRAVERSE_MOTOR_PORT);
  }

  /**
   * Returns the current state of the Climber. Used in the state machines.
   *
   * @return climber state
   */
  public ClimbState getState() {
    return state;
  }

  /**
   * Sets the state of the climber. Should only be used when starting climb.
   *
   * @param state ClimbState object that is the current state of the Climber
   */
  public void setState(ClimbState state) {
    this.state = state;
  }

  /**
   * Sets the ratcheting on or off
   *
   * @param value state of ratchet
   */
  public void setRatchet(boolean value) {
    ratchet.set(value);
  }

  //TODO: make methods commands
  /**
   * Unslaps the climber (sets to vertical position)
   */
  public void unslap() {
    slapper.set(ClimberConstants.UNSLAP);
    setRatchet(ClimberConstants.RATCHET_OFF);
    state = ClimbState.UNSLAPPED;
  }

  /**
   * Call this method periodically to extend
   */
  public void extendClimb() {
    if (topLimitSwitch.get()) { // done extending
      climbMotor.set(ClimberConstants.CLIMB_OFF);
      state = ClimbState.EXTENDED;
    } else if (!tentiometerLimitSwitch.get()) { // catch up unspooling
      climbMotor.set(ClimberConstants.CLIMB_OFF);
    } else { // unspool
      climbMotor.set(ClimberConstants.CLIMB_EXTEND);
    }
  }

  /**
   * Slaps climber
   */
  public void slap() {
    if (slapSwitch.get()) {
      state = ClimbState.SLAPPED;
      setRatchet(ClimberConstants.RATCHET_ON);
    }
    slapper.set(ClimberConstants.SLAP);
  }

  /**
   * Call this method periodically to retract
   */
  public void retractClimb() {
    if (bottomLimitSwitch.get()) {
      climbMotor.set(ClimberConstants.CLIMB_OFF);
      state = ClimbState.DONE;
    } else {
      climbMotor.set(ClimberConstants.CLIMB_RETRACT);
    }
  }

  /**
   * Turns off retracting/extending falcon
   */
  public void climbOff() {
    climbMotor.set(ClimberConstants.CLIMB_OFF);
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
   * Represents the state of the CLimber. Used by state machines.
   */
  public enum ClimbState {
    TRENCHABLE,
    UNTRENCHABLE,
    UNSLAPPED,
    EXTENDED,
    SLAPPED,
    DONE
  }

  /**
   * Mapping of directions of traversal. Uses an enum to prevent any other speed from being set.
   */
  public enum TraverseDirection {
    LEFT(.75),
    RIGHT(-.75),
    OFF(0);

    private double value;

    private TraverseDirection(double value) {
      this.value = value;
    }

    public double get() {
      return value;
    }
  }
}
