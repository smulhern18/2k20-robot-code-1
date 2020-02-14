package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.BallPathConstants;

/**
 * The ball path subsystem
 * <p>
 * input: six banner sensors, which track the power cells as they move throughout the robot
 * <p>
 * output: the belt, index wheel, and kicker wheel motors
 */
public class BallPathSubsystem extends BeefSubsystemBase {

  private WPI_TalonSRX kickerMotor, indexerMotor, beltMotor;

  private IndexerState indexerState;
  private DigitalInput goal; // reference to the nex banner sensor which should be triggered in indexing process

  private DigitalInput beltBannerSensor, indexer1BannerSensor, indexer2BannerSensor, indexer3BannerSensor, indexer4BannerSensor, indexer5BannerSensor;

  /**
   * Constructs the sensor and motor objects
   */
  public BallPathSubsystem() {
    //TODO: move kicker to shooter subsystem
    //TODO: two motors
    kickerMotor = new WPI_TalonSRX(BallPathConstants.KICKER_MOTOR_CHANNEL);
    indexerMotor = new WPI_TalonSRX(BallPathConstants.INDEXER_MOTOR_CHANNEL);
    beltMotor = new WPI_TalonSRX(BallPathConstants.FIRST_STAGE_MOTOR_CHANNEL);

    beltBannerSensor = new DigitalInput(BallPathConstants.BELT_BANNER_SENSOR_PORT);
    indexer1BannerSensor = new DigitalInput(BallPathConstants.INDEXER1_BANNER_PORT);
    indexer2BannerSensor = new DigitalInput(BallPathConstants.INDEXER2_BANNER_PORT);
    indexer3BannerSensor = new DigitalInput(BallPathConstants.INDEXER3_BANNER_PORT);
    indexer4BannerSensor = new DigitalInput(BallPathConstants.INDEXER4_BANNER_PORT);
    indexer5BannerSensor = new DigitalInput(BallPathConstants.INDEXER5_BANNER_PORT);

    indexerState = IndexerState.UNSHIFTED;
  }

  public void indexIn() {
    indexerMotor.set(1);
  }

  public void indexOut() {
    indexerMotor.set(-1);
  }

  public void stopIndex() {
    indexerMotor.set(0);
  }

  public void kick() {
    kickerMotor.set(1);
  }

  public void kickOut() {
    kickerMotor.set(-1);
  }

  public void stopKick() {
    kickerMotor.set(0);
  }

  public void runBelt() {
    beltMotor.set(1);
  }

  public void spitBelt() {
    beltMotor.set(-1);
  }

  public void stopBelt() {
    beltMotor.set(0);
  }

  /**
   * Shifts until next banner sensor is triggered.
   * Should not be used for shooting
   */
  public void shiftIndexer() {
    switch (indexerState) {
      case UNSHIFTED:
        // determine where to shift to
        //TODO: swap orders/names to something more sensical
        //TODO: if <5 balls, stop if any sensor after the necessary one is triggered
        if (indexer2BannerSensor.get()) {
          goal = indexer1BannerSensor;
        } else if (indexer3BannerSensor.get()) {
          goal = indexer2BannerSensor;
        } else if (indexer4BannerSensor.get()) {
          goal = indexer3BannerSensor;
        } else if (indexer5BannerSensor.get()) {
          goal = indexer4BannerSensor;
        } else {
          goal = indexer5BannerSensor;
        }
        indexerState = IndexerState.SHIFTING;
        break;
      case SHIFTING:
        // keep moving index wheel until next index is met
        if (goal.get())
          indexerState = IndexerState.SHIFTED;
        else {
          indexIn();
          runBelt();
        }
        break;
      case SHIFTED:
        stopBelt();
        stopIndex();
        break;
      default:
        System.out.println("UNEXPECTED INDEXER STATE!");
        break;
    }
  }

  public void resetState() {
    indexerState = IndexerState.UNSHIFTED;
  }

  /**
   * Engages every motor to be in the shooter direction
   */
  public void shoot() {
    kick();
    runBelt();
    indexIn();
  }

  /**
   * Stops all movement in the ball path
   */
  public void stopAll() {
    stopBelt();
    stopKick();
    stopIndex();
  }

  /**
   * Gets the state of the indexing process
   *
   * @return the state of the indexer
   */
  public IndexerState getIndexerState() {
    return indexerState;
  }

  /**
   * @return true if a new power cell is entering the robot
   */
  public boolean getBeltBannerSensor() {
    return !beltBannerSensor.get();
  }

  /**
   * @return true if any banner sensor's beam is broken
   */
  public boolean getAnyBannerSensor() {
    return !beltBannerSensor.get() || !indexer1BannerSensor.get() || !indexer2BannerSensor.get() ||
        !indexer3BannerSensor.get() || !indexer4BannerSensor.get() || !indexer5BannerSensor.get();
  }

  /**
   * @return true if the fifth banner sensor is triggered
   */
  public boolean hasFiveBalls() {
    //TODO: make sure this is the last one
    return !indexer5BannerSensor.get();
  }

  public enum IndexerState {
    UNSHIFTED,
    SHIFTING,
    SHIFTED
  }

  public enum BallPathDirection {
    IN,
    OUT
  }
}
