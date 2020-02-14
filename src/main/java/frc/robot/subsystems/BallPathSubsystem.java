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

  private DigitalInput beltBannerSensor, firstCellBannerSensor, secondCellBannerSensor, thirdCellBannerSensor, fourthCellBannerSensor, fifthCellBannerSensor;

  /**
   * Constructs the sensor and motor objects
   */
  public BallPathSubsystem() {
    //TODO: move kicker to shooter subsystem
    //TODO: two motors
    indexerMotor = new WPI_TalonSRX(BallPathConstants.INDEXER_MOTOR_CHANNEL);
    beltMotor = new WPI_TalonSRX(BallPathConstants.FIRST_STAGE_MOTOR_CHANNEL);

    beltBannerSensor = new DigitalInput(BallPathConstants.BELT_BANNER_SENSOR_PORT);
    firstCellBannerSensor = new DigitalInput(BallPathConstants.FIRST_CELL_BANNER_PORT);
    secondCellBannerSensor = new DigitalInput(BallPathConstants.SECOND_CELL_BANNER_PORT);
    thirdCellBannerSensor = new DigitalInput(BallPathConstants.THIRD_CELL_BANNER_PORT);
    fourthCellBannerSensor= new DigitalInput(BallPathConstants.FOURTH_CELL_BANNER_PORT);
    fifthCellBannerSensor = new DigitalInput(BallPathConstants.FIFTH_CELL_BANNER_PORT);

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
        if (fourthCellBannerSensor.get()) {
          goal = fifthCellBannerSensor;
        } else if (thirdCellBannerSensor.get()) {
          goal = fourthCellBannerSensor;
        } else if (secondCellBannerSensor.get()) {
          goal = thirdCellBannerSensor;
        } else if (firstCellBannerSensor.get()) {
          goal = secondCellBannerSensor;
        } else {
          goal = firstCellBannerSensor;
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
    return !beltBannerSensor.get() || !firstCellBannerSensor.get() || !secondCellBannerSensor.get() ||
        !thirdCellBannerSensor.get() || !fourthCellBannerSensor.get() || !fifthCellBannerSensor.get();
  }

  /**
   * @return true if the fifth banner sensor is triggered
   */
  public boolean hasFiveBalls() {
    //TODO: make sure this is the last one
    return !fifthCellBannerSensor.get();
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
