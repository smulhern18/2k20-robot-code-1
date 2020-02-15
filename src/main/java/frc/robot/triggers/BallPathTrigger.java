package frc.robot.triggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.RobotContainer;
import frc.robot.commands.ballpath.DefaultShiftCommand;
import frc.robot.subsystems.BallPathSubsystem;

public class BallPathTrigger extends Trigger {
    private BallPathSubsystem ballPathSubsystem;
    public BallPathTrigger(RobotContainer robotContainer){
        this.ballPathSubsystem = robotContainer.ballPathSubsystem;

    }

    @Override
    public boolean get() {
        return ballPathSubsystem.beltBannerSensor.beamBroken();
    }
}
