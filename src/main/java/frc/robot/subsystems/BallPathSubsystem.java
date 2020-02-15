package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants.BallPathConstants;
import frc.robot.models.sensors.BannerSensor;

/**
 * The ball path subsystem
 * <p>
 * input: six banner sensors, which track the power cells as they move throughout the robot
 * <p>
 * output: the belt, index wheel, and kicker wheel motors
 */
public class BallPathSubsystem extends BeefSubsystemBase {

  public BannerSensor beltBannerSensor, firstCellBannerSensor, secondCellBannerSensor, thirdCellBannerSensor, fourthCellBannerSensor, fifthCellBannerSensor;
  private WPI_TalonSRX indexerMotor, beltMotor;

  /**
   * Constructs the sensor and motor objects
   */
  public BallPathSubsystem() {
    indexerMotor = new WPI_TalonSRX(BallPathConstants.INDEXER_MOTOR_CHANNEL);
    beltMotor = new WPI_TalonSRX(BallPathConstants.FIRST_STAGE_MOTOR_CHANNEL);

    beltBannerSensor = new BannerSensor(BallPathConstants.BELT_BANNER_SENSOR_PORT);
    firstCellBannerSensor = new BannerSensor(BallPathConstants.FIRST_CELL_BANNER_PORT);
    secondCellBannerSensor = new BannerSensor(BallPathConstants.SECOND_CELL_BANNER_PORT);
    thirdCellBannerSensor = new BannerSensor(BallPathConstants.THIRD_CELL_BANNER_PORT);
    fourthCellBannerSensor = new BannerSensor(BallPathConstants.FOURTH_CELL_BANNER_PORT);
    fifthCellBannerSensor = new BannerSensor(BallPathConstants.FIFTH_CELL_BANNER_PORT);

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
   * Engages every motor to be in the shooter direction
   */
  public void shoot() {
    runBelt();
    indexIn();
  }

  /**
   * Stops all movement in the ball path
   */
  public void stopAll() {
    stopBelt();
    stopIndex();
  }


  /**
   * Gets the amount of balls in the robot
   *
   * @return int number of balls
   */
  public int getBallsInRobot() {
    if (fifthCellBannerSensor.beamBroken()) {
      return 5;
    } else if (fourthCellBannerSensor.beamBroken()) {
      return 4;
    } else if (thirdCellBannerSensor.beamBroken()) {
      return 3;
    } else if (secondCellBannerSensor.beamBroken()) {
      return 2;
    } else if (firstCellBannerSensor.beamBroken()) {
      return 1;
    } else {
      return 0;
    }
  }

  public enum BallPathDirection {
    IN,
    OUT
  }
}
