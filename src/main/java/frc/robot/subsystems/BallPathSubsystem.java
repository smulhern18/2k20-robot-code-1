package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
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

  private WPI_TalonSRX indexerMotor, beltMotor;

  public BannerSensor beltBannerSensor, firstCellBannerSensor, secondCellBannerSensor, thirdCellBannerSensor, fourthCellBannerSensor, fifthCellBannerSensor;

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
    fourthCellBannerSensor= new BannerSensor(BallPathConstants.FOURTH_CELL_BANNER_PORT);
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
   * @return true if any banner sensor's beam is broken
   */
  public boolean getAnyBannerSensor() {
    return beltBannerSensor.beamBroken() || firstCellBannerSensor.beamBroken() || secondCellBannerSensor.beamBroken() ||
        thirdCellBannerSensor.beamBroken() || fourthCellBannerSensor.beamBroken() || fifthCellBannerSensor.beamBroken();
  }

  /**
   * @return true if the fifth banner sensor is triggered
   */
  public boolean hasFiveBalls() {
    return fifthCellBannerSensor.beamBroken();
  }

  public enum BallPathDirection {
    IN,
    OUT
  }
}
