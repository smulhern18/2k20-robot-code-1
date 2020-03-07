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

  public BannerSensor beltBannerSensor, firstCellBannerSensor, secondCellBannerSensor, thirdCellBannerSensor, fourthCellBannerSensor;
  private WPI_TalonSRX indexWheelMotor, beltMotor, gooseneckMotor;
  private int balls = 0;

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
  }

  public void resetBalls() {
    balls = 0;
  }

  public void incrementBalls() {
    balls++;
  }

  public void preload(int balls) {
    this.balls = balls;
  }

  /**
   * Moves the balls in the index wheel forward towards shooter
   */
  private void indexWheelIn() {
    indexWheelMotor.set(.5);
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
    beltMotor.set(-.3);
    indexWheelMotor.set(.15);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("belt", beltBannerSensor.beamBroken());
    SmartDashboard.putBoolean("first", firstCellBannerSensor.beamBroken());
    SmartDashboard.putBoolean("second", secondCellBannerSensor.beamBroken());
    SmartDashboard.putBoolean("third", thirdCellBannerSensor.beamBroken());
    SmartDashboard.putBoolean("fourth", fourthCellBannerSensor.beamBroken());
    SmartDashboard.putNumber("balls", getBallsInRobot());
  }

  public void manualBelt() {
    beltMotor.set(-.3);
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
    return balls;
  }

  public enum BallPathDirection {
    IN,
    OUT
  }
}
