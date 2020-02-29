package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  private DigitalInput bottomLimitSwitch;
  private WPI_TalonFX climbMotor;
  public DoubleSolenoid slapper;
  private Solenoid trigger;
  private WPI_TalonSRX traverseMotor;

  /**
   * Construct hardware objects
   */
  public ClimberSubsystem() {
    bottomLimitSwitch = new DigitalInput(ClimberConstants.BOTTOM_SWITCH_PORT);// indicates when you reach the top
    climbMotor = new WPI_TalonFX(ClimberConstants.CLIMB_MOTOR_CHANNEL);
    slapper = new DoubleSolenoid(ClimberConstants.SLAPPER_PORT, ClimberConstants.UNSLAPPER_PORT);
    trigger = new Solenoid(ClimberConstants.TRIGGER_PORT);

    traverseMotor = new WPI_TalonSRX(ClimberConstants.TRAVERSE_MOTOR_PORT);
    trigger.set(false);

  }

  /**
   * Unslaps the climber (sets to vertical position)
   */
  public void unslap() {
    slapper.set(DoubleSolenoid.Value.kForward);
  }

  /**
   * Slaps climber
   */
  public void slap() {
    slapper.set(DoubleSolenoid.Value.kReverse);
  }

  public void triggerClimb() {
    trigger.set(true);
  }
  public void untrigger() {
    trigger.set(false);
  }

  /**
   * Turns off retracting/extending falcon
   */
  public void climbOff() {
    climbMotor.set(ClimberConstants.CLIMB_OFF);
  }

  /**
   * @return if the climber has hit the top
   */
  public boolean atBottom() {
    return bottomLimitSwitch.get();
  }

  /**
   * starts spooling or begins to retract the climber
   */
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
    LEFT(1),
    RIGHT(-1),
    OFF(0);

    private double value;

    TraverseDirection(double value) {
      this.value = value;
    }

    public double get() {
      return value;
    }
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("bottom", bottomLimitSwitch.get());
  }
}
