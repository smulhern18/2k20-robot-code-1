package frc.robot.models;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class PairedTalonSRX{
    TalonSRX leader, follower;
    FeedbackDevice encoder;

    public PairedTalonSRX(TalonSRX leader, TalonSRX follower, boolean inverted){
        this.leader = leader;
        this.follower = follower;
        this.leader.setInverted(inverted);
        this.follower.setInverted(inverted);
        follower.follow(leader);
        
    }

    public PairedTalonSRX(TalonSRX leader, TalonSRX follower, boolean inverted, FeedbackDevice encoder) {
        this(leader, follower, inverted);
        this.encoder = encoder;
    }

    /**
     * Sets the speed & moves the motor pair with a given control mode and a given value.
     * @param mode {@link ControlMode} 
     * @param val speed of motor [-1, 1]
     */
    public void set(ControlMode mode, double val){
        leader.set(mode, val);
    }

    /**
     * Sets the speed & moves the motor pair with Percent Output control mode and a given value .
     * @param val speed of motor [-1, 1]
     */
    public void set(double val){
        set(ControlMode.PercentOutput, val);
    }

}