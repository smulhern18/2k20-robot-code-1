package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.BallPathConstants;
import frc.robot.models.sensors.BannerSensor;

/**
 * The ball path subsystem
 * <p>
 * input: six banner sensors, which track the power cells as they move throughout the robot
 * <p>
 * output: the belt, index wheel, and gooseneck motor
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

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("belt", beltBannerSensor.beamBroken());
    SmartDashboard.putBoolean("first", firstCellBannerSensor.beamBroken());
    SmartDashboard.putBoolean("second", secondCellBannerSensor.beamBroken());
    SmartDashboard.putBoolean("third", secondCellBannerSensor.beamBroken());
    SmartDashboard.putBoolean("fourth", fourthCellBannerSensor.beamBroken());
    SmartDashboard.putNumber("count", getBallsInRobot());
  }

  /**
   *Moves the balls in the index wheel forward towards shooter
   */
  private void indexWheelIn() {
    indexWheelMotor.set(1);
  }

  /**
   * Pushes the balls in the index wheel backward towards collector
   */
  private void indexWheelOut() {
    indexWheelMotor.set(-0.7);
  }

  /**
   * Stops the movement of the index wheel
   */
  private void stopIndexWheel() {
    indexWheelMotor.set(0);
  }

  /**
   * Pushes the balls in the belt towards the shooter
   */
  private void runBelt() {
    beltMotor.set(-1);
  }

  /**
   * Pushes the balls in the belt backwards towards the collector
   */
  private void spitBelt() {
    beltMotor.set(-1);
  }

  /**
   * Stops the movement of the belt
   */
  private void stopBelt() {
    beltMotor.set(0);
  }

  /**
   * Feeds balls to the shooter by running the gooseneck
   */
  private void runGooseneck() {
    gooseneckMotor.set(1);
  }

  /**
   * Stops the gooseneck
   */
  private void stopGooseneck() {
    gooseneckMotor.set(0);
  }

  /**
   * Runs the balls backwards towards the indexer wheel
   */
  private void spitOutGooseneck() {
    gooseneckMotor.set(-1);
  }

  /**
   * Runs all the motors/ballpath forward towards the shooter
   */
  public void runAll() {
    runBelt();
    runGooseneck();
    indexWheelIn();
  }

  public void manualLoad() {
    beltMotor.set(-.5);
    indexWheelMotor.set(.3);
  }

  public void manualBelt() {
    beltMotor.set(-.5);
  }

  /**
   * Runs the motors/ballpath backwards towards the collector
   */
  public void spitOutAll() {
    spitBelt();
    spitOutGooseneck();
    indexWheelOut();
  }

  /**
   * Runs the entire indexer system(indexer & belt), not the gooseneck
   */
  public void runIndexer() {
    indexWheelIn();
//    runBelt();
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
    if (fourthCellBannerSensor.beamBroken() && beltBannerSensor.beamBroken()) {
      return 5;
    } else if (fourthCellBannerSensor.beamBroken() ) {
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
