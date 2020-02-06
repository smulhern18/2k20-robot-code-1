package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends GompeiSubsystemBase {
  private DigitalInput topLimitSwitch, bottomLimitSwitch, tentiometerLimitSwitch, slapSwitch;
  private WPI_TalonFX climbMotor;
  private Solenoid slapper, ratchet;
  private ClimbState state;
  private WPI_TalonSRX traverseMotor;

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

  public ClimbState getState() {
    return state;
  }

  public void setState(ClimbState state) {
    this.state = state;
  }

  public void setRatchet(boolean value) {
    ratchet.set(value);
  }

  public void unslap() {
    slapper.set(ClimberConstants.UNSLAP);
    setRatchet(ClimberConstants.RATCHET_OFF);
    state = ClimbState.UNSLAPPED;
  }

  /**
   * Call this method periodically to extend
   */
  public void extendClimb() {
    if (topLimitSwitch.get()) {
      climbMotor.set(ClimberConstants.CLIMB_OFF);
      state = ClimbState.EXTENDED;
    } else if (!tentiometerLimitSwitch.get()) {
      climbMotor.set(ClimberConstants.CLIMB_OFF);
    } else {
      climbMotor.set(ClimberConstants.CLIMB_EXTEND);
    }
  }

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

  public void climbOff() {
    climbMotor.set(ClimberConstants.CLIMB_OFF);
  }

  public void setTraverseSpeed(double speed) {
    traverseMotor.set(speed);
  }

  public enum ClimbState {
    TRENCHABLE,
    UNTRENCHABLE,
    UNSLAPPED,
    EXTENDED,
    SLAPPED,
    DONE
  }
}
