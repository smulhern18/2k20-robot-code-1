package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;

public class ShiftToFirst extends CommandBase {
    BallPathSubsystem ballPathSubsystem;
    public ShiftToFirst(RobotContainer robotContainer){
        this.ballPathSubsystem = robotContainer.ballPathSubsystem;
    }

    @Override
    public void execute(){
        ballPathSubsystem.indexIn();
        ballPathSubsystem.runBelt();
    }

    @Override
    public boolean isFinished(){
        return ballPathSubsystem.firstCellBannerSensor.beamBroken() || ballPathSubsystem.secondCellBannerSensor.beamBroken() ||
                ballPathSubsystem.thirdCellBannerSensor.beamBroken() || ballPathSubsystem.fourthCellBannerSensor.beamBroken() ||
                ballPathSubsystem.fifthCellBannerSensor.beamBroken();
    }

    @Override
    public void end(boolean interrupted){
        ballPathSubsystem.stopBelt();
        ballPathSubsystem.stopIndex();
    }
}
