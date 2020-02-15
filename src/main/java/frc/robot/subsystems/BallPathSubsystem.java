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
  private WPI_TalonSRX indexWheelMotor, beltMotor, gooseneckMotor;

  /**
   * Constructs the sensor and motor objects
   */
  public BallPathSubsystem() {
    indexWheelMotor = new WPI_TalonSRX(BallPathConstants.INDEXER_MOTOR_CHANNEL);
    beltMotor = new WPI_TalonSRX(BallPathConstants.FIRST_STAGE_MOTOR_CHANNEL);
    gooseneckMotor = new WPI_TalonSRX(BallPathConstants.GOOSENECK_CHANNEL);

    beltBannerSensor = new BannerSensor(BallPathConstants.BELT_BANNER_SENSOR_PORT);
    firstCellBannerSensor = new BannerSensor(BallPathConstants.FIRST_CELL_BANNER_PORT);
    secondCellBannerSensor = new BannerSensor(BallPathConstants.SECOND_CELL_BANNER_PORT);
    thirdCellBannerSensor = new BannerSensor(BallPathConstants.THIRD_CELL_BANNER_PORT);
    fourthCellBannerSensor = new BannerSensor(BallPathConstants.FOURTH_CELL_BANNER_PORT);
    fifthCellBannerSensor = new BannerSensor(BallPathConstants.FIFTH_CELL_BANNER_PORT);

  }

  private void indexWheelIn() {
    indexWheelMotor.set(1);
  }

  private void indexWheelOut() {
    indexWheelMotor.set(-1);
  }

  private void stopIndexWheel() {
    indexWheelMotor.set(0);
  }

  private void runBelt() {
    beltMotor.set(1);
  }

  private void spitBelt() {
    beltMotor.set(-1);
  }

  private void stopBelt() {
    beltMotor.set(0);
  }

  private void runGooseneck() {
    gooseneckMotor.set(1);
  }

  private void stopGooseneck() {
    gooseneckMotor.set(0);
  }

  private void spitOutGooseneck() {
    gooseneckMotor.set(-1);
  }

  public void runAll() {
    runBelt();
    runGooseneck();
    indexWheelIn();
  }

  public void spitOutAll() {
    spitBelt();
    spitOutGooseneck();
    indexWheelOut();
  }

  public void runIndexer() {
    indexWheelIn();
    runBelt();
  }

  /**
   * Stops all movement in the ball path
   */
  public void stopAll() {
    stopBelt();
    stopIndexWheel();
    stopGooseneck();
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
