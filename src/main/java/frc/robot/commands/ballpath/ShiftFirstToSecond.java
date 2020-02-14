package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;

public class ShiftFirstToSecond extends CommandBase {
    BallPathSubsystem ballPathSubsystem;
    public ShiftFirstToSecond(RobotContainer robotContainer){
        this.ballPathSubsystem = robotContainer.ballPathSubsystem;
    }

    @Override
    public void execute(){

    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
