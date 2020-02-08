package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.BallpathConstants;

public class BallPathSubsystem extends BeefSubsystemBase {
  private WPI_TalonSRX kickerMotor, indexerMotor, beltMotor;

  private int amountOfBallsContained = 0;

  private IndexerState indexerState;
  private DigitalInput goal;

  private DigitalInput beltBannerSensor, indexer1BannerSensor, indexer2BannerSensor, indexer3BannerSensor, indexer4BannerSensor, indexer5BannerSensor;

  /**
   * Creates a new ExampleSubsystem.
   */
  public BallPathSubsystem() {

    kickerMotor = new WPI_TalonSRX(BallpathConstants.KICKER_MOTOR_CHANNEL);
    indexerMotor = new WPI_TalonSRX(BallpathConstants.INDEXER_MOTOR_CHANNEL);
    beltMotor = new WPI_TalonSRX(BallpathConstants.FIRST_STAGE_MOTOR_CHANNEL);

    beltBannerSensor = new DigitalInput(BallpathConstants.BELT_BANNER_SENSOR_PORT);
    indexer1BannerSensor = new DigitalInput(BallpathConstants.INDEXER1_BANNER_PORT);
    indexer2BannerSensor = new DigitalInput(BallpathConstants.INDEXER2_BANNER_PORT);
    indexer3BannerSensor = new DigitalInput(BallpathConstants.INDEXER3_BANNER_PORT);
    indexer4BannerSensor = new DigitalInput(BallpathConstants.INDEXER4_BANNER_PORT);
    indexer5BannerSensor = new DigitalInput(BallpathConstants.INDEXER5_BANNER_PORT);

    indexerState = IndexerState.UNSHIFTED;

    createDoubleEntry(BallpathConstants.BALLS_CONTAINED_ENTRY, 9, 0, 1, 1, () -> amountOfBallsContained);
  }

  public void setPreloadedBalls(int amountOfPreloadedBalls) {
    amountOfBallsContained = amountOfPreloadedBalls;
  }

  public void kick() {
    kickerMotor.set(1);
  }

  public void stopKick() {
    kickerMotor.set(0);
  }

  public void runBelt() {
    beltMotor.set(1);
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
          indexerMotor.set(1);
          runBelt();
        }
        break;
      case SHIFTED:
        beltMotor.set(0);
        indexerMotor.set(0);
        break;
      default:
        System.out.println("UNEXPECTED INDEXER STATE!");
        break;
    }
  }

  public void resetState() {
    indexerState = IndexerState.UNSHIFTED;
  }

  public void shoot() {
    kick();
    runBelt();
    indexerMotor.set(1);
  }

  public IndexerState getIndexerState() {
    return indexerState;
  }

  public boolean getBeltBannerSensor() {
    return beltBannerSensor.get();
  }

  public boolean isLoaded() {
    return indexer5BannerSensor.get();
  }

  public enum IndexerState {
    UNSHIFTED,
    SHIFTING,
    SHIFTED
  }
}
