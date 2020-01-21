/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DrivetrainConstants{
        public static final int LEFT_JOYSTICK_CHANNEL = 0;
        public static final int RIGHT_JOYSTICK_CHANNEL = 1;

        public static final int LEFT_LEADER_CHANNEL = 0;
        public static final int LEFT_FOLLOWER_CHANNEL = 1;
        public static final int RIGHT_LEADER_CHANNEL = 2;
        public static final int RIGHT_FOLLOWER_CHANNEL = 3;

        public static final double S_VOLTS = 0; //TODO: run characterization tool
        public static final double V_VOLT_SECONDS_PER_METER = 0;
        public static final double A_VOLT_SECONDS_SQUARED_PER_METER = 0;
        public static final double TRACKWIDTH_METERS = 0; // horizontal distance between wheels
        public static final double WHEEL_DIAMETER_METERS = 0.1524;
        public static final DifferentialDriveKinematics DRIVE_KINEMATICS = new DifferentialDriveKinematics(TRACKWIDTH_METERS);
        public static final double MAX_SPEED_METERS_PER_SECOND = 0;
        public static final double MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 0;
        public static final double RAMSETE_B = 2; // DO NOT CHANGE
        public static final double RAMSETE_ZETA = .7; // DO NOT CHANGE

        public static final int PID_X = 0;
        public static final int TIMEOUT_MS = 0;
        public static final double COUNTS_PER_REVOLUTION = 0; //TODO: configure
        public static final double METERS_PER_COUNT = (WHEEL_DIAMETER_METERS * Math.PI) / COUNTS_PER_REVOLUTION;

        public static final int MAX_VOLTAGE = 10;
    }

    public static final class LEDConstants{
        public static final int BLINKIN_PARK_CHANNEL = 3;

    }
    
}
