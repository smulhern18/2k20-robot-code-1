package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.models.PairedTalonSRX;
import frc.robot.commands.drivetraincommands.DefaultDriveCommand;

/**
 * The motors and sensors that the robot uses to drive.
 */
public class DrivetrainSubsystem extends SubsystemBase { // drivetrain subsystem

    /**
     * Creates a new DrivetrainSubsystem.
     */
    private final int LEFT_LEADER_CHANNEL = 0, LEFT_FOLLOWER_CHANNEL = 1, RIGHT_LEADER_CHANNEL = 2, RIGHT_FOLLOWER_CHANNEL = 3; 

    private final TalonSRX LEFT_LEADER = new TalonSRX(LEFT_LEADER_CHANNEL), LEFT_FOLLOWER = new TalonSRX(LEFT_FOLLOWER_CHANNEL);
    private final TalonSRX RIGHT_LEADER = new TalonSRX(RIGHT_LEADER_CHANNEL), RIGHT_FOLLOWER = new TalonSRX(RIGHT_FOLLOWER_CHANNEL);

    private final PairedTalonSRX LEFT_PAIR = new PairedTalonSRX(LEFT_LEADER, LEFT_FOLLOWER, true);
    private final PairedTalonSRX RIGHT_PAIR = new PairedTalonSRX(RIGHT_LEADER, RIGHT_FOLLOWER, false);

    public DrivetrainSubsystem() {
        this.setDefaultCommand(new DefaultDriveCommand(this));
    }

    /**
     * Drive with a specified mode
     * @param mode A {@link ControlMode}
     * @param left speed of left motor pair [-1, 1]
     * @param right speed of right motor pair [-1, 1]
     */
    public void drive(ControlMode mode, double left, double right){ 
        LEFT_PAIR.set(mode, left);
        RIGHT_PAIR.set(mode, right);
    }

    /**
     * Drives the drive train with percent output as the ControlMode.
     * @param left speed of left motor pair [-1, 1]
     * @param right speed of right motor pair [-1, 1]
     */
    public void drive( double left, double right){
       drive(ControlMode.PercentOutput, left, right);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public String getName(){ //returns the name of the subsytem
        return "Drivetrain";
    }

    @Override
    public String getSubsystem(){ //returns the name of the subsytem for Sendable
        return "Drivetrain";
    }

  
}