/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import com.revrobotics.ColorMatch;
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

  public static final double MAX_BATTERY_VOLTAGE = 12.0;
  public static final double LOOP_TIME_S = .2;

  public static final class SubsystemConstants {
    public static final String DEBUG_TAB_NAME = "DEBUG";
    public static final String DRIVER_TAB_NAME = "DRIVER";
    public static final ShuffleboardTab DEBUG_TAB = Shuffleboard.getTab(DEBUG_TAB_NAME);
    public static final ShuffleboardTab DRIVER_TAB = Shuffleboard.getTab(DRIVER_TAB_NAME);
  }

  /**
   * Constants for the drivetrain subsystem
   */
  public static final class DrivetrainConstants {
    public static final int LEFT_JOYSTICK_CHANNEL = 0;
    public static final int RIGHT_JOYSTICK_CHANNEL = 1;

    public static final int LEFT_LEADER_CHANNEL = 1;
    public static final int LEFT_FOLLOWER_CHANNEL = 2;
    public static final int RIGHT_LEADER_CHANNEL = 3;
    public static final int RIGHT_FOLLOWER_CHANNEL = 4;

    // Many of these values found with the frc-characterization tool
    public static final double S_VOLTS = 1.34;
    public static final double V_VOLT_SECONDS_PER_METER = 3.13;
    public static final double A_VOLT_SECONDS_SQUARED_PER_METER = 0.926;
    public static final SimpleMotorFeedforward DRIVE_FEED_FORWARD = new SimpleMotorFeedforward(
        DrivetrainConstants.S_VOLTS, DrivetrainConstants.V_VOLT_SECONDS_PER_METER,
        DrivetrainConstants.A_VOLT_SECONDS_SQUARED_PER_METER);

    public static final double P = 4;
    public static final double I = 0;
    public static final double D = 0;
    public static final double F = 0;

    public static final double TRACKWIDTH_METERS = 0.6889; // horizontal distance between wheels
    public static final DifferentialDriveKinematics DRIVE_KINEMATICS = new DifferentialDriveKinematics(
        TRACKWIDTH_METERS);
    public static final double WHEEL_DIAMETER_METERS = 0.1524;
    public static final double COUNTS_PER_MOTOR_REVOLUTION = 2048;
    public static final double WHEEL_REVOLUTIONS_PER_MOTOR_REVOLUTIONS = (18.0 / 52.0) * (10.0 / 42.0);
    public static final double METERS_PER_COUNT = (1 / COUNTS_PER_MOTOR_REVOLUTION) * // MOTOR ROTATIONS per count
        WHEEL_REVOLUTIONS_PER_MOTOR_REVOLUTIONS * (WHEEL_DIAMETER_METERS * Math.PI);

    public static final double MAX_SPEED_METERS_PER_SECOND = 2.7432;
    public static final double MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 2;
    public static final int MAX_VOLTAGE = 11;

    public static final double RAMSETE_B = 2;
    public static final double RAMSETE_ZETA = .9;

    public static final DifferentialDriveVoltageConstraint AUTO_VOLTAGE_CONSTRAINT = new DifferentialDriveVoltageConstraint(
        DrivetrainConstants.DRIVE_FEED_FORWARD, DrivetrainConstants.DRIVE_KINEMATICS, DrivetrainConstants.MAX_VOLTAGE);

    public static final int SLOT_ID = 0;
    public static final int PID_LOOPTYPE = 0;
    public static final int TIMEOUT_MS = 20;

    public static final String ODOMETRY_ENTRY = "Odometry";
    public static final String VELOCITY_ENTRY = "Velocity";
    public static final String ACCELERATION_ENTRY = "Acceleration";

  }

  /**
   * Constants for the shooter subsystem
   */
  public static class ShooterConstants {
    public static final int LEADER_CHANNEL = 0;// TODO: configure
    public static final int FOLLOWER_CHANNEL = 1;// TODO: configure

    public static final double P = 0;
    public static final double I = 0;
    public static final double D = 0;
    public static final double F = 1.0;

    public static final int PID_LOOPTYPE = 0;
    public static final int SLOT_ID = 0;
    public static final int TIMEOUT_MS = 20;

    public static final double COUNTS_PER_REVOLUTION = 12;
    public static final double WHEEL_DIAMETER = 4;
    public static final double MOTOR_TO_WHEEL = 1.23;
    public static final double ROTATIONS_PER_COUNT = (1 / COUNTS_PER_REVOLUTION) * MOTOR_TO_WHEEL;
    public static final double RPM_THRESHOLD = 100;

    public static final String VELOCITY_ENTRY = "Shooter Velocity";
    public static final String TARGET_ENTRY = "Shooter Target RPM";
    public static final String CONFIG_P_ENTRY = "Config P";
    public static final String CONFIG_I_ENTRY = "Config I";
    public static final String CONFIG_D_ENTRY = "Config D";
    public static final String CONFIG_F_ENTRY = "Config F";
  }

  public static class VisionConstants {
    public static final int LED_PORT = 3;
    public static final boolean LED_ON = true;
    public static final boolean LED_OFF = false;
    public static final String DEFAULT_JSON = "{\"found\": 0, \"distance\": 0, \"angle\": 0, \"fps\": 0}";
    public static final String TABLE = "Vision";
    public static final String DATA_ENTRY = "data";
    public static final String FOUND_ENTRY = "Target Found";
    public static final String DISTANCE_ENTRY = "Target Distance";
    public static final String ANGLE_ENTRY = "Target Angle";
    public static final String FPS_ENTRY = "Target FPS";
    public static final String FOUND_KEY = "found";
    public static final String DISTANCE_KEY = "distance";
    public static final String ANGLE_KEY = "angle";
    public static final String FPS_KEY = "fps";
  }

  public static class LEDConstants {
    public static final int BLINKIN_PARK_CHANNEL = 0;
  }

  public static class TurretConstants {
    public static final int TURRET_MOTOR_CHANNEL = 2;// TODO: configure
    public static final int TIMEOUT_MS = 20;
    public static final int PID_LOOPTYPE = 0;
    public static final int SLOT_ID = 0;
    public static final double P = 1.0;
    public static final double I = 0; // undetermined
    public static final double D = 0;
    public static final double F = 0;

    public static final String POT_ENTRY = "Potentiometer Value";
    public static final String POSITION_ENTRY = "Position/Angle";

    public static final double POT_MIN = 0;
    public static final double POT_MAX = 2048; // TODO: configure
    public static final double MAX_ROTATION_DEGREES = 270.0;
    public static final double MAX_POT_ROTATIONS = 10;

    public static final double ERROR_TOLERANCE = 0.50;// set to real value
  }

  public static final class ClimberConstants {
    // TODO: Configure DIO ports
    public static final int TOP_SWITCH_PORT = 0;
    public static final int BOTTOM_SWITCH_PORT = 1;
    public static final int TENTIOMETER_SWITCH_PORT = 2;
    public static final int SLAP_SWITCH_PORT = 3;
    public static final int CLIMB_MOTOR_CHANNEL = 4; // TODO: configure
    public static final int SLAPPER_PORT = 3;
    public static final int RATCHET_PORT = 4;
    public static final boolean UNSLAP = true;
    public static final boolean SLAP = false;
    public static final double CLIMB_OFF = 0.0;
    public static final double CLIMB_EXTEND = 0.3;
    public static final double CLIMB_RETRACT = 0.9;
    public static final boolean RATCHET_OFF = false;
    public static final boolean RATCHET_ON = true;
    public static final int TRAVERSE_MOTOR_PORT = 5;
  }

  public static final class TrenchableConstants {
    public static final int TRENCHABLE_PORT = 0;
    public static final int UNTRENCHABLE_PORT = 1;
    public static final DoubleSolenoid.Value TRENCHABLE = DoubleSolenoid.Value.kForward;
    public static final DoubleSolenoid.Value UNTRENCHABLE = DoubleSolenoid.Value.kReverse;
    public static final int UNTRENCHABLE_SWITCH_PORT = 3; // TODO: configure

  }

  public static final class CollectorConstants {
    public static final int COLLECTOR_MOTOR_CHANNEL = 6;// TODO: configure
    public static final int DEPLOY_COLLECTOR_SOLENOID_CHANNEL = 7;
  }

  public static final class BallPathConstants {
    public static final int KICKER_MOTOR_CHANNEL = 7;// TODO: configure
    public static final int INDEXER_MOTOR_CHANNEL = 8;// TODO: configure
    public static final int FIRST_STAGE_MOTOR_CHANNEL = 9;// TODO: configure
    public static final String BALLS_CONTAINED_ENTRY = "Amount of Balls Collected";
    public static final int BELT_BANNER_SENSOR_PORT = 2;
    public static final int INDEXER1_BANNER_PORT = 3;// TODO: configure
    public static final int INDEXER2_BANNER_PORT = 4;// TODO: configure
    public static final int INDEXER3_BANNER_PORT = 5;// TODO: configure
    public static final int INDEXER4_BANNER_PORT = 6;// TODO: configure
    public static final int INDEXER5_BANNER_PORT = 7;// TODO: configure
  }

  public static final class ColorWheelConstants {
    public static final Port COLOR_SENSOR_PORT = I2C.Port.kOnboard;
    public static final Color BLUE_TARGET = ColorMatch.makeColor(0.143, 0.427, 0.429);
    public static final Color GREEN_TARGET = ColorMatch.makeColor(0.197, 0.561, 0.240);
    public static final Color RED_TARGET = ColorMatch.makeColor(0.561, 0.232, 0.114);
    public static final Color YELLOW_TARGET = ColorMatch.makeColor(0.361, 0.524, 0.113);
    public static final double CONFIDENCE_LIMIT = 0.5;
  }


}
