/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.util.Color;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

  public final static double MAX_BATTERY_VOLTAGE = 12.0;
  public final static double LOOP_TIME_S = .2;

  public final static class InputConstants {
    public final static int LEFT_JOYSTICK_CHANNEL = 0;
    public final static int RIGHT_JOYSTICK_CHANNEL = 1;
    public final static int BUTTON_BOX_LEFT_CHANNEL = 2;
    public final static int BUTTON_BOX_RIGHT_CHANNEL = 3;
  }

  public final static class SubsystemConstants {
    public final static String DEBUG_TAB_NAME = "DEBUG";
    public final static String DRIVER_TAB_NAME = "DRIVER";
    public final static ShuffleboardTab DEBUG_TAB = Shuffleboard.getTab(DEBUG_TAB_NAME);
    public final static ShuffleboardTab DRIVER_TAB = Shuffleboard.getTab(DRIVER_TAB_NAME);
  }

  /**
   * Constants for the drivetrain subsystem
   */
  public final static class DrivetrainConstants {
    public final static int LEFT_LEADER_CHANNEL = 1;
    public final static int LEFT_FOLLOWER_CHANNEL = 2;
    public final static int RIGHT_LEADER_CHANNEL = 3;
    public final static int RIGHT_FOLLOWER_CHANNEL = 4;

    // Many of these values found with the frc-characterization tool
    public final static double S_VOLTS = 1.67;
    public final static double V_VOLT_SECONDS_PER_METER = 2.79;
    public final static double A_VOLT_SECONDS_SQUARED_PER_METER = 0.106;
    public final static SimpleMotorFeedforward DRIVE_FEED_FORWARD = new SimpleMotorFeedforward(
        DrivetrainConstants.S_VOLTS, DrivetrainConstants.V_VOLT_SECONDS_PER_METER,
        DrivetrainConstants.A_VOLT_SECONDS_SQUARED_PER_METER);

    public final static double P = .15;
    public final static double I = 0;
    public final static double D = 0;
    public final static double F = 0;

    public final static double TRACKWIDTH_METERS = 0.781987; // horizontal distance between wheels
    public final static DifferentialDriveKinematics DRIVE_KINEMATICS = new DifferentialDriveKinematics(
        TRACKWIDTH_METERS);
    public final static double WHEEL_DIAMETER_METERS = 0.1524;
    public final static double COUNTS_PER_MOTOR_REVOLUTION = 2048;
    public final static double WHEEL_REVOLUTIONS_PER_MOTOR_REVOLUTIONS = (18.0 / 52.0) * (10.0 / 42.0);
    public final static double METERS_PER_COUNT = (1 / COUNTS_PER_MOTOR_REVOLUTION) * // MOTOR ROTATIONS per count
        WHEEL_REVOLUTIONS_PER_MOTOR_REVOLUTIONS * (WHEEL_DIAMETER_METERS * Math.PI);

    public final static double MAX_SPEED_METERS_PER_SECOND = 2.7432;
    public final static double MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 2;
    public final static int MAX_VOLTAGE = 11;

    public final static double RAMSETE_B = 2;
    public final static double RAMSETE_ZETA = .9;

    public final static DifferentialDriveVoltageConstraint AUTO_VOLTAGE_CONSTRAINT = new DifferentialDriveVoltageConstraint(
        DrivetrainConstants.DRIVE_FEED_FORWARD, DrivetrainConstants.DRIVE_KINEMATICS, DrivetrainConstants.MAX_VOLTAGE);

    public final static int SLOT_ID = 0;
    public final static int PID_LOOPTYPE = 0;
    public final static int TIMEOUT_MS = 20;

    public final static String ODOMETRY_ENTRY = "Odometry";
    public final static String VELOCITY_ENTRY = "Velocity";
    public final static String ACCELERATION_ENTRY = "Acceleration";

  }

  /**
   * Constants for the shooter subsystem
   */
  public static class ShooterConstants {
    public final static int LEADER_CHANNEL = 5;
    public final static int FOLLOWER_CHANNEL = 6;

    public final static double P = 3;
    public final static double I = 0;
    public final static double D = 0;
    public final static double F = 6;

    public final static int PID_LOOPTYPE = 0;
    public final static int SLOT_ID = 0;
    public final static int TIMEOUT_MS = 20;

    public final static double COUNTS_PER_REVOLUTION = 12;
    public final static double WHEEL_DIAMETER = 4;
    public final static double MOTOR_TO_WHEEL = 1.23;
    public final static double RPM_THRESHOLD = 100;

    public final static double DEFAULT_RPM = 7200;

    public final static String VELOCITY_ENTRY = "Shooter Velocity";
  }

  public static class VisionConstants {
    public final static int LED_PORT = 0;
    public final static boolean LED_ON = true;
    public final static boolean LED_OFF = false;
    public final static String DEFAULT_JSON = "{\"found\": 0, \"distance\": 0, \"angle\": 0, \"fps\": 0}";
    public final static String TABLE = "Vision";
    public final static String DATA_ENTRY = "data";
    public final static String FOUND_ENTRY = "Target Found";
    public final static String DISTANCE_ENTRY = "Target Distance";
    public final static String ANGLE_ENTRY = "Target Angle";
    public final static String FPS_ENTRY = "Target FPS";
    public final static String FOUND_KEY = "found";
    public final static String DISTANCE_KEY = "distance";
    public final static String ANGLE_KEY = "angle";
    public final static String FPS_KEY = "fps";
  }

  public static class LEDConstants {
    public final static int BLINKIN_PARK_CHANNEL = 0;
  }

  public static class TurretConstants {
    public final static int TURRET_MOTOR_CHANNEL = 2;// TODO: configure
    public final static int TIMEOUT_MS = 20;
    public final static int PID_LOOPTYPE = 0;
    public final static int SLOT_ID = 0;
    public final static double P = 1.0;
    public final static double I = 0; // undetermined
    public final static double D = 0;
    public final static double F = 0;

    public final static String POT_ENTRY = "Potentiometer Value";
    public final static String POSITION_ENTRY = "Position/Angle";

    public final static double POT_MIN = 0;
    public final static double POT_MAX = 2048; // TODO: configure
    public final static double MAX_ROTATION_DEGREES = 270.0;

    public final static double ERROR_TOLERANCE = 0.50;// set to real value
  }

  public final static class ClimberConstants {
    // TODO: Configure DIO ports
    public final static int TOP_SWITCH_PORT = 0;
    public final static int BOTTOM_SWITCH_PORT = 1;
    public final static int TENTIOMETER_SWITCH_PORT = 2;
    public final static int SLAP_SWITCH_PORT = 3;
    public final static int CLIMB_MOTOR_CHANNEL = 4; // TODO: configure
    public final static int SLAPPER_PORT = 3;
    public final static int RATCHET_PORT = 4;
    public final static boolean UNSLAP = true;
    public final static boolean SLAP = false;
    public final static double CLIMB_OFF = 0.0;
    public final static double CLIMB_EXTEND = 0.3;
    public final static double CLIMB_RETRACT = 0.9;
    public final static boolean RATCHET_OFF = false;
    public final static boolean RATCHET_ON = true;
    public final static int TRAVERSE_MOTOR_PORT = 5;
  }

  public final static class TrenchableConstants {
    public final static int TRENCHABLE_PORT = 0;
    public final static int UNTRENCHABLE_PORT = 1;
    public final static DoubleSolenoid.Value TRENCHABLE = DoubleSolenoid.Value.kForward;
    public final static DoubleSolenoid.Value UNTRENCHABLE = DoubleSolenoid.Value.kReverse;
    public final static int UNTRENCHABLE_SWITCH_PORT = 3; // TODO: configure

  }

  public final static class CollectorConstants {
    public final static int COLLECTOR_MOTOR_CHANNEL = 6;// TODO: configure
    public final static int DEPLOY_COLLECTOR_SOLENOID_CHANNEL = 7;
  }

  public final static class BallPathConstants {
    public final static int GOOSENECK_CHANNEL = 9;//TODO: configure
    public final static int INDEXER_MOTOR_CHANNEL = 8;// TODO: configure
    public final static int FIRST_STAGE_MOTOR_CHANNEL = 9;// TODO: configure
    public final static String BALLS_CONTAINED_ENTRY = "Amount of Balls Collected";
    public final static int BELT_BANNER_SENSOR_PORT = 2;
    public final static int FIRST_CELL_BANNER_PORT = 3;// TODO: configure
    public final static int SECOND_CELL_BANNER_PORT = 4;// TODO: configure
    public final static int THIRD_CELL_BANNER_PORT = 5;// TODO: configure
    public final static int FOURTH_CELL_BANNER_PORT = 6;// TODO: configure
    public final static int FIFTH_CELL_BANNER_PORT = 7;// TODO: configure
  }

  public final static class ColorWheelConstants {
    public final static Port COLOR_SENSOR_PORT = I2C.Port.kOnboard;
    public final static Color BLUE_TARGET = ColorMatch.makeColor(0.143, 0.427, 0.429);
    public final static Color GREEN_TARGET = ColorMatch.makeColor(0.197, 0.561, 0.240);
    public final static Color RED_TARGET = ColorMatch.makeColor(0.561, 0.232, 0.114);
    public final static Color YELLOW_TARGET = ColorMatch.makeColor(0.361, 0.524, 0.113);
    public final static double CONFIDENCE_THRESHOLD = 0.5;
    public final static String UNKNOWN = "UNKNOWN";
  }


}
